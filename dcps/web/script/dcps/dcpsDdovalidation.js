var saveOrUpdateFlag=0;  //			1 for save and 2 for update
var ddoOfficeId="";


function EditDDOValidation()
{
     //x=document.getElementById("txtNameOfOffice")
     //x.disabled = !x.disabled;

     /*x=document.getElementById("txtGov")
     x.disabled = x.disabled;

     x=document.getElementById("txtDate")
     x.disabled = x.disabled;*/
     
     x=document.getElementById("cmbState");
     x.disabled = x.disabled;
     
     x=document.getElementById("cmbDist");
     x.disabled = !x.disabled;
     
     x=document.getElementById("cmbTaluka");
     x.disabled = !x.disabled;
     
     //x=document.getElementById("txtTown");
     //x.disabled = !x.disabled;
     
     x=document.getElementById("txtVillage");
     x.disabled = !x.disabled;
     
     x=document.getElementById("txtAddress1");
     x.disabled = !x.disabled;
     
     x=document.getElementById("txtPin");
     x.disabled = !x.disabled;
     
     x=document.getElementById("cmbOfficeCityClass");
     x.disabled = x.disabled;
     
     x=document.getElementById("txtdiceCode");
     x.disabled = x.disabled;
     
     x=document.getElementById("txtGrant");
     x.disabled = !x.disabled;
     
     x=document.getElementById("txtTelNo1");
     x.disabled = !x.disabled;
     
     x=document.getElementById("txtTelNo2");
     x.disabled = !x.disabled;
     
     x=document.getElementById("txtMobileNo");
     x.disabled = !x.disabled;
     
     x=document.getElementById("txtEmail");
     x.disabled = !x.disabled;     
     
     x=document.getElementById("cmbcity");
     x.disabled = !x.disabled; 
}


function SaveOfficeData()
{
	if(ValidateOfficeData())
	{
		SaveDataUsingAjax();
	}
}
function SaveDataUsingAjax()
{
	saveOrUpdateFlag=1;
	
	var txtNameOfOffice = document.getElementById("txtNameOfOffice").value;
	

	
	
	var cmbState = document.getElementById("cmbState").value;
	var cmbDist = document.getElementById("cmbDist").value;
	var cmbTaluka = document.getElementById("cmbTaluka").value;
	var txtTown = document.getElementById("txtTown").value;
	var cmbcity = document.getElementById("cmbcity").value;
	var txtGrant = document.getElementById("txtGrant").value;
	
	var txtVillage = document.getElementById("txtVillage").value;
	
	var txtAddress1 = document.getElementById("txtAddress1").value;
	
	var txtPin = document.getElementById("txtPin").value;
	var cmbOfficeCityClass = document.getElementById("cmbOfficeCityClass").value;
	var txtTelNo1 = document.getElementById("txtTelNo1").value;
	var txtTelNo2 = document.getElementById("txtTelNo2").value;
	var txtMobileNo = document.getElementById("txtMobileNo").value;
	var txtEmail = document.getElementById("txtEmail").value;
	var txtDdoCode = document.getElementById("txtDdoCode").value;
	var txtdiceCode = document.getElementById("txtdiceCode").value;
	
	for(i=0;i<2;i++)
	{
		if(document.frmDCPSDdoOffice.RadioButtonTriableArea[i].checked)
		{
		var RadioButtonTriableArea = document.frmDCPSDdoOffice.RadioButtonTriableArea[i].value;
		}
	}
	
	for(i = 0;i<2;i++)
	{
		if(document.frmDCPSDdoOffice.RadioButtonHillyArea[i].checked)
		{
			var RadioButtonHillyArea = document.frmDCPSDdoOffice.RadioButtonHillyArea[i].value;
		}
	}
	for(i = 0;i<2;i++)
	{
		if(document.frmDCPSDdoOffice.RadioButtonNaxaliteArea[i].checked)
		{
			var RadioButtonNaxaliteArea = document.frmDCPSDdoOffice.RadioButtonNaxaliteArea[i].value;
		}
	}
	if(cmbcity == -1)
	{
		alert('Please select City.');
		return false;
	}
	var uri = "ifms.htm?actionFlag=saveDdoOffice";
	
	var url1 = "txtdiceCode="+txtdiceCode+"&txtDdoCode="+txtDdoCode+"&txtNameOfOffice="+txtNameOfOffice+"&cmbState="+cmbState
				+"&cmbDist= "+cmbDist+"&cmbTaluka="+cmbTaluka+"&txtGrant="+txtGrant+"&txtTown="+txtTown+"&txtVillage="+txtVillage;
	var url2 =	"&txtAddress1="+txtAddress1+"&txtPin="+txtPin+"&cmbOfficeCityClass="+cmbOfficeCityClass+"&txtTelNo1="+txtTelNo1
				+"&txtTelNo2="+txtTelNo2+"&txtMobileNo="+txtMobileNo+"&txtEmail="+txtEmail;	
	var url3 =  "&RadioButtonTriableArea="+RadioButtonTriableArea+"&RadioButtonHillyArea="+RadioButtonHillyArea+"&RadioButtonNaxaliteArea="+RadioButtonNaxaliteArea;
	var url4 =  "&saveOrUpdateFlag="+saveOrUpdateFlag;
	var url = url1 + url2 + url3 + url4 ;
	
	
	
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForSaveOffice(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );

}

