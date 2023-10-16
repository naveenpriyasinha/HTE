var counter =1;
var assetIdArr = new Array();
function selectno(l)
{


	var id = l.value;
	
	
	if(id == '')
				{
					return;
				}
	
	if((id == 'Car_asset' || id == 'Scooter_asset' || id == 'Bike_asset') && document.form1.Purpose[0].checked == true)
	{
	document.getElementById('Registrationno').style.display="";	
	document.getElementById('Registrationno1').style.display="";		
	
	}
	else
	{
	document.getElementById('Registrationno').style.display="none";	
	document.getElementById('Registrationno1').style.display="none";	
	
	}
	
}

function joiningSelectNo(l)
{
	var id = l.value;
	
	
	if(id == '')
				{
					return;
				}
	
	if((id == 'Car_asset' || id == 'Scooter_asset' || id == 'Bike_asset'))
	{
	document.getElementById('Registrationno').style.display="";	
	document.getElementById('Registrationno1').style.display="";		
	
	}
	else
	{
	document.getElementById('Registrationno').style.display="none";	
	document.getElementById('Registrationno1').style.display="none";	
	
	}
}
function adminRegNo(l)
{


	var id = l.value;
	
	
	if(id == '')
				{
					return;
				}
	
	if((id == 'Car_asset' || id == 'Scooter_asset' || id == 'Bike_asset'))
	{
	document.getElementById('adminRegistrationno').style.display="";	
	document.getElementById('adminRegistrationno1').style.display="";		
	
	}
	else
	{
	document.getElementById('adminRegistrationno').style.display="none";	
	document.getElementById('adminRegistrationno1').style.display="none";	
	
	}
	
}

function selectother(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == 'Other_fixed')
	{
	document.getElementById('Name_other').style.display="";	
	}
	else
	{
	document.getElementById('Name_other').style.display="none";	
	}
}

function selectother1(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}	
	if(id == 'Other_movable')
	{
	document.getElementById('Name_other1').style.display="";	
	}
	else
	{
	document.getElementById('Name_other1').style.display="none";	
	}
}

function adminOtherMovableAsset(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}	
	if(id == 'Other_movable')
	{
	document.getElementById('admin_Name_other').style.display="";	
	}
	else
	{
	document.getElementById('admin_Name_other').style.display="none";	
	}
}

function selectothermode(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}	
	
	if(id == 'Other_mode')
	{
	document.getElementById('Name_other2').style.display="";	
	}
	else
	{
	document.getElementById('Name_other2').style.display="none";	
	}
}

function selectothermedium(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}	
	
	if(id == 'Other_medium')
	{
	document.getElementById('Name_other3').style.display="";	
	}
	else
	{
	document.getElementById('Name_other3').style.display="none";	
	}
}

function selectpursell(l)
{


	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == 'Gift' && document.form1.Purpose[0].checked == true)
	{
	document.getElementById('permission3').style.display="";	
	document.getElementById('permission4').style.display="";		
	
	}
	else
	{
	document.getElementById('permission3').style.display="none";	
	document.getElementById('permission4').style.display="none";	
	document.getElementById('permission1').style.display="none";	
	document.getElementById('permission2').style.display="none";
	
	}


}

function srcofpayment(l)
{

	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == 'Others_src')
	{
	document.getElementById('other').style.display="";	
	document.getElementById('other1').style.display="";	
	
	}
	else
	{
	document.getElementById('other').style.display="none";	
	document.getElementById('other1').style.display="none";
	
	}

}

function permissionyes()
{
	document.getElementById('attach').style.display="";
	document.getElementById('attach1').style.display="";
	document.getElementById('textarea').style.display="none";
	document.getElementById('textarea1').style.display="none";

}

function permissionno()
{
	document.getElementById('textarea').style.display="";
	document.getElementById('textarea1').style.display="";
	document.getElementById('attach').style.display="none";
	document.getElementById('attach1').style.display="none";
}


function relationwithemp()
{
	document.getElementById('relation').style.display="";
	document.getElementById('relation1').style.display="";
	document.getElementById('NoFamilyDtl').style.display="";
	if(document.form1.specifydtls.disabled)
	{
		document.form1.Submit.disabled=true;
	}
	
}
function editRelationwithemp()
{	
	document.form1.editSpecifydtls.disabled = false;
	document.form1.editfirstname.disabled=true;
	document.form1.editmiddlename.disabled=true;
	document.form1.editlastname.disabled=true;
	document.getElementById('relation4').style.display="";
	document.getElementById('relation5').style.display="";
	//document.getElementById('EditNoFamilyDtl').style.display="";
	/*if(document.form1.editSpecifydtls.disabled)
	{
		document.form1.Update.disabled=true;
	}*/
}

function relationwithempno()
{
	makeEnableDisable("GuardianAddress",1);
	resetAddress("GuardianAddress");
	document.form1.firstname.readOnly = false;
	document.form1.middlename.readOnly = false;
	document.form1.lastname.readOnly = false;
	document.form1.firstname.value = "";
	document.form1.middlename.value = "";
	document.form1.lastname.value = "";
	document.getElementById('relation').style.display="none";
	document.getElementById('relation1').style.display="none";
	document.getElementById('NoFamilyDtl').style.display="none";
	document.form1.Submit.disabled=false;
}

