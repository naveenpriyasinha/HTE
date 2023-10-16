function joiningTimeAddData()
{
	//alert(document.getElementById("ownerrelation1").value);
	if(document.form1.assettype.value =="Fixed_asset")
	{
		if(document.form1.rdoAddressAddress[0].checked == true)
		{
			 document.getElementById("assetAddress").value = document.getElementById("txtCityHouseNameAddress").value+","+document.getElementById("txtAreaAddress").value;
		 	
		}
		if(document.form1.rdoAddressAddress[1].checked == true)
		{
			 document.getElementById("assetAddress").value = document.getElementById("txtVillageHouseNameAddress").value+","+document.getElementById("txtFaliyuAddress").value;
		 	
		}
		if(document.form1.rdoAddressAddress[2].checked == true)
		{
		 	document.getElementById("assetAddress").value = document.getElementById("txtOtherAreaAddress").value+","+document.getElementById("txtOtherCityVillageAddress").value;
		 	
		}
	}
	else
	{
		//alert(inside else');
		document.getElementById("assetAddress").value = "";
	}
	//alert('joiningTimeAddData');		
	var addressArray = addressParameters("Address", "Permanent Address"); 
	var addressArray1 = addressParameters("OwnerJoiningAddress", "Permanent Address");
	//addressArray=addressArray.concat(addressArray1);	
	var totalArray=new Array("assettype","property","other_movable","assetAddress","registration","asset","other_fixed","date_of_trans","approxprice","TypeOfOwner","relationempwithowner","ownerrelation1","firstname1","middlename1","lastname1");
    totalArray = totalArray.concat(addressArray);
    totalArray = totalArray.concat(addressArray1);
   // alert(totalArray);
   // alert(totalArray.length);
    addOrUpdateRecord('showJoiningTable','addJoiningData',totalArray);			
}

function showJoiningTable()
{
	if (xmlHttp.readyState == 4)
	{		  		
	var decXml=xmlHttp.responseText;
	//alert(decXml);
		if(xmlHttp.status == 200)
		{
		 var displayFieldArray=new Array("assettype");
		 if(document.form1.assettype.value == "Movable_asset")
		 {
		 	if(document.form1.property.value == "Other_movable")
		 	{
		 		displayFieldArray[1] = "other_movable";
		 	}
		 	else
		 	{
		 		displayFieldArray[1] = "property";
		 	}
		 }
		 if(document.form1.assettype.value == "Fixed_asset")
		 {
		 	if(document.form1.asset.value == "Other_fixed")
		 	{
		 		displayFieldArray[1] = "other_fixed";
		 	}
		 	else
		 	{
		 		displayFieldArray[1] = "asset";
		 	}
		 }
		 
		 displayFieldArray[2] = "assetAddress";
		 displayFieldArray[3] = "registration";
		 displayFieldArray[4] = "date_of_trans";
		 displayFieldArray[5] = "approxprice";
		 if(document.form1.TypeOfOwner.value == "Asset_self")
		 {
		 	displayFieldArray[6] = "TypeOfOwner";
		 }
		 else if(document.form1.TypeOfOwner.value == "Asset_other")
		 {
		 	document.getElementById("ownerName").value = document.getElementById("firstname1").value+" "+document.getElementById("lastname1").value;
		 	displayFieldArray[6] = "ownerName";
		 }
		 
		 addDataInTable("addJoiningTable", 'encXML', displayFieldArray, "editAddJoiningData", "deleteAddJoiningData", "") 
		 document.getElementById('addJoiningTable').style.display='';		
		  		
		 resetJoiningData();
		 document.getElementById('submitButton').style.display="";
		}
	}	
}

function editAddJoiningData(rowId)
{//	alert(rowId);
	changeRowColor("addJoiningTable",rowId,true);
	sendAjaxRequestForEdit(rowId, 'populateJoiningForm');
}


