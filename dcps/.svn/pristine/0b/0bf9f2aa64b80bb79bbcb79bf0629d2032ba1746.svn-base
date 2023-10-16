var checkPermanentAsNative=document.getElementsByName('radPermanentAddress');
var checkCurrentAsPermanent=document.getElementsByName('radCurrentAddress');

function updateBirthAddress()
{
	if(document.getElementById("birthUpdate") != null)
	{
		if(!(birthPattern.test(updateFlag)))
		{	
			updateFlag=updateFlag+"^"+"BA";
			document.getElementById("birthUpdate").style.display='none';
			document.getElementById("birthReset").style.display='';
			document.getElementById("readOnyBirthPlaceAddressTrId").style.display='none';
			document.getElementById("birthPlaceAddressTrId").style.display='';
			sendAjaxRequestForPopulateAddress('birth');
			makeEnableDisable('birthPlaceAddress',1);
			//showHideFieldGroupElements('fieldGroupAddressbirthPlaceAddress',null);
			getFieldGroupObj(document.getElementById('countryStateTablebirthPlaceAddress'));
		}
	}
}
function resetBirthAddress()
{
	if(document.getElementById("birthReset") != null)
	{
		if((birthPattern.test(updateFlag)))
		{
			updateFlag = updateFlag.replace("^BA","");
			document.getElementById("birthReset").style.display='none';
			document.getElementById("birthUpdate").style.display='';
			document.getElementById("birthPlaceAddressTrId").style.display='none';
			document.getElementById("readOnyBirthPlaceAddressTrId").style.display='';
			makeReadOnly('birthPlaceAddress');
			//showHideFieldGroupElements('fieldGroupAddressbirthPlaceAddressReadOnly',null);
			getFieldGroupObj(document.getElementById('villagetblbirthPlaceAddressReadOnly'));
		}
	}
}

function sendAjaxRequestForPopulateAddress(addressName) 
{
	if(xmlFileName==null || xmlFileName=='')
	 	return false;
	 
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) 
	{
	  alert (AddressDtlsAlertMsgArr[6]);
	  return;
	} 
	var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
	
	if(addressName == 'birth')	
		xmlHttp.onreadystatechange = populateFormBirthUpdate;
	else if(addressName == 'native')
		xmlHttp.onreadystatechange = populateFormNativeUpdate;
	else if(addressName == 'permanent')
		xmlHttp.onreadystatechange = populateFormPermanentUpdate;
	else if(addressName == 'current')
		xmlHttp.onreadystatechange = populateFormCurrentUpdate;
		
	xmlHttp.open("POST",encodeURI(url),false);
	xmlHttp.send(null);
}

function populateFormBirthUpdate()  
{
	if (xmlHttp.readyState == 4) 
	 {
	 	var decXML = xmlHttp.responseText;
		var xmlDOM = getDOMFromXML(decXML);
		var addrXPath = 'cmnAddressMstByEmpBirthPlaceAddressId';
		editAddress('birthPlaceAddress',xmlDOM,addrXPath);	
	}
}