function editRelationwithempno()
{
	makeEnableDisable("EditPartyAddress",1);
	resetAddress("EditPartyAddress");
	document.form1.editfirstname.readOnly = false;
	document.form1.editmiddlename.readOnly = false;
	document.form1.editlastname.readOnly = false;
	document.form1.editfirstname.disabled=false;
	document.form1.editmiddlename.disabled=false;
	document.form1.editlastname.disabled=false;
	document.form1.editfirstname.value = "";
	document.form1.editmiddlename.value = "";
	document.form1.editlastname.value = "";
	document.getElementById('relation4').style.display="none";
	document.getElementById('relation5').style.display="none";
	//document.getElementById('EditNoFamilyDtl').style.display="none";
//	document.form1.Update.disabled=false;
	
}
function relationempwithowneryes()
{
	/*makeEnableDisable("OwnerAddress",0);  */
	document.getElementById('ownerrelation').style.display="";
	document.getElementById('ownerrelation1').style.display="";
	document.getElementById('NoFamilyDtlForOwner').style.display="";
	if(document.form1.ownerrelation.disabled)
	{
		document.form1.Submit.disabled=true;
	}
}

function relationempwithownerno()
{
	makeEnableDisable("OwnerJoiningAddress",1);
	document.form1.firstname1.readOnly = false;
	document.form1.middlename1.readOnly = false;
	document.form1.lastname1.readOnly = false;
	document.form1.firstname1.value = "";
	document.form1.middlename1.value = "";
	document.form1.lastname1.value = "";
	resetAddress("OwnerJoiningAddress");
	document.getElementById('ownerrelation').style.display="none";
	document.getElementById('ownerrelation1').style.display="none";
	document.getElementById('NoFamilyDtlForOwner').style.display="none";
	document.form1.Submit.disabled=false;
}
function editRelationempwithowneryes()
{
	/*makeEnableDisable("OwnerAddress",0);  */
	document.getElementById('ownerrelation4').style.display="";
	document.getElementById('ownerrelation5').style.display="";
	//document.getElementById('EditNoFamilyOwnerDtl').style.display="";
	/*if(document.form1.editownerrelation2.disabled)
	{
		document.form1.Update.disabled=true;
	}*/
}
function editRelationempwithownerno()
{
	makeEnableDisable("changedOwnerAddress",1);
	resetAddress("changedOwnerAddress");
	document.form1.editFirstname1.readOnly = false;
	document.form1.editMiddlename1.readOnly = false;
	document.form1.editLastname1.readOnly = false;
	document.form1.editFirstname1.disabled=false;
	document.form1.editMiddlename1.disabled=false;
	document.form1.editLastname1.disabled=false;
	document.form1.editFirstname1.value = "";
	document.form1.editMiddlename1.value = "";
	document.form1.editLastname1.value = "";
	document.getElementById('ownerrelation4').style.display="none";
	document.getElementById('ownerrelation5').style.display="none";
	//document.getElementById('EditNoFamilyOwnerDtl').style.display="none";
	//document.form1.Update.disabled=false;
}
function permissionobtainedyes()
{
	document.getElementById('permission1').style.display="none";
	document.getElementById('permission2').style.display="none";
}


function futurerelationyes()
{
	document.getElementById('workdetail').style.display="";
	document.getElementById('workdetail1').style.display="";
}

function futurerelationno()
{
	document.getElementById('workdetail').style.display="none";
	document.getElementById('workdetail1').style.display="none";
}

function permissionobtainedno()
{
	document.getElementById('permission1').style.display="";
	document.getElementById('permission2').style.display="";
}


function typeasset(l)
{

	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	
	
	if(id == 'Fixed_asset')
	{
		document.form1.assettype.checked=false;
		document.getElementById('movable').style.display="none";
		document.getElementById('fixed').style.display="";
		document.getElementById('addressofasset').style.display="";
		document.getElementById('property').value=0;
		document.getElementById('other_movable').value="";
		document.getElementById('registration').value="";
		
	}
	if(id == 'Movable_asset')
	{
		document.form1.assettype.checked=false;
		document.getElementById('movable').style.display="";
		document.getElementById('fixed').style.display="none";
		document.getElementById('addressofasset').style.display="none";
		document.getElementById('asset').value=0;
		document.getElementById('other_fixed').value="";
		resetAddress("Address");
	}
	
	
}


function billdetails(l)
{

	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == 'Purchase_asset' && document.form1.Purpose[0].checked == true)
	{
	
	 	document.getElementById('billdtls').style.display="";
		document.getElementById('billdetails').style.display="";
		document.getElementById('billattachment').style.display="";
		document.getElementById('attachbill').style.display="";
		document.getElementById('attachbill1').style.display="";
	}

	
	 if(id == 'sale_asset' || document.form1.Purpose[1].checked == true)
	{
		
	 	document.getElementById('billdtls').style.display="none";
		document.getElementById('billdetails').style.display="none";
		document.getElementById('billattachment').style.display="none";
		document.getElementById('attachbill').style.display="none";
		document.getElementById('attachbill1').style.display="none";
	}
}

function adminBilldetails(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == 'admin_purchased')
	{
	
	 	document.getElementById('billdtls').style.display="";
		document.getElementById('billdetails').style.display="";
		document.getElementById('billattachment').style.display="";
		document.getElementById('attachbill').style.display="";
		document.getElementById('attachbill1').style.display="";
	}

	
	 if(id == 'admin_sold')
	{
		
	 	document.getElementById('billdtls').style.display="none";
		document.getElementById('billdetails').style.display="none";
		document.getElementById('billattachment').style.display="none";
		document.getElementById('attachbill').style.display="none";
		document.getElementById('attachbill1').style.display="none";
	}
}


