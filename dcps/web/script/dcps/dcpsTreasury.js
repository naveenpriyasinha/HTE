var dummyOfficeId;

function dcpsEmpDetach()
{
	var totalEmployees = document.getElementById("hdnCounter").value ;
	var hidString = document.getElementById("hidString").value;
	var Emp_Id="";
	var remarks = "" ;
	var cmbReasons = "" ;
	var attachDates = "" ;
	var officeCodes = "" ;
	var Deputn_Id = "";

	var finalSelectedEmployee=0;
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("chkbxDeputnId"+k).checked)
		{  
			finalSelectedEmployee = k ;
		}
	}

	if(finalSelectedEmployee == 0)
	{
		alert('Please select at least one employee');
		return ; 
	}
	
	for(var i=1;i<=totalEmployees;i++)
	{
		if(document.getElementById("chkbxDeputnId"+i).checked)
		{
			if(i==finalSelectedEmployee)
			{
				Emp_Id = Emp_Id + document.getElementById("chkbxDeputnId"+i).value;
				remarks= remarks + document.getElementById("txtRemarks"+i).value;
				cmbReasons= cmbReasons + document.getElementById("cmbReason"+i).value;
				attachDates = attachDates + document.getElementById("txtDateOfDetach"+i).value;
				officeCodes = officeCodes + document.getElementById("cmbOfficeCode"+i).value;
				Deputn_Id = Deputn_Id + document.getElementById("txtDeptnId"+i).value;
			}
			else
			{
				Emp_Id = Emp_Id + document.getElementById("chkbxDeputnId"+i).value +  "~";
				remarks= remarks + document.getElementById("txtRemarks"+i).value + "~";
				cmbReasons= cmbReasons + document.getElementById("cmbReason"+i).value + "~";
				attachDates = attachDates + document.getElementById("txtDateOfDetach"+i).value + "~";
				officeCodes = officeCodes + document.getElementById("cmbOfficeCode"+i).value + "~" ;
				Deputn_Id = Deputn_Id + document.getElementById("txtDeptnId"+i).value+ "~" ;
			}
		}
	}
	
		var url = "ifms.htm?actionFlag=dcpsEmpDeputation&Emp_Id="+Emp_Id+"&remarks="+remarks+"&cmbReasons="+cmbReasons+"&detachDates="+attachDates
		+"&officeCodes="+officeCodes+"&hidString="+hidString+"&deputnIds="+Deputn_Id ;
		DetachUsingAjax(url);
	
}

function DetachUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	
	xmlHttp.onreadystatechange=detachCaseStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function detachCaseStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			var XMLDoc=xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC1');
			var hidString = XmlHiddenValues[0].childNodes[0].text;
			var successFlag =  XmlHiddenValues[0].childNodes[1].text;
				if(successFlag)
				{
				alert("Employee has been detached successfully.");
				self.location.href="ifms.htm?actionFlag=loadEmpDeputation&queryString="+hidString;
				}
		}
	}
}

function dcpsEmpAttach()
{
	var totalEmployees = document.getElementById("hdnCounter").value ;
	var hidString = document.getElementById("hidString").value;
	var Emp_Id=" ";
	var remarks = "" ;
	var cmbReasons = "" ;
	var attachDates = "" ;
	var officeCodes = "" ;
	var Deputn_Id = "";

	var finalSelectedEmployee=0;
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("chkbxDeputnId"+k).checked)
		{  
			finalSelectedEmployee = k ;
		}
	}
	
	if(finalSelectedEmployee == 0)
	{
		alert('Please select at least one employee');
		return ; 
	}
	
	for(var i=1;i<=totalEmployees;i++)
	{
		if(document.getElementById("chkbxDeputnId"+i).checked)
		{
			if(i==finalSelectedEmployee)
			{
				Emp_Id = Emp_Id + document.getElementById("chkbxDeputnId"+i).value;
				remarks= remarks + document.getElementById("txtRemarks"+i).value;
				cmbReasons= cmbReasons + document.getElementById("cmbReason"+i).value;
				attachDates = attachDates + document.getElementById("txtDateOfAttach"+i).value;
				officeCodes = officeCodes + document.getElementById("cmbOfficeCode"+i).value;
				Deputn_Id = Deputn_Id + document.getElementById("txtDeptnId"+i).value;
			}
			else
			{
				Emp_Id = Emp_Id + document.getElementById("chkbxDeputnId"+i).value +  "~";
				remarks= remarks + document.getElementById("txtRemarks"+i).value + "~";
				cmbReasons= cmbReasons + document.getElementById("cmbReason"+i).value + "~";
				attachDates = attachDates + document.getElementById("txtDateOfAttach"+i).value + "~";
				officeCodes = officeCodes + document.getElementById("cmbOfficeCode"+i).value + "~" ;
				Deputn_Id = Deputn_Id + document.getElementById("txtDeptnId"+i).value+ "~" ;
			}
		}
	}
	
		url = "ifms.htm?actionFlag=dcpsEmpDeputation&Emp_Id="+Emp_Id+"&remarks="+remarks+"&cmbReasons="+cmbReasons+"&attachDates="+attachDates
		+"&officeCodes="+officeCodes+"&hidString="+hidString+"&deputnIds="+Deputn_Id ;
		
		AttachUsingAjax(url);
	
}
function AttachUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	
	xmlHttp.onreadystatechange=AttachCaseStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function AttachCaseStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			var XMLDoc=xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC1');
			var hidString = XmlHiddenValues[0].childNodes[0].text;
			var successFlag =  XmlHiddenValues[0].childNodes[1].text;
				if(successFlag)
				{
				alert("Employee has been Attached successfully.");
				self.location.href="ifms.htm?actionFlag=loadEmpDeputation&queryString="+hidString;
				}
		}
	}
}

