var EvtgridCount = 0;
var SergridCount = 0;
var qulfSerCount=0
var rowFCnt=0;
var empIDs; 
var empNames;
var empDtlsSize;
function TierICntrnbtnTableAddRow()
{
	
	//if(empDtlsSize==0)
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
	   		
	   		
	   		Cell1.innerHTML = '<input type="checkbox" name="chkbxArr" id="chkbxArr'+Number(rowFCnt)+'" size="10" onkeypress="numberFormat(this);"   style="width:40px" value="'+document.getElementById('txtEmployeeId').value+'" readonly="readonly"/>';
	   		Cell2.innerHTML ='<input type="text" name="txtFinancialYr" id="txtFinancialYr'+Number(rowFCnt)+'" size="30" onkeypress="numberFormat(this);"   style="width:100px" readonly="readonly" value="2011"/>'; 
	   		Cell3.innerHTML ='<input type="text" name="txtEmpPnsnId" id="txtEmpPnsnId'+Number(rowFCnt)+'" size="30" onkeypress="numberFormat(this);"   style="width:100px" value="'+document.getElementById('txtEmployeeId').value+'" readonly="readonly" />';
	   		Cell4.innerHTML ='<input type="text" name="txtEmpName" id="txtEmpName'+Number(rowFCnt)+'" size="60"  style="width:150px" value="'+document.getElementById('txtEmployeeName').value+'" readonly="readonly" />';
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
		  
	   
	   		if(document.getElementById('txtEmployeeId').value == null)
	   		{
	   			alert("Employee ID cannot be null.");
	   			return;
	   		}
	   		//getData(empId,Number(rowFCnt));
	   		
	   		document.getElementById("chkbxArr"+Number(rowFCnt)).value= document.getElementById("chkbxArr"+Number(rowFCnt)).value;
	   		document.getElementById("hidGridSize").value = Number(rowFCnt)+1;
	}
	 
		
	if(document.frmTierICntrnbtn.chkEmployee.checked == true)
	{
		getEmpOfDDODesig();
		if(empDtlsSize>0)
		{
			
			var lEmpNamesArr = empNames;
			var lEmpIdArr = empIDs;
			var idLength = lEmpIdArr.length;
			var nameLength = lEmpNamesArr.length;
			
			
			var namefinalArr = lEmpNamesArr.substr(1,nameLength-2);
			var idfinalArr = lEmpIdArr.substr(1,idLength-2);
			
			var arrEmpFinalName = namefinalArr.split(",");
			var arrEmpFinalId = idfinalArr.split(",");
			for(var Cnt=0;Cnt<empDtlsSize;Cnt++)
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
		   		
		   		
		   		Cell1.innerHTML = '<input type="checkbox" name="chkbxArr" id="chkbxArr'+Number(rowFCnt)+'" size="10" value="'+arrEmpFinalId[rowFCnt]+'" onkeypress="numberFormat(this);"   style="width:40px"  readonly="readonly"/>';
		   		Cell2.innerHTML ='<input type="text" name="txtFinancialYr" id="txtFinancialYr'+Number(rowFCnt)+'" size="30" onkeypress="numberFormat(this);"   style="width:100px" readonly="readonly" value="2011"/>'; 
		   		Cell3.innerHTML ='<input type="text" name="txtEmpPnsnId" id="txtEmpPnsnId'+Number(rowFCnt)+'" size="30" onkeypress="numberFormat(this);"   style="width:100px" value="'+arrEmpFinalId[rowFCnt]+'" />';
		   		Cell4.innerHTML ='<input type="text" name="txtEmpName" id="txtEmpName'+Number(rowFCnt)+'" size="60"  style="width:150px" value="'+arrEmpFinalName[rowFCnt]+'" />';
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
		   		Cell16.innerHTML = '<input type="hidden" name="hidEmpId" id="hidEmpId'+Number(rowFCnt)+'" value="'+arrEmpFinalId[rowFCnt]+'" />';
			  
		   	
		   		
		   		//getData(empId,Number(rowFCnt));  
		   		
		   		document.getElementById("hidGridSize").value = Number(rowFCnt)+1;
		   		
		   		document.getElementById("chkbxArr"+Number(rowFCnt)).value= document.getElementById("chkbxArr"+Number(rowFCnt)).value;
			}
		}
	}
		
		
		
		
		
		
	
	
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
	document.getElementById("txtEmployeeName").value='';	
	
	
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
         alert("Tier Details saved successfully."); 
         self.location.reload();
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
function getEmpOfDDODesig()
{
	if(document.frmTierICntrnbtn.chkEmployee.checked == true)
	{
		
		var cmbDesignation =  document.getElementById('cmbDesignation').value;
		var cmbDesignationName = document.getElementById('cmbDesignation').options[document.getElementById('cmbDesignation').selectedIndex].text;
		var uri = "ifms.htm?actionFlag=getEmpDtlsFromDDODesig&cmbDesignation="+cmbDesignation; 
		getEmpOfDDODesigUsingAjx(uri);
	}
	else
	{
		return;
	}
}