function partyname(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == 'Person')
	{
		document.getElementById('partydetails').style.display="";
		document.getElementById('companydetails').style.display="none";
	}
	
	if(id == 'Organization')
	{
		
		document.getElementById('partydetails').style.display="none";
		document.getElementById('companydetails').style.display="";
		document.getElementById('NoFamilyDtl').style.display="none";
	}
}

function editPartyname(l)
{
	var id = l.value;
	//alert(id);
	if(id == '')
				{
					return;
				}
	if(id == 'Person')
	{
		document.getElementById('changePartydetails').style.display="";
		document.getElementById('changeCompanydetails').style.display="none";
	}
	
	if(id == 'Organization')
	{
		document.form1.editfirstname.disabled=false;
		document.form1.editmiddlename.disabled=false;
		document.form1.editlastname.disabled=false;
		makeEnableDisable("EditPartyAddress",1);
		document.getElementById('changePartydetails').style.display="none";
		document.getElementById('changeCompanydetails').style.display="";
	}
}
function ownername(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == 'Asset_self')
	{
		document.getElementById('ownerName').style.display="none";
		document.getElementById('OwnerAddress').style.display="none";
		document.getElementById('ownerdetails').style.display="none";
		document.getElementById('NoFamilyDtlForOwner').style.display="none";
	}
	
	if(id == 'Asset_other')
	{
		
		document.getElementById('ownerName').style.display="";
		document.getElementById('OwnerAddress').style.display="";
		document.getElementById('ownerdetails').style.display="";
	}
}
function editOwnername(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == 'Asset_self')
	{
		document.getElementById('editownerdetails').style.display="none";
		document.getElementById('chngeownerName').style.display="none";
		document.getElementById('chngeOwnerAddress').style.display="none";
	}
	
	if(id == 'Asset_other')
	{
		
		document.getElementById('editownerdetails').style.display="";
		document.getElementById('chngeownerName').style.display="";
		document.getElementById('chngeOwnerAddress').style.display="";
	}
}
function tranxnTypeValidation(l)
{
	
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == 'Purchase_asset')
	{
		var purchaseArray = new Array('assettype');
		var statusPurchaseValidation =  validateSpecificFormFields(purchaseArray);
	}
	if(id == 'sale_asset')
	{
		var propertyArray = new Array('property');
		var propertyValidation =  validateSpecificFormFields(propertyArray);
	}
}


function partyValidation(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == 'Person')
	{
		var PersonArray = new Array('relationemp');
		var PersonValidation =  validateSpecificFormFields(PersonArray);
	}
	if(id == 'Organization')
	{
		var OrganizationArray = new Array('companyname');
		var OrganizationValidation =  validateSpecificFormFields(OrganizationArray);
	}
}
 
function assetRequestValidations()
{
	 	  
		
	/*	var purchaseSaleTypeArray = new Array('purchasesale');
		var statusPurchaseSaleValidation =  validateSpecificFormFields(purchaseSaleTypeArray);
			
		if(statusPurchaseSaleValidation)
		{
			
			if(document.form1.purchasesale.value == 'Purchase_asset')
			{
				var purchaseArray = new Array('assettype');
				var statusPurchaseValidation =  validateSpecificFormFields(purchaseArray);
				if(statusPurchaseValidation)
				{
					if(document.form1.assettype.value == 'Movable_asset')
					{
						var propertyArray = new Array('property');
						var propertyValidation =  validateSpecificFormFields(propertyArray);
					}
					if(document.form1.assettype.value == 'Fixed_asset')
					{	
						var propertyArray = new Array('asset');
						var propertyValidation =  validateSpecificFormFields(propertyArray);
						if(propertyValidation)
						{
							alert(checkVillageValidation("Address"));
							if(checkVillageValidation('Address') == false && checkCityValidation('Address') == false)
							{
								alert('Address');
							}
						}	
					}
						
						
					
				}
			}
			if(document.form1.purchasesale.value == 'sale_asset')
			{
				var propertyArray = new Array('property');
				var propertyValidation =  validateSpecificFormFields(propertyArray);
			}
			
			
		} */
		
	     
			
		
	

}
function closewindow()
	{
	
		
		var urlstyle="hdiits.htm?actionFlag=getHomePage"
		document.form1.action=urlstyle;
		document.form1.submit(); 
	}

function submitForm_Submit(formNameValue)
{
	var validData = validateForm_form1();
	if( validData==true )
	{
		//window.document.forms[formNameValue].submit();
		submitAllDtls();
		
	}
	endProcess();
}


