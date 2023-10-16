function SaveDdoData()
{
	if(validDdoInfo() == true)
	{
	
		uri = "ifms.htm?actionFlag=saveDdoInfo";
		saveDDODetailsUsingAjx(uri);
	}
}

function saveDDODetailsUsingAjx(uri)
{

   xmlHttp=GetXmlHttpObject();

   if (xmlHttp==null)
   {
	   alert ("Your browser does not support AJAX!");
	   return;
   }  
   
   var url = runForm(0); 
   url = uri + url; 
   
   xmlHttp.onreadystatechange=ddocaseStateChanged;
   xmlHttp.open("POST",uri,false);
   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xmlHttp.send(url);
   
}





function ddocaseStateChanged() 
{ 


	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("DDO Details saved successfully.");
		}
	}
}

function validDdoInfo()
{
	if(IsEmptyFun("txtDDOCode",DDOCODE,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtAdminDept",ADMNDEPT,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtFieldDept",FIELDDEPT,'0')==false)
	{
		return false;
	}
	return true;
}




function saveDesigInfo()
{
	
	var uri;
	
    //if(validDesigInfo() == true)
	{
			   
			 
			   uri = "ifms.htm?actionFlag=saveDesigInfo";
			   saveDesigDetailsUsingAjx(uri);
			  // resetAllFieldsAfterSavePressed();
		       
    } 
	
}


function saveDesigDetailsUsingAjx(uri)
{

   xmlHttp=GetXmlHttpObject();

   if (xmlHttp==null)
   {
	   alert ("Your browser does not support AJAX!");
	   return;
   }  
   
   var url = runForm(0); 
   url = uri + url; 
   
   xmlHttp.onreadystatechange=desigCaseStateChanged;
   xmlHttp.open("POST",uri,false);
   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xmlHttp.send(url);
   
}





function desigCaseStateChanged() 
{ 


	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("Designation Details saved successfully.");
			
			var currFieldDeptId = document.getElementById("cmbFieldDepartment").value;
			self.location.href = "ifms.htm?actionFlag=loadDesigInfo&currFieldDeptId="+currFieldDeptId ;
		}
	}
}


function validDesigInfo()
{
	
}


function saveOrgInfo()
{
	
	var uri;
	
    //if(validOrgInfo() == true)
	{
			   
			 
			   uri = "ifms.htm?actionFlag=saveOrgInfo";
			   saveOrgDetailsUsingAjx(uri);
			  // resetAllFieldsAfterSavePressed();
		       
    } 
	
}


function saveOrgDetailsUsingAjx(uri)
{

   xmlHttp=GetXmlHttpObject();

   if (xmlHttp==null)
   {
	   alert ("Your browser does not support AJAX!");
	   return;
   }  
   
   var url = runForm(0); 
   url = uri + url; 
  
   xmlHttp.onreadystatechange=orgCaseStateChanged;
   xmlHttp.open("POST",uri,false);
   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xmlHttp.send(url);
   
}





function orgCaseStateChanged() 
{ 


	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("Organization Details saved successfully.");
			self.location.href="ifms.htm?actionFlag=loadOrgInfo";
			
		}
	}
}

function validOrgInfo()
{
	
}

function clearAllValues()
{
	document.getElementById("txtOrgType").value='';
	document.getElementById("txtOrgDesc").value='';
	document.getElementById("txtEmpHeadAcc").value='';
	document.getElementById("txtEmpSchemeCode").value='';
	document.getElementById("txtEmplrHeadAcc").value='';
	document.getElementById("txtEmplrSchemeCode").value='';
	document.getElementById("txtDeptEmpHeadAcc").value='';
	document.getElementById("txtDeptEmpSchemeCode").value='';
	document.getElementById("txtDeptEmplrHeadAcc").value='';
	document.getElementById("txtDeptEmplrSchemeCode").value='';
}



function getDesigsForTheFieldDept()
{
	var currFieldDeptId = document.getElementById("cmbFieldDepartment").value;
	self.location.href = "ifms.htm?actionFlag=loadDesigInfo&currFieldDeptId="+currFieldDeptId ;
}