function getEmpOfDDODesigUsingAjx(uri)
{
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		return;
	}  	 	   
	var url = runForm(0); 	  
	url = uri + url; 	  
	xmlHttp.onreadystatechange=EmpCaseStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(url);
	
}

function EmpCaseStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{
		var XMLDoc=xmlHttp.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC1');
		empIDs = XmlHiddenValues[0].childNodes[0].text;
		empNames = XmlHiddenValues[0].childNodes[1].text;
		empDtlsSize = XmlHiddenValues[0].childNodes[2].text;
		if(xmlHttp.status==200)
		{

			document.getElementById("empIDs").value = empIDs;
			document.getElementById("empNames").value = empNames;
			document.getElementById("empDtlsSize").value = empDtlsSize;
			//return empDtls;
			
			
		}
	  
    }
	
}


function showEmpNameFromId()
{
	var txtEmployeeId =  document.getElementById('txtEmployeeId').value;
	var uri = "ifms.htm?actionFlag=getEmpNameFromId&txtEmployeeId="+txtEmployeeId; 
	getEmpNameFromIdUsingAjx(uri);
	
}
function getEmpNameFromIdUsingAjx(uri)
{
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		return;
	}  	 	   
	var url = runForm(0); 	  
	url = uri + url; 	  
	xmlHttp.onreadystatechange=EmpNameCaseStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(url);
	
}

function EmpNameCaseStateChanged() 
{
	if (xmlHttp.readyState==4)
	{
		var XMLDoc=xmlHttp.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC2');
		var empName = XmlHiddenValues[0].childNodes[0].text;
		if(xmlHttp.status==200)
		{
			document.getElementById("txtEmployeeName").value = empName;
		}
	}
}
function disabledEmpNameId()
{

		document.getElementById("txtEmployeeId").style.display="none";
		document.getElementById("txtEmployeeName").style.display="none";

	
}
function enabledEmpNameId()
{
	document.getElementById("txtEmployeeId").style.display="block";
	document.getElementById("txtEmployeeName").style.display="block";
	
}



function ForwardTierToDDO()
{
	var temp ="";
	var flag=0;
	var chk = new Array();
	var j=0;
	var Emp_Id=" ";
	var ForwardToPost = document.frmTierICntrnbtn.ForwardToPost.value;
	rowFCnt = document.getElementById("hidGridSize").value;
	
	
	var chkbxName = document.getElementsByName("chkbxArr");
	
	if(document.frmTierICntrnbtn.chkEmployee.checked == true)
	{
		getEmpOfDDODesig();
		if(empDtlsSize>0)
		{
			try
			{
				for(var i = 1;i<chkbxName.length;i++)
				{
				
					if(document.getElementById("chkbxArr"+i)!=null)
					{
						
						if(document.getElementById("chkbxArr"+i).checked == true)
						{
							temp = temp + document.getElementById("chkbxArr"+i).value +  "~" ;
							flag = 1;
							
						}
						
					}
					
				}
			
			}
			catch(Exception)
			{
			}
			
		}
	}
	
	else
	{
		
		try
		{
			
			
			for(var i = 0;i<chkbxName.length;i++)
			{
				
				
			
				
				if(document.getElementById("chkbxArr"+i)!=null)
				{
					
					if(document.getElementById("chkbxArr"+i).checked == true)
					{
						temp = temp + document.getElementById("chkbxArr"+i).value +  "~" ;
						flag = 1;
						
					}
					
				}
				
			}
		
		}
		catch(Exception)
		{
		}
		
}
	
	
	try
	{
		chk = temp.split("~");
		for(var j=0;j<chk.length;j++)
		{
			Emp_Id = Emp_Id + chk[j] + "~";
			
			
		}
	}
	catch(Exception)
	{
		
	}
	
		
	
	

	if(flag == 1)
	{
		FrwrdTierDDOUsingAjax("ifms.htm?actionFlag=forwardTierDDO&Emp_Id="+Emp_Id+"&ForwardToPost="+ForwardToPost+"&TierType="+document.getElementById("hidTierType").value);
	}
	else
	{
		alert("Please select a case to forward!");
	}

	
}
function FrwrdTierDDOUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	xmlHttp.onreadystatechange=FrwrdTierStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function FrwrdTierStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		var XMLDoc=xmlHttp.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCTIER');
		var hidTierType = XmlHiddenValues[0].childNodes[0].text;
		if(xmlHttp.status==200)
		{
			alert("The Case has been forwarded to DDO Approver.");
			self.location.href = "ifms.htm?actionFlag=loadDCPSTier&UserType=DDOAsst&TierType="+hidTierType;
			//self.location.reload();
		}
	}
}