function editRequestedData(l,rowId)
{
	
	var editAssetId = l;
	var rowId = rowId;
	document.form1.editPermissionTableRowId.value = rowId;
	if(editAssetId == '')
				{
					return;
				}
				
	try{   
    			xmlHttp=new XMLHttpRequest();	    	    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
        		  		}
				      	catch (e)
				      	{
       	   	  	           	  
			            	  return false;        
			      		}
			 	}
        	}			
	//alert(document.form1.counter.value);
	/*var urlstyle="hdiits.htm?actionFlag=getAssetDataForEdit&EditAssetId="+editAssetId
	document.form1.action=urlstyle;
	document.form1.submit(); */
	
	var url = "hdiits.htm?actionFlag=getAssetDataForEdit&EditAssetId="+editAssetId;    
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = displyAllEditData12;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}	
function displyAllEditData12()
{	
	if (xmlHttp.readyState == 4) 
			{    // alert(xmlHttp.status);
				if (xmlHttp.status == 200) 
				{	
					displayNoneForAllEditData();
					
					//alert('displyAllEditData');
					var xmlStr = xmlHttp.responseText;//alert(xmlStr);
					var XMLDoc=getDOMFromXML(xmlStr);
					var editAssetId = XMLDoc.getElementsByTagName('EditAssetId'); 
					document.getElementById("editAssetId").value = editAssetId[0].childNodes[0].text;
					var editTranxnSrNo = XMLDoc.getElementsByTagName('EditTranxnSrNo'); 
					document.getElementById("editTranxnSrNo").value = editTranxnSrNo[0].childNodes[0].text;
					var editPermissionSrNo = XMLDoc.getElementsByTagName('EditPermissionSrNo'); 
					document.getElementById("editPermissionSrNo").value = editPermissionSrNo[0].childNodes[0].text;
					var assetTypeLookupName = XMLDoc.getElementsByTagName('AssetTypeLookupName'); 
					var tranxnTypeLookupName = XMLDoc.getElementsByTagName('TranxnTypeLookupName');
			/*		var editPartyAddressId = XMLDoc.getElementsByTagName('PartyAddressId'); 
					document.getElementById("editPartyAddressId").value = editPartyAddressId[0].childNodes[0].text; */
					
					
					var requestType=XMLDoc.getElementsByTagName('RequestType'); 
					var transactionType=XMLDoc.getElementsByTagName('TranxnType'); 
					var typeOfAsset=XMLDoc.getElementsByTagName('AssetType'); 
					var assetName=XMLDoc.getElementsByTagName('AssetName');
					var tranxnDate=XMLDoc.getElementsByTagName('TranxnDate'); 
					var tranxnPrice=XMLDoc.getElementsByTagName('TranxnPrice');
					var partyType=XMLDoc.getElementsByTagName('PartyType'); 
					
					document.getElementById("editTypeOfParty").value = partyType[0].childNodes[0].text;
					document.getElementById('changeTypeOfParty').style.display="";
					document.form1.editTypeOfParty.disabled=true;
				
					if((partyType[0].childNodes[0].text) == 'Person')
					{
						var partyRealation=XMLDoc.getElementsByTagName('PartyRelation');
						//alert(partyRealation[0].childNodes[0].text);
						if((partyRealation[0].childNodes[0].text) == 'Y')
						{	
							
							
							document.getElementById("editRelationemp2").value = partyRealation[0].childNodes[0].text;
						//	document.getElementById('changePartydetails').style.display="";
							document.form1.editRelationemp2[0].checked = true;
							document.form1.editRelationemp2[0].disabled=true; 
							document.form1.editRelationemp2[1].disabled=true; 
							var partyRealationType = XMLDoc.getElementsByTagName('FamilyRelation');
							var partyFirstName = XMLDoc.getElementsByTagName('PartyFirstName');
							document.getElementById("editSpecifydtls").value = partyRealationType[0].childNodes[0].text+" ("+partyFirstName[0].childNodes[0].text+")";
							document.form1.editSpecifydtls.disabled = true;
							
						//	document.getElementById('relation4').style.display="";
						//	document.getElementById('relation5').style.display="";
							
							
							
							
						}
						else if((partyRealation[0].childNodes[0].text) == 'N')
						{
							//document.getElementById("editIsPartyRelationNo").value = partyRealation[0].childNodes[0].text;
							//document.form1.editIsPartyRelationNo.disabled=true; 
							//document.getElementById('changePartydetails').style.display="";
							document.form1.editRelationemp2[1].checked = true; 
							document.form1.editRelationemp2[0].disabled=true;
							document.form1.editRelationemp2[1].disabled=true;
						//	document.getElementById('editPersonNoRelationParty').style.display="";
							
						}
					}
					
					if((partyType[0].childNodes[0].text) == 'Organization')
					{	
						var partyRealation=XMLDoc.getElementsByTagName('OrganizationName');
						document.getElementById("editCompanyname1").value = partyRealation[0].childNodes[0].text;
						document.form1.editCompanyname1.disabled=true;  
						//document.getElementById('changeCompanydetails').style.display="";
						
					}
					
					var partyFirstName = XMLDoc.getElementsByTagName('PartyFirstName');
					document.getElementById("editfirstname").value = partyFirstName[0].childNodes[0].text;
					document.form1.editfirstname.disabled=true;
					
				/*	var partyMiddleName = XMLDoc.getElementsByTagName('PartyMiddleName');
					alert(partyMiddleName);
					document.getElementById("editmiddlename").value = partyMiddleName[0].childNodes[0].text;*/
					
					str=getXPathValueFromDOM(XMLDoc, 'PartyMiddleName');
					if(str==null  || str==''){str='';}
					document.forms[0].editmiddlename.value=str;
					
					var partyLastName = XMLDoc.getElementsByTagName('PartyLastName');
					document.getElementById("editlastname").value = partyLastName[0].childNodes[0].text;
					//document.getElementById('changePartyName').style.display="";
					document.form1.editlastname.disabled=true;
				
					if(document.form1.editTypeOfParty.value == 'Person')
					{
						document.getElementById('changeTypeOfParty').style.display="";
						document.getElementById('changePartydetails').style.display="";
						if(document.form1.editRelationemp2[0].checked == true)
						{
							document.getElementById('relation4').style.display="";
							document.getElementById('relation5').style.display="";
						}
					}
					if(document.form1.editTypeOfParty.value == 'Organization')
					{
							document.getElementById('changeCompanydetails').style.display="";
					}
	
					document.getElementById('changePartyName').style.display="";
					
					
					  
					if((assetTypeLookupName[0].childNodes[0].text) == 'Movable_asset')
					{	
						if((tranxnTypeLookupName[0].childNodes[0].text) == "sale_asset" && (assetName[0].childNodes[0].text == 'Car' || assetName[0].childNodes[0].text == 'Scooter' || assetName[0].childNodes[0].text == 'Bike' ))
						{
							var registrationNo=XMLDoc.getElementsByTagName('RegistrationNo');
							document.getElementById("editRegiNo").value = registrationNo[0].childNodes[0].text;
							document.getElementById('editRegNo').style.display="";
						}
						else if(assetName[0].childNodes[0].text == 'Car' || assetName[0].childNodes[0].text == 'Scooter' || assetName[0].childNodes[0].text == 'Bike')
						{
							document.getElementById('editRegNo').style.display="";
							document.getElementById("editRegiNo").value = "";
						}
					}
					//alert(typeOfAsset[0].childNodes[0].text);
					
					if((tranxnTypeLookupName[0].childNodes[0].text) == 'Purchase_asset')
			    	{	
			    		document.getElementById('editOwnerLable').style.display="";
			    		var editOwnerSrNo = XMLDoc.getElementsByTagName('EditOwnerSrNo'); 
						document.getElementById("editOwnerSrNo").value = editOwnerSrNo[0].childNodes[0].text;
						
						document.getElementById('editTypeOfOwner').style.display="";
						var editOwnerType = XMLDoc.getElementsByTagName('EditOwnerType');
						//alert(editOwnerType[0].childNodes[0].text);
						document.getElementById("editAssetTypeOfOwner").value = editOwnerType[0].childNodes[0].text;
						document.form1.editAssetTypeOfOwner.disabled=true;  
						
						
						
						if((editOwnerType[0].childNodes[0].text) == 'Asset_other')
						{
							document.getElementById('editownerdetails').style.display="";
							var isRelationWithOwner = XMLDoc.getElementsByTagName('IsRelationWithOwner');
							
							var editOwnerName = XMLDoc.getElementsByTagName('OwnerFirstName');
							
						//	document.form1.editrelationempwithowner2[0].disabled=true;
						//	document.form1.editrelationempwithowner2[1].disabled=true;
							
							if((isRelationWithOwner[0].childNodes[0].text) == 'Y')
							{
								document.form1.editrelationempwithowner2[0].checked = true;
								document.form1.editrelationempwithowner2[0].disabled=true;
								document.form1.editrelationempwithowner2[1].disabled=true;
							
								document.getElementById('ownerrelation4').style.display="";
								document.getElementById('ownerrelation5').style.display="";
								var editRelationWithOwner = XMLDoc.getElementsByTagName('RelationTypeWithOwner');
								document.getElementById("editownerrelation2").value = editRelationWithOwner[0].childNodes[0].text+" ("+editOwnerName[0].childNodes[0].text+")";
								document.form1.editownerrelation2.disabled=true;
								
								
							}
							if((isRelationWithOwner[0].childNodes[0].text) == 'N')
							{
								document.form1.editrelationempwithowner2[1].checked = true;
								document.form1.editrelationempwithowner2[0].disabled=true;
								document.form1.editrelationempwithowner2[1].disabled=true;
							}
							
							document.getElementById('chngeownerName').style.display="";
							document.getElementById("editFirstname1").value = editOwnerName[0].childNodes[0].text;
							document.form1.editFirstname1.disabled=true;
							
						/*	var editOwnerName = XMLDoc.getElementsByTagName('OwnerMiddleName');
							document.getElementById("editMiddlename1").value = editOwnerName[0].childNodes[0].text;
							document.form1.editMiddlename1.disabled=true;    */
							
							var editOwnerName = XMLDoc.getElementsByTagName('OwnerLastName');
							document.getElementById("editLastname1").value = editOwnerName[0].childNodes[0].text;
							document.form1.editLastname1.disabled=true;
							
							var editOwnerAddressId = XMLDoc.getElementsByTagName('OwnerAddressId');
							document.getElementById("editOwnerAddressId").value = editOwnerAddressId[0].childNodes[0].text;
							
							
							 
						}
						document.getElementById('changeOwnerDtls').style.display="";
			    	}     
					
						
						
						xmlFileName1=XMLDoc.getElementsByTagName('AssetAddress');
						xmlFileName2=XMLDoc.getElementsByTagName('PartyAddress');
						
						
			    	    xmlFileName=xmlFileName1[0].childNodes[0].text;		
			    	    xmlFileName4=xmlFileName2[0].childNodes[0].text;
			    	   
			    	 //   alert(editOwnerType[0].childNodes[0].text);
			    	 
			    	    
						//alert(xmlFileName4);
						try{   
		    			xmlHttp=new XMLHttpRequest();	    	    
			    	}
					catch (e)
					{    // Internet Explorer    
							try{
		      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
		      				}
				    		catch (e){
				          		try
		        		  		{
		                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
		        		  		}
						      	catch (e)
						      	{
		       	   	  	           	 
					            	  return false;        
					      		}
					 		}			 
		        	}	
		        	
		        	if((assetTypeLookupName[0].childNodes[0].text) == 'Fixed_asset')
					{	//alert('if');
						
						var editAssetAddressId = XMLDoc.getElementsByTagName('AssetAddressId'); 
						document.getElementById("editAssetAddressId").value = editAssetAddressId[0].childNodes[0].text;
						
						var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
						xmlHttp.open("POST", encodeURI(url) , false);			
						xmlHttp.onreadystatechange = showAddressOfAsset;  
						xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
						xmlHttp.send(encodeURIComponent(null));	 
					}	
				//	alert((typeOfAsset[0].childNodes[0].text));
					else if((assetTypeLookupName[0].childNodes[0].text) == 'Movable_asset')
					{	//alert('movable');
						var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName4;
						xmlHttp.open("POST", encodeURI(url) , false);			
						xmlHttp.onreadystatechange = showPartyAddressDtlsForMovable;  
						xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
						xmlHttp.send(encodeURIComponent(null));	 
					}
					
					
			/*		else if((transactionType[0].childNodes[0].text) == 'Purchase')
			    	 {
			    	    if((editOwnerType[0].childNodes[0].text) == 'Asset_other')
			    	    {
			    	    
			    	    	var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName5;
							xmlHttp.open("POST", encodeURI(url) , true);			
							xmlHttp.onreadystatechange = showOwnerAddressDtls;  
							xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
							xmlHttp.send(encodeURIComponent(null));	 
						}	
					}    */
					
					
					
					if((tranxnTypeLookupName[0].childNodes[0].text) == 'Purchase_asset')
			    	 {
			    	    if((editOwnerType[0].childNodes[0].text) == 'Asset_other')
			    	    {
			    	    	xmlFileName3=XMLDoc.getElementsByTagName('OwnerAddress');
			    	    	xmlFileName5=xmlFileName3[0].childNodes[0].text;
			    	    	ownerAddressDisplay(xmlFileName5);
			    	    }
			    	 }  
					
					
				
					
					//alert(assetName[0].childNodes[0].text);
					
					
					document.getElementById('editRequestType').value = requestType[0].childNodes[0].text;
					//document.form1.editRequestType.readOnly = true; 
					document.form1.editRequestType.disabled=true;     
					document.getElementById("editTranxnType").value = transactionType[0].childNodes[0].text;
					document.getElementById("tranxnTypeForEditOwnerDtls").value = tranxnTypeLookupName[0].childNodes[0].text;
					document.form1.editTranxnType.disabled=true;    
					document.getElementById("editAssetType").value = typeOfAsset[0].childNodes[0].text;
					document.form1.editAssetType.disabled=true;    
					document.getElementById("editAssetName").value = assetName[0].childNodes[0].text;
					document.form1.editAssetName.disabled=true; 
				
					document.getElementById("editTranxnDate").value = tranxnDate[0].childNodes[0].text;
					document.getElementById("editTranxnPrice").value = tranxnPrice[0].childNodes[0].text;
				//	document.getElementById("editPartyType").value = partyType[0].childNodes[0].text;
					
							
				/*	var relativePartyaddress = XMLDoc.getElementsByTagName('PartyAddress');
					alert(relativePartyaddress[0].childNodes[0].text);
					document.getElementById("editPartyAddress1").value = relativePartyaddress[0].childNodes[0].text;
					document.form1.editPartyAddress1.disabled=true;  */
					
			    	
			    	
					
					
					document.getElementById('LineForEdit').style.display="";
					document.getElementById('LableForEdit').style.display="";
					document.getElementById('DataForEdit').style.display="";
					document.getElementById('tranxnLable').style.display="";
					document.getElementById('TranxnDataForEdit').style.display="";
					document.getElementById('editPartyLable').style.display="";
			
					document.getElementById('saveEditData').style.display="";
			
					document.getElementById('changePartyDtls').style.display="";
					
				
					
				}
			}
}