function populateJoiningForm()
{
	if (xmlHttp.readyState == 4) { 	
	
		document.getElementById('addButton').style.display="none";	
		document.getElementById('updateButton').style.display="";	
	  	var decXML = xmlHttp.responseText;
	 //	alert(decXML);
		var xmlDOM = getDOMFromXML(decXML);
		//alert(xmlDOM);
		document.getElementById('assettype').value = getXPathValueFromDOM(xmlDOM, 'assetType');
		//alert(document.getElementById('assettype').value);
		if(document.getElementById('assettype').value == "Movable_asset")
		{	//alert(getXPathValueFromDOM(xmlDOM, 'otherAssetName'));
			resetAddress("Address");
			document.getElementById('movable').style.display="";
			document.getElementById('fixed').style.display="none";
			document.getElementById('addressofasset').style.display="none";
			if(getXPathValueFromDOM(xmlDOM, 'otherAssetName')	!= null)
			{
				//document.getElementById('Name_other1').style.display="none";
				document.getElementById('Name_other1').style.display="";
				document.getElementById('Registrationno').style.display="none";
				document.getElementById('Registrationno1').style.display="none";
				document.getElementById('other_movable').value = getXPathValueFromDOM(xmlDOM, 'otherAssetName');
			}
			else if(getXPathValueFromDOM(xmlDOM, 'registrationNo') != null)
			{
				document.getElementById('Name_other1').style.display="none";
				document.getElementById('Registrationno').style.display="";
				document.getElementById('Registrationno1').style.display="";
				document.getElementById('registration').value = getXPathValueFromDOM(xmlDOM, 'registrationNo');
			}
		
			document.getElementById('property').value = getXPathValueFromDOM(xmlDOM, 'assetName');
			
			
		}
		if(document.getElementById('assettype').value == "Fixed_asset")
		{
			document.getElementById('movable').style.display="none";
			document.getElementById('fixed').style.display="";
			document.getElementById('addressofasset').style.display="";
			//alert(getXPathValueFromDOM(xmlDOM, 'otherAssetName'));	
			if(getXPathValueFromDOM(xmlDOM, 'otherAssetName') != null)
			{
				document.getElementById('Name_other').style.display="";
				document.getElementById('other_fixed').value = getXPathValueFromDOM(xmlDOM, 'otherAssetName');
			}
			
			document.getElementById('asset').value = getXPathValueFromDOM(xmlDOM, 'assetName');
			
			var addrXPath = 'cmnAddressMstByAddress';
			editAddress('Address',xmlDOM,addrXPath);

		}
		
		document.getElementById('date_of_trans').value = getXPathValueFromDOM(xmlDOM, 'transactionDate');	
		document.getElementById('approxprice').value = getXPathValueFromDOM(xmlDOM, 'transactionPrice');	
		document.getElementById('TypeOfOwner').value = getXPathValueFromDOM(xmlDOM, 'typeOfOwner');	
		
		if(document.getElementById('TypeOfOwner').value == "Asset_other")
		{	
			document.getElementById('ownerdetails').style.display="";
			document.getElementById('ownerName').style.display="";
			document.getElementById('OwnerAddress').style.display="";
			//alert(getXPathValueFromDOM(xmlDOM, 'relationEmpWithOwner'));
			if(getXPathValueFromDOM(xmlDOM, 'relationEmpWithOwner') == "Y")
			{
				document.getElementById('ownerrelation').style.display="";
				document.getElementById('ownerrelation1').style.display="";
				document.form1.relationempwithowner[0].checked = true;
				//alert(getXPathValueFromDOM(xmlDOM, 'lookupName'));
				//alert(getXPathValueFromDOM(xmlDOM, 'relationType'));
				document.getElementById('ownerrelation1').value = getXPathValueFromDOM(xmlDOM, 'relationType');	
				/*document.getElementById('firstname1').value = getXPathValueFromDOM(xmlDOM, 'fmFirstName');
				makeEnableDisable("OwnerAddress",1);
				var addrXPath = 'cmnAddressMst';
				editAddress('OwnerAddress',xmlDOM,addrXPath);	*/
				
				
			}
			if(getXPathValueFromDOM(xmlDOM, 'relationEmpWithOwner') == "N")
			{
				document.getElementById('ownerrelation').style.display="none";
				document.getElementById('ownerrelation1').style.display="none";
				document.form1.relationempwithowner[1].checked = true;
				
				
			}
			var addrXPath = 'cmnAddressMstByOwnerAddress';
			editAddress('OwnerJoiningAddress',xmlDOM,addrXPath);
				
			document.getElementById('firstname1').value = getXPathValueFromDOM(xmlDOM, 'firstName');
			if(getXPathValueFromDOM(xmlDOM, 'middleName')==null || getXPathValueFromDOM(xmlDOM, 'middleName')=='')
			{
				document.getElementById('middlename1').value='';
			}
			else
			{
				document.getElementById('middlename1').value = getXPathValueFromDOM(xmlDOM, 'middleName');	
			}
			document.getElementById('lastname1').value = getXPathValueFromDOM(xmlDOM, 'lastName');	
		}
		else {
		document.getElementById('ownerdetails').style.display="none";
		document.getElementById('ownerName').style.display="none";
		document.getElementById('OwnerAddress').style.display="none";
		}
	}
}
function deleteAddJoiningData(rowId)
{
	deleteRecord(rowId);
}