function rejectTierData()
{
	var temp ="";
	var flag=0;
	var chk = new Array();
	var j=0;
	var Emp_Id=" ";
	var remarks = document.getElementById("txtRemarks").value; 
	
	rowFCnt = document.getElementById("hidGridSize").value;
	
	
	var chkbxName = document.getElementsByName("chkbxArr");
	
	if(document.frmTierICntrnbtn.chkEmployee.checked == true)
	{
		getEmpOfDDODesig();
		if(empDtlsSize>0)
		{
			try
			{
				for(var i = 1;i<chkbxName.length;i++)
				{
				
					if(document.getElementById("chkbxArr"+i)!=null)
					{
						
						if(document.getElementById("chkbxArr"+i).checked == true)
						{
							temp = temp + document.getElementById("chkbxArr"+i).value +  "~" ;
							flag = 1;
							
						}
						
					}
					
				}
			
			}
			catch(Exception)
			{
			}
			
		}
	}
	
	else
	{
		
		try
		{
			
			
			for(var i = 0;i<chkbxName.length;i++)
			{
				
				if(document.getElementById("chkbxArr"+i)!=null)
				{
					
					if(document.getElementById("chkbxArr"+i).checked == true)
					{
						temp = temp + document.getElementById("chkbxArr"+i).value +  "~" ;
						flag = 1;
						
					}
					
				}
				
			}
		
		}
		catch(Exception)
		{
		}
		
}
	
	
	try
	{
		chk = temp.split("~");
		for(var j=0;j<chk.length;j++)
		{
			Emp_Id = Emp_Id + chk[j] + "~";
			
			
		}
	}
	catch(Exception)
	{
		
	}
	
	
	
	
	


	if(flag == 1)
	{
		RejectTierCaseUsingAjax("ifms.htm?actionFlag=rejectTierCase&Emp_Id="+Emp_Id+"&remarks="+remarks+"&TierType="+document.getElementById("hidTierType").value);
	}
	else
	{
		alert("Please select a case to forward!");
	}

	
}
function RejectTierCaseUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	xmlHttp.onreadystatechange=RejectTierDataStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function RejectTierDataStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		var XMLDoc=xmlHttp.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCTIER');
		var hidTierType = XmlHiddenValues[0].childNodes[0].text;
		if(xmlHttp.status==200)
		{
			alert("The Case has been sent back to DDO Assistant.");
			self.location.href = "ifms.htm?actionFlag=loadDCPSTierDDO&UserType=DDO&TierType="+hidTierType;
		}
	}
}


function approveTierData()
{
	var temp ="";
	var flag=0;
	var chk = new Array();
	var j=0;
	var Emp_Id=" ";
	var ForwardToPost = document.frmTierICntrnbtn.ForwardToPost.value;
	rowFCnt = document.getElementById("hidGridSize").value;
	
	var chkbxName = document.getElementsByName("chkbxArr");
	
	if(document.frmTierICntrnbtn.chkEmployee.checked == true)
	{
		getEmpOfDDODesig();
		if(empDtlsSize>0)
		{
			try
			{
				for(var i = 1;i<chkbxName.length;i++)
				{
				
					if(document.getElementById("chkbxArr"+i)!=null)
					{
						
						if(document.getElementById("chkbxArr"+i).checked == true)
						{
							temp = temp + document.getElementById("chkbxArr"+i).value +  "~" ;
							flag = 1;
							
						}
						
					}
					
				}
			
			}
			catch(Exception)
			{
			}
			
		}
	}
	
	else
	{
		
		try
		{
			
			
			for(var i = 0;i<chkbxName.length;i++)
			{
				
				
			
				
				if(document.getElementById("chkbxArr"+i)!=null)
				{
					
					if(document.getElementById("chkbxArr"+i).checked == true)
					{
						temp = temp + document.getElementById("chkbxArr"+i).value +  "~" ;
						flag = 1;
						
					}
					
				}
				
			}
		
		}
		catch(Exception)
		{
		}
		
}
	
	
	try
	{
		chk = temp.split("~");
		for(var j=0;j<chk.length;j++)
		{
			Emp_Id = Emp_Id + chk[j] + "~";
			
			
		}
	}
	catch(Exception)
	{
		
	}
	
	
	
	
	


	if(flag == 1)
	{
		ApproveTierCaseUsingAjax("ifms.htm?actionFlag=approveTierCase&Emp_Id="+Emp_Id+"&TierType="+document.getElementById("hidTierType").value);
	}
	else
	{
		alert("Please select a case to forward!");
	}

	
}
function ApproveTierCaseUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	xmlHttp.onreadystatechange=ApproveTierDataStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function ApproveTierDataStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		var XMLDoc=xmlHttp.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCTIER');
		var hidTierType = XmlHiddenValues[0].childNodes[0].text;
		if(xmlHttp.status==200)
		{
			alert("The Case has been approved by DDO Approver.");
			//self.location.reload();
			self.location.href = "ifms.htm?actionFlag=loadDCPSTierDDO&UserType=DDO&TierType="+hidTierType;
		}
	}
}