function showAddressOfAsset()
{
	//alert(xmlHttp.readyState);
		if (xmlHttp.readyState == 4) 
			{  //  alert(xmlHttp.status);	
				if (xmlHttp.status == 200) 
				{	document.getElementById('editAssetAddress').style.display="";					
					var xmlStr = xmlHttp.responseText;
					//alert(xmlStr);
			    	var XMLDoc=getDOMFromXML(xmlStr);	
			    			
			    	var addrXPath = 'cmnAddressMst';
					editAddress('AssetAddress',XMLDoc,addrXPath);
					
					
				//alert(xmlFileName);
						try{   
		    			xmlHttp=new XMLHttpRequest();	    	    
			    	}
					catch (e)
					{    // Internet Explorer    
							try{
		      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
		      				}
				    		catch (e){
				          		try
		        		  		{
		                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
		        		  		}
						      	catch (e)
						      	{
		       	   	  	           	 
					            	  return false;        
					      		}
					 		}			 
		        	}	
					var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName4;   
					xmlHttp.open("POST", encodeURI(url) , false);			
					xmlHttp.onreadystatechange = showPartyAddressDtls;
					xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
					xmlHttp.send(encodeURIComponent(null));	
					} 
					
											
			}    
}

function showPartyAddressDtls()
{		//alert('showPartyAddressDtls');	
	//alert(xmlHttp.readyState);
	if (xmlHttp.readyState == 4) 
			{     	//alert(xmlHttp.status);	
				if (xmlHttp.status == 200) 
				{	
				
				    document.getElementById('changePartyAddress').style.display="";				
					var xmlStr = xmlHttp.responseText;
				//	alert(xmlStr);
			    	var XMLDoc=getDOMFromXML(xmlStr);	
			    	   	
			    	var addrXPath = 'cmnAddressMst';
					editAddress('EditPartyAddress',XMLDoc,addrXPath);
					makeEnableDisable("EditPartyAddress",0);
				}							
			}   
}   