function updateNativeAddress()
{
	if(document.getElementById("nativeUpdate") != null)
	{
		if(!(nativePattern.test(updateFlag)))
		{
			updateFlag=updateFlag+"^"+"NA";
			makeEnableDisable('nativePlaceAddress',1);
			var radiopermanentVar=document.getElementsByName(comp[2]);
			var radiocurrentVar=document.getElementsByName(comp[3]);
			
			if (radiopermanentVar[0] != null && radiopermanentVar[0].checked)
			{
				if(!(permanentPattern.test(updateFlag)))
					updateFlag=updateFlag+"^"+"PA";
			
				if (radiocurrentVar[0] != null && radiocurrentVar[0].checked)
				{
					if(!(currentPattern.test(updateFlag)))
						updateFlag=updateFlag+"^"+"CA";
				}
			}
			
			document.getElementById("nativeUpdate").style.display='none';
			document.getElementById("nativeReset").style.display='';
			document.getElementById("readOnyNativePlaceAddressTrId").style.display='none';
			document.getElementById("nativePlaceAddressTrId").style.display='';
			sendAjaxRequestForPopulateAddress('native');
			getFieldGroupObj(document.getElementById('countryStateTablenativePlaceAddress'));
		}
	}
}
function resetNativeAddress()
{
	if(document.getElementById("nativeReset") != null)
	{
		if((nativePattern.test(updateFlag)))
		{
			updateFlag = updateFlag.replace("^NA","");
				
			var radiopermanentVar=document.getElementsByName(comp[2]);
			var radiocurrentVar=document.getElementsByName(comp[3]);
			
			if (radiopermanentVar[0] != null && radiopermanentVar[0].checked)
			{
				if((permanentPattern.test(updateFlag)))
					updateFlag = updateFlag.replace("^PA","");
			
				if (radiocurrentVar[0] != null && radiocurrentVar[0].checked)
				{
					if((currentPattern.test(updateFlag)))
						updateFlag = updateFlag.replace("^CA","");
				}
			}
			
			document.getElementById("nativeReset").style.display='none';
			document.getElementById("nativeUpdate").style.display='';
			document.getElementById("nativePlaceAddressTrId").style.display='none';
			document.getElementById("readOnyNativePlaceAddressTrId").style.display='';
			makeReadOnly('nativePlaceAddress');
			getFieldGroupObj(document.getElementById('villagetblnativePlaceAddressReadOnly'));
		}
	}
}

function populateFormNativeUpdate()  
{
	if (xmlHttp.readyState == 4) 
	 {
	 	var decXML = xmlHttp.responseText;
		var xmlDOM = getDOMFromXML(decXML);
		var addrXPath = 'cmnAddressMstByEmpNativePlaceAddressId';
		editAddress('nativePlaceAddress',xmlDOM,addrXPath);	
	}
}

function updateCurrentPlaceAddress()
{
	if(document.getElementById("currentUpdate") != null)
	{
		if(!(currentPattern.test(updateFlag)))
		{
			updateFlag=updateFlag+"^"+"CA";
		}
		makeEnableDisable('currentPlaceAddress',1);
		document.getElementById("currentUpdate").style.display='none';
		document.getElementById("currentReset").style.display='';
		document.getElementById("readOnyCurrentPlaceAddressTrId").style.display='none';
		if(temp != 'Y')
		{
			document.getElementById("currentPlaceAddressTrId").style.display='';
			sendAjaxRequestForPopulateAddress('current');
		}		
		document.frmBF.radCurrentAddress[0].disabled = false;
		document.frmBF.radCurrentAddress[1].disabled = false;
		getFieldGroupObj(document.getElementById('countryStateTablecurrentPlaceAddress'));
	}
}
function resetCurrentPlaceAddress()
{
	if(document.getElementById("currentReset") != null)
	{
		if((currentPattern.test(updateFlag)))
		{
			updateFlag = updateFlag.replace("^CA","");
		}	
	
		document.getElementById("currentReset").style.display='none';
		document.getElementById("currentUpdate").style.display='';
		document.getElementById("currentPlaceAddressTrId").style.display='none';
		
		if(temp == 'Y')
		{
			checkCurrentAsPermanent[0].checked="true";
			document.getElementById('readOnyCurrentPlaceAddressTrId').style.display='none';
		}
		else
		{
			checkCurrentAsPermanent[1].checked="true";
			document.getElementById('readOnyCurrentPlaceAddressTrId').style.display='';
		}
		document.frmBF.radCurrentAddress[0].disabled = true;
		document.frmBF.radCurrentAddress[1].disabled = true;
		makeReadOnly('currentPlaceAddress');
		getFieldGroupObj(document.getElementById('villagetblcurrentPlaceAddressReadOnly'));
	}
}