function resetJoiningData()
{
		 document.form1.assettype.value = 0; 
		 document.form1.property.value = 0; 
		 document.form1.other_movable.value = ""; 
		 document.form1.registration.value = ""; 
		 document.form1.asset.value = 0; 
		 document.form1.other_fixed.value = ""; 
		 document.form1.date_of_trans.value = ""; 
		 document.form1.approxprice.value = ""; 
		 document.form1.TypeOfOwner.value = 0; 
		 document.form1.relationempwithowner[0].checked = false; 	
		 document.form1.relationempwithowner[1].checked = false; 
		 document.form1.ownerrelation.value = 0; 
		 document.form1.firstname1.value = ""; 
		 document.form1.middlename1.value = ""; 
		 document.form1.lastname1.value = ""; 
		 resetAddress("Address");
		 resetAddress("OwnerJoiningAddress");
		 document.form1.rdoAddressAddress[0].checked = false; 	
		 document.form1.rdoAddressAddress[1].checked = false; 
		 document.form1.rdoAddressAddress[2].checked = false; 	
		 document.form1.rdoAddressOwnerJoiningAddress[0].checked = false; 
		 document.form1.rdoAddressOwnerJoiningAddress[1].checked = false; 	
		 document.form1.rdoAddressOwnerJoiningAddress[2].checked = false; 
}


function updateJoiningDtls()
{
	
	var addrName ='Address';
	var addrLookUpName= 'Permanent Address';
	var addrName1 ='OwnerJoiningAddress';			
	var addrLookUpName1= 'Permanent Address';				
	var addressArray = addressParameters(addrName, addrLookUpName); 
	var addressArray1 = addressParameters(addrName1, addrLookUpName1);
	addressArray=addressArray.concat(addressArray1);	
			
    var totalArray=new Array("assettype","property","other_movable","registration","asset","other_fixed","date_of_trans","approxprice","TypeOfOwner","relationempwithowner","ownerrelation","firstname1","middlename1","lastname1");
    totalArray = totalArray.concat(addressArray);
    addOrUpdateRecord('updateRecord','addJoiningData',totalArray);		
}