function showPartyAddressDtlsForMovable()
{	//alert('showPartyAddressDtlsForMovable');
	if (xmlHttp.readyState == 4) 
			{     		
				if (xmlHttp.status == 200) 
				{	
					document.getElementById('changePartyAddress').style.display="";					
					var xmlStr = xmlHttp.responseText;
					//alert(xmlStr);
			    	var XMLDoc=getDOMFromXML(xmlStr);	
			    	   	
			    	var addrXPath = 'cmnAddressMst';
					editAddress('EditPartyAddress',XMLDoc,addrXPath);
					makeEnableDisable("EditPartyAddress",0);
					
					
						
						
		        	
						
				
					
				}							
			}  
}

function showOwnerAddressDtls()
{
	if (xmlHttp.readyState == 4) 
			{     		
				if (xmlHttp.status == 200) 
				{	
					document.getElementById('chngeOwnerAddress').style.display="";					
					var xmlStr = xmlHttp.responseText;
					//alert(xmlStr);
			    	var XMLDoc=getDOMFromXML(xmlStr);	
			    	   	
			    	var addrXPath = 'cmnAddressMst';
					editAddress('changedOwnerAddress',XMLDoc,addrXPath);
					makeEnableDisable("changedOwnerAddress",0);
				}							
			} 
}