function populateFormCurrentUpdate()  
{
	if (xmlHttp.readyState == 4) 
	 {
	 	var decXML = xmlHttp.responseText;
		var xmlDOM = getDOMFromXML(decXML);
		var addrXPath = 'cmnAddressMstByEmpCurrentAddressId';
		editAddress('currentPlaceAddress',xmlDOM,addrXPath);	
	}
}

function updatePermanentAddress()
{
	if(document.getElementById("permanentUpdate") != null)
	{
		if(!(permanentPattern.test(updateFlag)))
		{
			updateFlag=updateFlag+"^"+"PA";
			var radiocurrentVar=document.getElementsByName(comp[3]);
		
			if (radiocurrentVar[0] != null && radiocurrentVar[0].checked)
			{
				if(!(currentPattern.test(updateFlag)))
					updateFlag=updateFlag+"^"+"CA";
			}
		}
		makeEnableDisable('permanentPlaceAddress',1);
		document.getElementById("permanentUpdate").style.display='none';
		document.getElementById("permanentReset").style.display='';
		document.getElementById("readOnyPermanentAddressTrId").style.display='none';
		
		if(tempPermnent != 'Y')
		{
			document.getElementById("permanentAddressTrId").style.display='';
			sendAjaxRequestForPopulateAddress('permanent');
		}
		document.frmBF.radPermanentAddress[0].disabled = false;
		document.frmBF.radPermanentAddress[1].disabled = false;
		getFieldGroupObj(document.getElementById('countryStateTablepermanentPlaceAddress'));
	}
}
function resetPermanentAddress()
{
	if(document.getElementById("permanentReset") != null)
	{
		if((permanentPattern.test(updateFlag)))
		{
			updateFlag = updateFlag.replace("^PA","");
			
			var radiocurrentVar=document.getElementsByName(comp[3]);
		
			if (radiocurrentVar[0] != null && radiocurrentVar[0].checked)
			{
				if((currentPattern.test(updateFlag)))
					updateFlag = updateFlag.replace("^CA","");
			}
		}
		document.getElementById("permanentReset").style.display='none';
		document.getElementById("permanentUpdate").style.display='';
		document.getElementById("permanentAddressTrId").style.display='none';
		
		if(tempPermnent == 'Y')
		{
			checkPermanentAsNative[0].checked="true";
			document.getElementById('readOnyPermanentAddressTrId').style.display='none';
		}
		else
		{
			checkPermanentAsNative[1].checked="true";
			document.getElementById('readOnyPermanentAddressTrId').style.display='';
		}
		document.frmBF.radPermanentAddress[0].disabled = true;
		document.frmBF.radPermanentAddress[1].disabled = true;
		makeReadOnly('permanentPlaceAddress');
		getFieldGroupObj(document.getElementById('villagetblpermanentAddressReadOnly'));
	}
}

function populateFormPermanentUpdate()  
{
	if (xmlHttp.readyState == 4) 
	 {
	 	var decXML = xmlHttp.responseText;
		var xmlDOM = getDOMFromXML(decXML);
		var addrXPath = 'cmnAddressMstByEmpPermanentAddressId';
		editAddress('permanentPlaceAddress',xmlDOM,addrXPath);	
	}
}


function validateForm()
{
	if (updateFlag != '')
	{
		if(validateAddressStatus())
		{
			var uri = "./hrms.htm?actionFlag=";
			var url = uri + "displayAddress&updateflag="+updateFlag;
			
			document.frmBF.radCurrentAddress[0].disabled = false;
			document.frmBF.radCurrentAddress[1].disabled = false;
			document.frmBF.radPermanentAddress[0].disabled = false;
			document.frmBF.radPermanentAddress[1].disabled = false;
			
			document.frmBF.action = url;
 	 	    document.frmBF.submit();
		}
 	}
	else
	{	
		alert(AddressDtlsAlertMsgArr[1]);
	}
}