function updateRecord()
{		//alert("inside update record")
	  if (xmlHttp.readyState == 4)
	  {		
	  	
		 var displayFieldArray=new Array("assettype");
		 if(document.form1.assettype.value == "Movable_asset")
		 {
		 	if(document.form1.property.value == "Other_movable")
		 	{
		 		displayFieldArray[1] = "other_movable";
		 	}
		 	else
		 	{
		 		displayFieldArray[1] = "property";
		 	}
		 }
		 if(document.form1.assettype.value == "Fixed_asset")
		 {
		 	if(document.form1.asset.value == "Other_fixed")
		 	{
		 		displayFieldArray[1] = "other_fixed";
		 	}
		 	else
		 	{
		 		displayFieldArray[1] = "asset";
		 	}
		 }
		 if(document.form1.rdoAddressAddress[0].checked == true)
		 {
		 	document.getElementById("assetAddress").value = document.getElementById("txtCityHouseNameAddress").value+" "+document.getElementById("txtAreaAddress").value;
		 	
		 }
		 if(document.form1.rdoAddressAddress[1].checked == true)
		 {
		 	document.getElementById("assetAddress").value = document.getElementById("txtVillageHouseNameAddress").value+" "+document.getElementById("txtFaliyuAddress").value;
		 	
		 	
		 }
		 if(document.form1.rdoAddressAddress[2].checked == true)
		 {
		 	document.getElementById("assetAddress").value = document.getElementById("txtOtherAreaAddress").value+" "+document.getElementById("txtOtherCityVillageAddress").value;
		 	
		 }
		 
		 	
		 
		 displayFieldArray[2] = "assetAddress";
		 displayFieldArray[3] = "registration";
		 displayFieldArray[4] = "date_of_trans";
		 displayFieldArray[5] = "approxprice";
		 if(document.form1.TypeOfOwner.value == "Asset_self")
		 {
		 	displayFieldArray[6] = "TypeOfOwner";
		 }
		 else if(document.form1.TypeOfOwner.value == "Asset_other")
		 {
		 	document.getElementById("ownerName").value = document.getElementById("firstname1").value+" "+document.getElementById("lastname1").value;
		 	displayFieldArray[6] = "ownerName";
		 }
		 //alert(encXML);
		updateDataInTable('encXML',displayFieldArray);

		 resetJoiningData();
		 document.getElementById('addButton').style.display="";	
		 document.getElementById('updateButton').style.display="none";	
		 }
}



function getDateDiffInString(strDate1,strDate2)
{
		//alert('getDateDiffInString');
		strDate1 = strDate1.split("/"); 
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		starttime = new Date(starttime.valueOf()); 

		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		endtime = new Date(endtime.valueOf()); 												

		
		if(endtime >= starttime) 
		{	//alert('endtime >= starttime');
			var setDay = 0;    	
			var lIntYear = strDate2[2] - strDate1[2];
     	 	var lIntMonth = (strDate2[1])- (strDate1[1]);
     	 	var lIntDay = strDate2[0] - strDate1[0];     	 	

     	 	var intMonth=parseInt(strDate1[1]);
     	 	var intday = parseInt(strDate1[0]);
     	 	intYear = parseInt(strDate1[2]);
     	 	while(strDate2[2] >= intYear)
     	 	{  // alert('while');  	 		    	 		
				if (intMonth>=13) {			
					intMonth=1;
					intYear++;
				}
				if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) {
					setDay = 31;	
				}
				if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) {
					setDay = 30;
				}
				if (intMonth == 2) 
				{
					if (LeapYear(intYear) == true) {
						setDay = 29;
					}
					else {
						setDay = 28;
					}
				}				

	     	 	if(setDay!=0)
	     	 	{
				    while(lIntDay > setDay)
				    {
				          lIntDay -= setDay;
				          lIntMonth++;
				          if(lIntMonth==12)
				          {
				              lIntMonth=0;
				              lIntYear++;
				          }
				    }
				    while(lIntDay < 0)
				    {
				          lIntDay = setDay + lIntDay;
				          lIntMonth--;
				          if(lIntMonth<=-1)
				          {
				              lIntMonth=12+lIntMonth;
				              lIntYear--;              
				          }
				    }
				    if(lIntMonth <=-1)
				    {
				          lIntMonth=12+lIntMonth;
				          lIntYear--;              
				    }
				    
				   	return ( lIntYear+' Years '+lIntMonth+' Months '+lIntDay+' Days');
				   
				}
				else 
				{
					return ('0 Years 0 Months 0 Days');
					
				}
				intMonth++;													
			}
		}
		else
		{
			return  ('0 Years 0 Months 0 Days'); 
			
		}
}
function LeapYear(year)
{	
	if(year%4 == 0 ){return true;}
	else 	{
		return false;		
	}
}