function changePartyDtlsYes()
{
	document.form1.editTypeOfParty.disabled=false;
	if(document.form1.editTypeOfParty.value == 'Person')
	{
		document.form1.editRelationemp2[0].disabled=false; 
		document.form1.editRelationemp2[1].disabled=false; 
		if(document.form1.editRelationemp2[0].checked == true)
		{
			document.form1.editSpecifydtls.disabled = false;
			
			
		}
	}
	if(document.form1.editTypeOfParty.value == 'Organization')
	{
		document.form1.editCompanyname1.disabled=false;
	}
	
		
		
	if(document.form1.editRelationemp2[1].checked == true || document.form1.editTypeOfParty.value == 'Organization')
	{
		makeEnableDisable("EditPartyAddress",1);
		document.form1.editfirstname.disabled=false;
		document.form1.editmiddlename.disabled=false;
		document.form1.editlastname.disabled=false;
	}	
	else
	{
		document.form1.editfirstname.disabled=true;
		document.form1.editmiddlename.disabled=true;
		document.form1.editlastname.disabled=true;
	}
}

function changePartyDtlsNo()
{
	document.form1.editTypeOfParty.disabled=true;
	if(document.form1.editTypeOfParty.value == 'Person')
	{
		document.form1.editRelationemp2[0].disabled=true; 
		document.form1.editRelationemp2[1].disabled=true; 
		if(document.form1.editRelationemp2[0].checked == true)
		{
			document.form1.editSpecifydtls.disabled = true;
		}
	}
	if(document.form1.editTypeOfParty.value == 'Organization')
	{
		document.form1.editCompanyname1.disabled=true;
	}
	
		document.form1.editfirstname.disabled=true;
		document.form1.editmiddlename.disabled=true;
		document.form1.editlastname.disabled=true;
		
	if(document.form1.editRelationemp2[1].checked == true || document.form1.editTypeOfParty.value == 'Organization')
	{
		makeEnableDisable("EditPartyAddress",0);
	}	
}
function changeOwnerDtlsYes()
{
	//alert(document.form1.editAssetTypeOfOwner.value);
	document.form1.editAssetTypeOfOwner.disabled=false;
	if(document.form1.editAssetTypeOfOwner.value == 'Asset_other')
	{
		document.form1.editrelationempwithowner2[0].disabled=false; 
		document.form1.editrelationempwithowner2[1].disabled=false; 
		if(document.form1.editrelationempwithowner2[0].checked == true)
		{
			document.form1.editownerrelation2.disabled = false;
		
		}
		
		if(document.form1.editrelationempwithowner2[1].checked == true )
		{
			document.form1.editFirstname1.disabled=false;
			document.form1.editMiddlename1.disabled=false;
			document.form1.editLastname1.disabled=false;
			makeEnableDisable("changedOwnerAddress",1);
		}
		else
		{
			document.form1.editFirstname1.disabled=true;
			document.form1.editMiddlename1.disabled=true;
			document.form1.editLastname1.disabled=true;
			makeEnableDisable("changedOwnerAddress",0);
		}
		
	}
	
}
function changeOwnerDtlsNo()
{
	document.form1.editAssetTypeOfOwner.disabled=true;
	if(document.form1.editAssetTypeOfOwner.value == 'Asset_other')
	{
		document.form1.editrelationempwithowner2[0].disabled=true; 
		document.form1.editrelationempwithowner2[1].disabled=true; 
		if(document.form1.editrelationempwithowner2[0].checked == true)
		{
			document.form1.editownerrelation2.disabled = true;
		
		}
		
		
			document.form1.editFirstname1.disabled=true;
			document.form1.editMiddlename1.disabled=true;
			document.form1.editLastname1.disabled=true;
			makeEnableDisable("changedOwnerAddress",0);
		
	}
}


function deleteRequestedData(l,rowId)
{	//alert(rowId);
	var editAssetId = l;
	var rowId = rowId;
	if(editAssetId == '')
				{
					return;
				}
				
	//deleteRecord(rowId);
		var agree=confirm("Are you sure you want to delete this record ?");
     	if (agree)
     	{	
     		var delRow = document.getElementById(rowId);
			//alert(delRow.rowIndex);
			delRow.parentNode.deleteRow(delRow.rowIndex);
     	
			try{   
    				xmlHttp=new XMLHttpRequest();	    	    
	    		}
				catch (e)
				{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
        		  		}
				      	catch (e)
				      	{
       	   	  	           	  
			            	  return false;        
			      		}
			 	}
        	}		
			var url = "hdiits.htm?actionFlag=setDeleteFlagforPermissionEdit&EditAssetId="+editAssetId;  
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = deleteSucessfullyMsg;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}
}