function getDataStateChangedForSaveOffice(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(successFlag)
	{
		alert("Office Data Entered Successfully");
		self.location.href="ifms.htm?actionFlag=loadDdoOffice&elementId=700018";
	}
}

function UpdateOfficeData(ddoOfficeIdValue)
{
	//alert(ValidateOfficeData());
	if(ValidateOfficeData())
	{
	UpdateDataUsingAjax(ddoOfficeIdValue);
	}	
}

function UpdateDataUsingAjax(ddoOfficeIdValue)
{
	//alert('inside UpdateDataUsingAjax');
	ddoOfficeId = ddoOfficeIdValue ;
	saveOrUpdateFlag=2;
	
	var txtNameOfOffice = document.getElementById("txtNameOfOffice").value;
/*	for(var i = 0;i<2;i++)
	{
		if(document.frmDCPSDdoOffice.radioButtonDDO[i].checked)
		{
			var radioButtonDDO = document.frmDCPSDdoOffice.radioButtonDDO[i].value;
		}
	}*/
	
	//alert("1");
	var cmbState = document.getElementById("cmbState").value;
	//alert('cmbState: '+cmbState);
	var cmbDist = document.getElementById("cmbDist").value;
	//alert('cmbDist: '+cmbDist);
	//alert("1.1");
	var cmbTaluka = document.getElementById("cmbTaluka").value;
	//alert('cmbTaluka: '+cmbTaluka);
	//var txtTown = document.getElementById("txtTown").value;
	//alert('txtTown: '+txtTown);
	//alert("1.2");
	var cmbcity = document.getElementById("cmbcity").value;
	var txtGrant = document.getElementById("txtGrant").value;
	//alert("2");
	var txtVillage = document.getElementById("txtVillage").value;
	var txtAddress1 = document.getElementById("txtAddress1").value;
	var txtPin = document.getElementById("txtPin").value;
	var cmbOfficeCityClass = document.getElementById("cmbOfficeCityClass").value;
	var txtTelNo1 = document.getElementById("txtTelNo1").value;
	var txtTelNo2 = document.getElementById("txtTelNo2").value;
	var txtMobileNo = document.getElementById("txtMobileNo").value;
	var txtEmail = document.getElementById("txtEmail").value;
	var txtDdoCode = document.getElementById("txtDdoCode").value;
	var txtdiceCode = document.getElementById("txtdiceCode").value;
	//alert("3");
	
	if(cmbcity == -1)
	{
		alert('Please select City.');
		return false;
	}
	//alert("4");
	
	for(i = 0;i<2;i++)
	{
		if(document.frmDCPSDdoOffice.RadioButtonTriableArea[i].checked)
		{
		var RadioButtonTriableArea = document.frmDCPSDdoOffice.RadioButtonTriableArea[i].value;
		}
	}
	for(i = 0;i<2;i++)
	{
		if(document.frmDCPSDdoOffice.RadioButtonHillyArea[i].checked)
		{
			var RadioButtonHillyArea = document.frmDCPSDdoOffice.RadioButtonHillyArea[i].value;
		}
	}
	for(i = 0;i<2;i++)
	{
		if(document.frmDCPSDdoOffice.RadioButtonNaxaliteArea[i].checked)
		{
			var RadioButtonNaxaliteArea = document.frmDCPSDdoOffice.RadioButtonNaxaliteArea[i].value;
		}
	}
	//alert("5");
	var uri = "ifms.htm?actionFlag=officeUpdt";
	
	var url1 =  "txtDdoCode="+txtDdoCode+"&txtNameOfOffice="+txtNameOfOffice+"&cmbState="+cmbState
				//+"&cmbDist="+cmbDist+"&cmbTaluka="+cmbTaluka+"&txtGrant="+txtGrant+"&txtTown="+txtTown+"&txtVillage="+txtVillage;
				  +"&cmbDist="+cmbDist+"&cmbTaluka="+cmbTaluka+"&txtGrant="+txtGrant+"&txtVillage="+txtVillage+"&cmbcity="+cmbcity;
	var url2 =	"&txtAddress1="+txtAddress1+"&txtPin="+txtPin+"&cmbOfficeCityClass="+cmbOfficeCityClass+"&txtTelNo1="+txtTelNo1
				+"&txtTelNo2="+txtTelNo2+"&txtMobileNo="+txtMobileNo+"&txtEmail="+txtEmail;	
	var url3 =  "&RadioButtonTriableArea="+RadioButtonTriableArea+"&RadioButtonHillyArea="+RadioButtonHillyArea+"&RadioButtonNaxaliteArea="+RadioButtonNaxaliteArea;
	var url4 =  "&saveOrUpdateFlag="+saveOrUpdateFlag;
	var url5 =  "&ddoOfficeId="+ddoOfficeId+"&UpdateFlag=true"+"&txtdiceCode="+txtdiceCode;
	var url = url1 + url2 + url3 + url4 + url5;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForUpdateOffice(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function getDataStateChangedForUpdateOffice(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag= XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(successFlag)
	{
		alert("Office Data Updated Successfully");
		self.location.href="ifms.htm?actionFlag=loadDdoOffice&elementId=700018";
	}
}

function popUpDDOOfficeDetails(DDOOfficeId)
{	
	saveOrUpdateFlag = 2;
	ddoOfficeId = DDOOfficeId ;
	self.location.href="ifms.htm?actionFlag=popUpDdoOfficeDtls&elementId=700018&ddoOfficeId="+ddoOfficeId ;
	
//	document.frmDCPSDdoOffice.action = url;
//	document.frmDCPSDdoOffice.submit();
}


function popUpDDOOfficeDetailsapprove(DDOOfficeId)
{	
	saveOrUpdateFlag = 2;
	ddoOfficeId = DDOOfficeId ;
	var flag = document.getElementById('approve').value;
	
	
	self.location.href="ifms.htm?actionFlag=popUpDdoOfficeDtls&elementId=700018&ddoOfficeId="+ddoOfficeId+"&flag="+flag ;
	
//	document.frmDCPSDdoOffice.action = url;
//	document.frmDCPSDdoOffice.submit();
}

function ApproveDDOOfficeData()
{	//added by ketan 
	
	ddoOfficeId = document.getElementById("officeId").value;
	
	self.location.href="ifms.htm?actionFlag=SaveApproveDdoOffice&ddoOfficeId="+ddoOfficeId;
	alert("Office Datat Approved");

	//self.location.href="ifms.htm?actionFlag=popUpDdoOfficeDtls&elementId=700018&ddoOfficeId="+ddoOfficeId+"&flag="+flag ;
//	document.frmDCPSDdoOffice.action = url;
//	document.frmDCPSDdoOffice.submit();
}

function ValidateOfficeData()
{
	//alert('ValidateOfficeData()');
	if(!chkEmpty(document.getElementById('txtNameOfOffice'),'Name Of Office') || 
		
			!chkEmpty(document.getElementById('cmbState'),'State') || 
			!chkEmpty(document.getElementById('cmbDist'),'District') || 
			!chkEmpty(document.getElementById('cmbTaluka'),'Taluka') || 
			//!chkEmpty(document.getElementById('cmbTown'),'Town') || 
			//!chkEmpty(document.getElementById('txtVillage'),'Village') || 
			
		
			
			!chkEmpty(document.getElementById('txtAddress1'),'Address') || 
			!chkEmpty(document.getElementById('txtPin'),'Pin') || 
			!chkEmpty(document.getElementById('cmbOfficeCityClass'),'Office City Class') || 
			!chkEmpty(document.getElementById('txtTelNo1'),'Tel No') || 
			!chkEmpty(document.getElementById('txtEmail'),'Email') || 
			!chkEmpty(document.getElementById('RadioButtonTriableArea'),'Triable Area') || 
			!chkEmpty(document.getElementById('RadioButtonHillyArea'),'Hilly Area') || 
			!chkEmpty(document.getElementById('RadioButtonNaxaliteArea'),'Naxalite Area')	
	)
	{
		//alert("something wrong!");
		return false;
	}
	else{
		//alert("everythign is fine");
		return true;
	}
}

function ReturnPage()
{
	self.location.href="ifms.htm?actionFlag=validateLogin";
}
function showBlankDDOOffice()
{
	self.location.href="ifms.htm?actionFlag=loadDdoOffice&elementId=700018";
}

function blockpendingData(){
	
	document.getElementById("save").disabled = true
	
}

function ApproveDataBlock(){
	
	document.getElementById("approve").disabled = true
	
}