function validateAddressStatus()
{
	var radiopermanentVar=document.getElementsByName(comp[2]);
	var radiocurrentVar=document.getElementsByName(comp[3]);
			
	if((birthPattern.test(updateFlag)))
	{
		if(!validateEmpAddress('birthPlaceAddress','Birth Place Address',AddressDtlsAlertMsgArr[2]))
			return false;
	}
	
	if((nativePattern.test(updateFlag)))
	{
		if(!validateEmpAddress('nativePlaceAddress','Native Address',AddressDtlsAlertMsgArr[3]))
			return false;
		
	}
	
	if((permanentPattern.test(updateFlag)) && radiopermanentVar[1] != null && radiopermanentVar[1].checked)
	{
		if(!validateEmpAddress('permanentPlaceAddress','Permanent Address',AddressDtlsAlertMsgArr[4]))
			return false;
	}
	
	if((currentPattern.test(updateFlag)) && radiocurrentVar[1] != null && radiocurrentVar[1].checked)
	{
		if(!validateEmpAddress('currentPlaceAddress','Current Address',AddressDtlsAlertMsgArr[5]))
		return false;
	}
	
	return true;
}

function validateEmpAddress(addrName,addrLookupName,alertMessage)
{
	var statusAddrValidation =  true;

	var addressParameter = addressParameters(addrName,addrLookupName);
				
	if(!isAddressClosed(addrName))				
		statusAddrValidation =  validateSpecificFormFields(addressParameter);		
	else
	{
		alert(alertMessage);
		statusAddrValidation = false;		
	}
	
	return statusAddrValidation;
}


function showHideAddress(addressType,btnValue)
{
	if(addressType==1)
	{
		if(btnValue=='N')
		{
			if(tempPermnent=='Y' || tempPermnent=='y')
			{	
				resetAddress('permanentPlaceAddress');
				closeAddress('permanentPlaceAddress'); 
			}
			
			document.getElementById('permanentAddressTrId').style.display='';
		}
		else
		{
			if(flag1==true)
			{
				flag1=false;
			}
			document.getElementById('permanentAddressTrId').style.display='none';	
		}
	}
	if(addressType==2)
	{
		if(btnValue=='N')
		{
			if(temp=='Y' || temp=='y')
			{	
				resetAddress('currentPlaceAddress');
				closeAddress('currentPlaceAddress'); 
			}
			
			document.getElementById('currentPlaceAddressTrId').style.display='';	
		}
		else
		{
			if(flag2==true)
			{
				flag2=false;
			}
			document.getElementById('currentPlaceAddressTrId').style.display='none';	
		}
	}		
}
	function closeWindow()
	{
		document.frmBF.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	   	document.frmBF.submit();
	}
	
function onChangeAddressFormLoad()
{
	if(tempPermnent == 'Y')
	{
		checkPermanentAsNative[0].checked="true";
		document.getElementById('readOnyPermanentAddressTrId').style.display='none';
	}
	else
	{
		checkPermanentAsNative[1].checked="true";
		document.getElementById('readOnyPermanentAddressTrId').style.display='';
	}
	
	if(temp == 'Y')
	{
		checkCurrentAsPermanent[0].checked="true";
		document.getElementById('readOnyCurrentPlaceAddressTrId').style.display='none';
		
	}
	else
	{
		checkCurrentAsPermanent[1].checked="true";
		document.getElementById('readOnyCurrentPlaceAddressTrId').style.display='';
	}

	document.frmBF.radCurrentAddress[0].disabled = true;
	document.frmBF.radCurrentAddress[1].disabled = true;
	
	document.frmBF.radPermanentAddress[0].disabled = true;
	document.frmBF.radPermanentAddress[1].disabled = true;
	
	document.getElementById("birthReset").style.display='none';
	document.getElementById("nativeReset").style.display='none';
	document.getElementById("permanentReset").style.display='none';
	document.getElementById("currentReset").style.display='none';
}	