function deleteSucessfullyMsg()
{
	if (xmlHttp.readyState == 4) 
			{    // alert(xmlHttp.status);
				if (xmlHttp.status == 200) 
				{	
					alert('your data deleted sucessfully');	
					var num = document.getElementById('editDataTable').rows.length;
					if(num == 1)
					{
						document.getElementById('editDataTable').style.display="none";
					}
				}
			}		
}

function displayNoneForAllEditData()
{	
	document.getElementById('LineForEdit').style.display="none";
	document.getElementById('LableForEdit').style.display="none";
	document.getElementById('DataForEdit').style.display="none";
	document.getElementById('editAssetAddress').style.display="none";
	document.getElementById('editRegNo').style.display="none";
	document.getElementById('tranxnLable').style.display="none";
	document.getElementById('TranxnDataForEdit').style.display="none";
	document.getElementById('editPartyLable').style.display="none";
	document.getElementById('changePartyDtls').style.display="none";
	document.getElementById('changeTypeOfParty').style.display="none";
	document.getElementById('changePartydetails').style.display="none";
	document.getElementById('changeTypeOfParty').style.display="none";
	document.getElementById('relation4').style.display="none";
	document.getElementById('relation5').style.display="none";
	document.getElementById('saveEditData').style.display="none";
	document.getElementById('relation4').style.display="none";
	document.getElementById('relation5').style.display="none";
	document.getElementById('changeCompanydetails').style.display="none";
	document.getElementById('changePartyName').style.display="none";
	document.getElementById('changePartyAddress').style.display="none";
	document.getElementById('editOwnerLable').style.display="none";
	document.getElementById('changeOwnerDtls').style.display="none";
	document.getElementById('editTypeOfOwner').style.display="none";
   	document.getElementById('editownerdetails').style.display="none";
   	document.getElementById('ownerrelation4').style.display="none";
	document.getElementById('ownerrelation5').style.display="none";
	document.getElementById('chngeownerName').style.display="none";
	document.getElementById('chngeOwnerAddress').style.display="none";
	document.getElementById('saveEditData').style.display="none";
   
	      			
}

function ownerAddressDisplay(xmlFileName5)
{	//alert('ownerAddressDisplay');
//alert(xmlFileName5);
	try{   
		    			xmlHttp=new XMLHttpRequest();	    	    
			    	}
					catch (e)
					{    // Internet Explorer    
							try{
		      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");	      					   
		      				}
				    		catch (e){
				          		try
		        		  		{
		                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");   	                	             
		        		  		}
						      	catch (e)
						      	{
		       	   	  	           	 
					            	  return false;        
					      		}
					 		}			 
		        	}	
		        	
		        	
		        	var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName5;
					xmlHttp.open("POST", encodeURI(url) , false);			
					xmlHttp.onreadystatechange = showOwnerAddressDtls;  
					xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
					xmlHttp.send(encodeURIComponent(null));	
}

function addOrUpdatePermissionRecord(methodName, actionFlag, fieldArray)
{
	xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert ("Your browser does not support AJAX!");
		  return;
		} 
		showProgressbar("Please Wait...");
		
		var reqBody = getRequestBody(fieldArray);	
		
		var url='actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		xmlHttp.onreadystatechange=function() { eval(methodName);}
		xmlHttp.open('POST', 'hdiits.htm', true);
      	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      	xmlHttp.setRequestHeader("Content-length", url.length);
      	xmlHttp.setRequestHeader("Connection", "close");
      	xmlHttp.send(url);
      	
		xmlHttp.onreadystatechange=function() {
			if(xmlHttp.readyState == 4) {
				eval(methodName);
				hideProgressbar();
			}	
		}
			
		//alert(url);
	//	xmlHttp.open('POST',encodeURI(url),true);
	//	xmlHttp.send(null);
}
/*function intimationForWhichPurpose(l)
{
	var id = l.value;
	
	if(id == '')
				{
					return;
				}
	if(id == "Intimation_asset")
	{
		document.getElementById('intimationPurpose').style.display="";
	}
	
	if(id == "Permission_asset")
	{
		document.getElementById('intimationPurpose').style.display="none";
	}
}

function approvedAssetReqData()
{
	if(document.getElementById('purchasesale').value == "Purchase_asset")
	{
		document.getElementById('movable').style.display="none";
		document.getElementById('fixed').style.display="none";
		document.getElementById('addressofasset').style.display="none";
		document.getElementById('assetDetails').style.display="none";
		document.getElementById('sale').style.display="";
		document.getElementById('purchase').style.display="none";
		document.getElementById('saleattachment').style.display="";
		document.getElementById('other1').style.display="none";
		document.getElementById('other').style.display="none";
		document.getElementById('ownerLable').style.display="none";
		document.getElementById('typeOfOwner').style.display="none";
		document.getElementById('ownerName').style.display="none";
		document.getElementById('OwnerAddress').style.display="none";
		document.getElementById('ownerdetails').style.display="none";
	//	document.getElementById('saleProperty').style.display="";
		document.getElementById('saleLable').style.display="";
	}	
	
	
}*/