function showEmpDeputationList()
{
	var txtSevaarthId =  document.getElementById("txtSevaarthId").value.trim();
	var txtEmployeeName = document.getElementById("txtEmployeeName").value.trim();
	var queryString = document.getElementById("hidString").value.trim();
	
	if(txtSevaarthId == "" && txtEmployeeName == "")
	{
		alert('Please enter search criteria');
		return;
	}
	
	var url ="ifms.htm?actionFlag=loadEmpDeputation&requestForSearch=Yes&txtSevaarthId="+txtSevaarthId+"&txtEmployeeName="+txtEmployeeName+"&queryString="+queryString;
		
	showProgressbar('Please Wait<br>Your request is in progress...');
	document.frmEmpDeputn.action = url ;
	enableAjaxSubmit(true);
	document.frmEmpDeputn.submit();
	
}

function saveDummyOfficeInfo(flag)
{
	var uri;
	var saveOrUpdateFlag = flag;
	uri = "ifms.htm?actionFlag=saveDummyOffice&saveOrUpdateFlag="+saveOrUpdateFlag;
	saveDummyOfficeDetailsUsingAjx(uri);
}

function saveDummyOfficeDetailsUsingAjx(uri)
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
			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var success_flag = XmlHiddenValues[0].childNodes[0].text;
			var dummyOfficeId = XmlHiddenValues[0].childNodes[1].text;
			if(success_flag)
			{
				alert("Dummy Office details saved successfully.");
				self.location.href = "ifms.htm?actionFlag=loadDummyOfficeEntry";
			}
		}
	}
}

function newDummyOffice()
{
	self.location.href = "ifms.htm?actionFlag=loadDummyOfficeEntry" ;
}

function editDummyOffice()
{
	var totalselectedEmployees=0;
	var totalEmployees = document.getElementById("hdnCounter").value ;
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{  	
			totalselectedEmployees++ ;
		}
	}

	if(totalselectedEmployees > 1)
	{
		alert('Please select only one office');
		return ;
	}
	else if(totalselectedEmployees == 1)
	{
		for(k=1;k<=totalEmployees;k++)
		{
			if(document.getElementById("checkbox"+k).checked)
			{  	
				 dummyOfficeId= document.getElementById("checkbox"+k).value;
			}
		}
		self.location.href = "ifms.htm?actionFlag=loadDummyOfficeEntry&dummyOfficeId="+dummyOfficeId ;
	}
	else
	{}
}

function goBack()
{
	self.location.href = "ifms.htm?actionFlag=loadDummyOffice" ;
}

function displayAllDeputedEmpList()
{
	var queryString =   document.getElementById("hidString").value.trim() ;
	self.location.href = "ifms.htm?actionFlag=loadEmpDeputation&queryString="+queryString+"&elementId=700047";
}

function chkDtAfterDeselctn(counter)
{
	var attachDate = document.getElementById("txtDateOfAttach"+counter).value.trim();
	var deselectionDate = document.getElementById("hidDeselectionDate"+counter).value.trim();
	if(deselectionDate != "" && attachDate != "")
		{
			if(compareDatesWithoutAlert(attachDate,deselectionDate,'<'))
				{
					alert('Attach date must be greater than De-selection date - ' + deselectionDate);
					document.getElementById("txtDateOfAttach"+counter).value = "" ;
					return false;
				}
			else
				{
					return true;
				}
		}
	return true;
}

function chkDtAfterAttachment(counter)
{
	var detachDate = document.getElementById("txtDateOfDetach"+counter).value.trim();
	var attachDate = document.getElementById("hidAttachDate"+counter).value.trim();
	if(compareDatesWithoutAlert(detachDate,attachDate,'<'))
	{
		alert('Detach date must be greater than Attach date - ' + attachDate);
		document.getElementById("txtDateOfDetach"+counter).value = "" ;
		return false;
	}
	else
	{
		return true;
	}
	return true;
}


