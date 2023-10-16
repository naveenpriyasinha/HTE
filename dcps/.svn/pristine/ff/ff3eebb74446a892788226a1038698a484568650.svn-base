function validateForm()
{	
		var submitFlag = false;

		var totalArray = new Array();
		var totalArrayCount =0;
	
		totalArray[totalArrayCount]='radPhyChallenged';	
		totalArrayCount=totalArrayCount+1;

		var radPhyChallenged = 'N';
		if(document.frmBF.radPhyChallenged[0].status==true){radPhyChallenged='Y';}				
		if(radPhyChallenged=='Y')
		{
			totalArray[totalArrayCount]='type_Of_Disability';	
			totalArrayCount=totalArrayCount+1;	
			
			totalArray[totalArrayCount]='empDisabilityDtls';	
			totalArrayCount=totalArrayCount+1;
		}
	
		totalArray[totalArrayCount]='empBg';	
		totalArrayCount=totalArrayCount+1;
	
		totalArray[totalArrayCount]='empIdentificationMark';	
		totalArrayCount=totalArrayCount+1;
		
		totalArray[totalArrayCount]='empWeight';	
		totalArrayCount=totalArrayCount+1;
		
		totalArray[totalArrayCount]='empHeight';	
		totalArrayCount=totalArrayCount+1;
		
		totalArray[totalArrayCount]='empChest';	
		totalArrayCount=totalArrayCount+1;
		
		empWeightValidation = checkForDot(document.getElementById('empWeight').value,document.getElementById('empWeight'));
		empHeightValidation = checkForDot(document.getElementById('empHeight').value,document.getElementById('empHeight'));	
		empChestValidation = checkForDot(document.getElementById('empChest').value,document.getElementById('empChest'));
		
		totalArray[totalArrayCount]= 'radEmergencyDtls';	
		totalArrayCount=totalArrayCount+1;	
		
		var radEmergencyDtls ='F';
		if(document.frmBF.radEmergencyDtls[0].status==true){radEmergencyDtls='O';}	
		
		if(radEmergencyDtls=='O')
		{
			totalArray[totalArrayCount]='otherPersonLName';	
			totalArrayCount=totalArrayCount+1;
			
			totalArray[totalArrayCount]='otherPersonFName';	
			totalArrayCount=totalArrayCount+1;	
			
			totalArray[totalArrayCount]='otherEmergencyRelation';	
			totalArrayCount=totalArrayCount+1;
			
			totalArray[totalArrayCount]='otherRelation';	
			totalArrayCount=totalArrayCount+1;
		}
		else
		{
			totalArray[totalArrayCount]='familyPersonName';	
			totalArrayCount=totalArrayCount+1;	
		}
	
		totalArray[totalArrayCount]='otherEmergencyResidencePhone';	
		totalArrayCount=totalArrayCount+1;

		totalArray[totalArrayCount]='otherEmergencyOfficePhone';	
		totalArrayCount=totalArrayCount+1;
		
		totalArray[totalArrayCount]='otherEmergencyMobile';	
		totalArrayCount=totalArrayCount+1;

		totalArray[totalArrayCount]='otherEmergencyEmail';	
		totalArrayCount=totalArrayCount+1;
		
		totalArray[totalArrayCount]='otherEmergencyFax';	
		totalArrayCount=totalArrayCount+1;
		
		submitFlag=validateSpecificFormFields(totalArray);	
		
		if(submitFlag==true)
		{
			if(!empWeightValidation)
			{
				alert(alrtWeight);
				setFocusSelection('empWeight');
				return;
			} 
			 if(!empHeightValidation)
			{
				alert(alrtHeight);
				setFocusSelection('empHeight');
				return;
			} 
			if(!empChestValidation) 
			{
				alert(alrtChest);
				setFocusSelection('empChest');
			    return;
			}
			
			var addressParameter = addressParameters('otherEmergencyAddress','Permanent Address');
			var addressCheck = true;
					
			if(radEmergencyDtls=='O' && !isAddressClosed('otherEmergencyAddress'))				
				addressCheck =  validateSpecificFormFields(addressParameter);
			else if(isAddressClosed('otherEmergencyAddress'))/*changed by sandip start*/
			{
				alert(emergencyAddressAlert);
				getFieldGroupObj(document.getElementById('cmbCountryotherEmergencyAddress'));
				document.getElementById('cmbCountryotherEmergencyAddress').focus();
				addressCheck=false;
			}	/*changed by sandip end*/	
			
			if(empHeightValidation && empWeightValidation && empChestValidation && addressCheck)
			{
				var contactStatus=validateEmergencyContactDtls();
				if(contactStatus)
				{
					var uri = "./hrms.htm?actionFlag=";
					var url = uri + "insertEmpData_WF&edit=Y&employeeId=${hrEisEmpMst.empId}";   
					document.frmBF.action = url;
					document.frmBF.otherEmergencyRelation.disabled=false;
				 	document.frmBF.submit();
				 }
				 else
 				 {
					return;
				 }
			}
			else
			{
				return;
			}
		}
}

function checkForDot(fieldValue,fieldId)
{			
	var valueFlag=true,dotCount=0;
	for(var i=0;i<fieldValue.length;i++)
	{
		var keyId = fieldValue.charCodeAt(i);									
		if(keyId==46) {dotCount=parseInt(dotCount)+1;}
	}
	if(dotCount>=2){valueFlag=false;}
	
	if(fieldId.name=='empWeight')
	{
		if(dotCount==0 && fieldValue.length>3)
		{
				valueFlag=false;
		}
	}
	if(fieldId.name=='empHeight')
	{
		if(dotCount==0 && fieldValue.length>3)
		{
				valueFlag=false;
		}
	}
	if(fieldId.name=='empChest')
	{
		if(dotCount==0 && fieldValue.length>3)
		{
				valueFlag=false;
		}
	}
	
	return valueFlag;
}

/**Start------> Count Age Function *****/

/*function countAge(birthDate)
{									
	var splitDate=birthDate.split("/");							
	var bday=parseInt(splitDate[0]);
	var bmo=(parseInt(splitDate[1])-1);
	var byr=parseInt(splitDate[2]);
	var age;
	var now = new Date();		
	tday=now.getUTCDate();
	tmo=(now.getUTCMonth());
	tyr=(now.getUTCFullYear());
	if((tmo > bmo)||(tmo==bmo & tday>=bday)) {age=byr}				
	else  {age=byr+1}
	if(isNaN(tyr-age)==true){}
	else if((tyr-age)>150 || (tyr-age)<=-1)			
	{							
		document.getElementById("emp_age").innerHTML = "0";
	}
	else {document.getElementById("emp_age").innerHTML = tyr-age;}
}*/
/**Ends------> Count Age Function *****/

/**Start------>Multipal Add For Language Proficiency*******/

function addLangugeInTable()
{
	if (xmlHttp.readyState == 4) 
	{
		if (xmlHttp.status == 200) 
		{			
			var displayFieldArray = new Array('emp_lang_known','emp_proficiency');
			addDataInTable('multipleAddLangTable','langXML',displayFieldArray ,'editLanguage','deleteLanguage');		
			resetLanguage();
		}
	}
}
function addLangauge(btnValue)  
{
	var lang=document.getElementById('emp_lang_known').value;	
	var proficiency=document.getElementById('emp_proficiency').value;
	if(lang=='' || lang=='Select' || lang=='null') 
	{
		alert(alrtLanguageType);
		document.getElementById('emp_lang_known').focus();
	}
	else if(proficiency=='' || proficiency=='null' || proficiency=='Select')
	{
		alert(alrtLangProf); 	
		document.getElementById('emp_proficiency').focus();
	}
	else 
	{		
		var langStatus=checkForLanguageRec(); 
		
		if(langStatus)
		{	
			if(btnValue=='Add')
			{
				addOrUpdateRecord('addLangugeInTable','addEmpKnownLanguage_WF',new Array('emp_lang_known','emp_proficiency'));			
			}
			else
			{
				if(globalflagForDBRecord==true)
				{
					var srNo=document.getElementById('srNo').value;				
					addOrUpdateRecord('saveLangaugeInTable','addEmpKnownLanguage_WF&srNo='+srNo,new Array('emp_lang_known','emp_proficiency'));
				}
				else
				{
					addOrUpdateRecord('saveLangaugeInTable','addEmpKnownLanguage_WF',new Array('emp_lang_known','emp_proficiency'));		
				}
			}
		}
	  	else
		{
			alert(alrtLanguage);
			resetLanguage();
			document.getElementById('emp_lang_known').focus();
		}
	}
}

function editDBLanguage(rowId)
{
	globalflagForDBRecord=true;
	sendAjaxRequestForEdit(rowId, 'populateLanguage');
	document.frmBF.addLanguage.disabled=true;		
	document.frmBF.saveLanguage.disabled=false;		
}
function editLanguage(rowId)
{
	globalflagForDBRecord=false;
	sendAjaxRequestForEdit(rowId, 'populateLanguage');
	document.frmBF.addLanguage.disabled=true;		
	document.frmBF.saveLanguage.disabled=false;		
}
function populateLanguage()
{
	 if (xmlHttp.readyState == 4) 
	 {	 	
	 	if (xmlHttp.status == 200) 
		{
		  	var decXML = xmlHttp.responseText;				  	
			var xmlDOM = getDOMFromXML(decXML);
			try 
			{
				document.getElementById('srNo').value=getXPathValueFromDOM(xmlDOM, 'langProfPkId');
			}catch(ex){document.getElementById('srNo').value='';}
			var str = '';
			str= getXPathValueFromDOM(xmlDOM, 'cmnLanguageMstByLanguageId/lookupName');/**Added by Sunil For Language*/
			
			document.getElementById('emp_lang_known').value=str;
			str='';
			str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMst/lookupName');			
			document.getElementById('emp_proficiency').value=str;
		}
	}
}
function deleteDBLanguage(rowId)
{
	var flag_d = deleteDBRecord(rowId);	
	if(flag_d)
	{					
		if(document.getElementById('multipleAddLangTable').rows.length==1)
		{
			trow=document.getElementById(rowId);
			trow.cells[1].innerText='0';
			document.getElementById('multipleAddLangTable').style.display='none';
		}else
		{
				trow=document.getElementById(rowId);
				trow.cells[1].innerText='0';
		}
		resetLanguage();
	}
}
function deleteLanguage(rowId)
{
	var flag_D = deleteRecord(rowId);	
	if(flag_D==true)
	{					
		var flag=1;
		var num = document.getElementById('multipleAddLangTable').rows.length;								
		for(var i=1;i<num;i++)
		{
			trow= document.getElementById('multipleAddLangTable').rows[i];																																		
			try{															
				if(trow.style.display=='')
				{								
					flag=0;
				}
			}catch(e){break;}
		}
		if(flag==1){document.getElementById('multipleAddLangTable').style.display='none';}	
		resetLanguage();
	}
	
}
function saveLangaugeInTable()
{
	if (xmlHttp.readyState == 4) 
	{
		if (xmlHttp.status == 200) 
		{
			var displayFieldArray = new Array('emp_lang_known','emp_proficiency');
			if(globalflagForDBRecord==false)
			{
				updateDataInTable('langXML', displayFieldArray);
			}
			else
			{
				updateDataInTable('addedlangXML', displayFieldArray);
			}
			resetLanguage();	
		}
	}
}
function resetLanguage()
{
	document.getElementById('emp_lang_known').value='Select';	
	document.getElementById('emp_proficiency').value='Select';
	document.frmBF.addLanguage.disabled=false;		
	document.frmBF.saveLanguage.disabled=true;
	updateRow=null;
}
function checkForLanguageRec()
{
	var currentLang='';
	var checkLangFlag=true; 
	var num = document.getElementById('multipleAddLangTable').rows.length;
	var selectedLang=document.frmBF.emp_lang_known;
	var lang=selectedLang.options[selectedLang.selectedIndex].text;
	for(var i=1;i<num;i++)
	{		
		trow=document.getElementById('multipleAddLangTable').rows[i];
		currentLang=trow.cells[1].innerText;
		if(currentLang!='0')
		{
			rowId=trow.id;
			if (updateRow!=null && rowId == updateRow)
			continue;
		
			if(currentLang==lang) 
			{
				checkLangFlag=false;
			}
		}
	}
	return	checkLangFlag;
}
/**Start------>Multipal Add For Language Proficiency*****************/


/**Strat---->Load Emergency Contact Details of Family Member Or Other Member*********/
function showEmergencyDtls(objId)
{		
	if(objId=='F') 	
	{
		makeEnableDisable('otherEmergencyAddress',0);
		document.getElementById('otherEmergency').style.display='none';
		document.getElementById('familyEmergency').style.display='';
		document.getElementById('family_Relation_text').value='';
		document.getElementById('family_Relation').style.display='none';
		document.getElementById('family_Relation_text').style.display='none';	
		onLoadFamilyDetails();
	}
	else if(objId=='O')	
	{
		makeEnableDisable('otherEmergencyAddress',1);
		document.getElementById('familyEmergency').style.display='none';
		document.getElementById('otherEmergency').style.display='';	
		document.getElementById('otherEmergencyRelation').value=otherLookupDesc;
		document.getElementById('other_Relation').style.display='';
		document.getElementById('other_Relation_text').style.display='';
		onLoadOtherMemberDetails();
	}
}
/**Ends---->Load Emergency Contact Details of Family Member Or Other Member*********/


/********Physically Challenged Script***********/	
	function phyChallenged(btnValue)
	{
		if(btnValue=='N')
		{	
			document.getElementById('emp_phyChallenged').style.display='none';
		}
		else if(btnValue=='Y')
		{	
			document.getElementById('emp_phyChallenged').style.display='';	
		}
	}
/**********End of Physically Challenged Script ********/

/************* Function Used for if Relation is Other  ***************/
function OtherRelation(RelationCombo,flag)
{			
	var id = RelationCombo.value;	
	if(id == '')
	{
		RelationCombo.value='';
		return;
	}				
	if(flag==1)
	{
		document.getElementById('other_Relation').style.display='';
		document.getElementById('other_Relation_text').style.display='';
		document.getElementById('familyEmergency').style.display='none';
	}
	else if(flag==2)
	{
		document.getElementById('familyEmergency').style.display='';
		document.getElementById('other_Relation').style.display='none';
		document.getElementById('other_Relation_text').style.display='none';
	}
}
/************End Of Reltion Combo Function ************/

/*****Start----->For Populating Emergency Contact Dtls Of Family Member*****/

function getFamilyInfo(cmbFamily)
{
	var id = cmbFamily.value;	
	if(id!='Select' && id!='' )
	{		
		showProgressbar();
		xmlHttp=GetXmlHttpObject();       	
		var url = "hrms.htm?actionFlag=getNextComboValueData_WF&flag=family&cmbId="+id;
		xmlHttp.open("POST", encodeURI(url) , true);
				
		xmlHttp.onreadystatechange = processFamily;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
	}
	else
	{
		checkFamilyOtherRel('Select');
		document.frmBF.familyEmergencyRelation.value='';
		resetAddress('otherEmergencyAddress');
		closeAddress('otherEmergencyAddress'); 
	}
}
function processFamily()
{
	if (xmlHttp.readyState == 4) 
	{
		if (xmlHttp.status == 200) 
		{
			var decXML = xmlHttp.responseText;				  								
			proceeMe(decXML);	
		}
	}
}
function proceeMe(decXML)
{
	XMLDoc = getDOMFromXML(decXML);					
	if(XMLDoc!=null)
	{
		var rel1 = XMLDoc.getElementsByTagName('Relation');
		var addr1 = XMLDoc.getElementsByTagName('Address');
		
		rel=rel1[0].childNodes[0].text;
		addr=addr1[0].childNodes[0].text;
		document.frmBF.familyEmergencyRelation.value=rel;
		checkFamilyOtherRel(rel);
		try
		{
			var relOther1 = XMLDoc.getElementsByTagName('Other');
			relOther=relOther1[0].childNodes[0].text;
			document.frmBF.familyRelation.value=relOther;
			if(relOther!='' && relOther!=null)
			{
				document.getElementById('family_Relation').style.display='';
				document.getElementById('family_Relation_text').style.display='';
			}
		}catch(ex){}				
		
		
		try
		{   
    		xmlHttp=new XMLHttpRequest();	    	    
    	}
		catch (e)
		{ 
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
		       	  return false;        
		    	}
		 	}			 
        }	
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + addr;   
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = processAddressFamily;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
}

function processAddressFamily()
{
	if (xmlHttp.readyState == 4) 
	{
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;
			var XMLDoc=getDOMFromXML(xmlStr);
			var addrXPath = 'cmnAddressMst';		
			editAddress('otherEmergencyAddress',XMLDoc,addrXPath);
			makeEnableDisable('otherEmergencyAddress',0);
		}
	}
	hideProgressbar();
}

function checkFamilyOtherRel(rel)
{
	if(rel==otherLookupDesc)
	{
		document.getElementById('family_Relation').style.display='';
		document.getElementById('family_Relation_text').style.display='';
	}	
	else
	{	
		document.getElementById('family_Relation').style.display='none';
		document.getElementById('family_Relation_text').style.display='none';
		document.getElementById('family_Relation_text').value='';
	}	
}
/*****Ends----->For Populating Emergency Contact Dtls Of Family Member*****/


function onLoadFamilyDetails()
{
	var tempStr=strAjaxKey;
	if(tempStr!='' && tempStr!=null)
	{
		showProgressbar(); 
		proceeMe(tempStr);
	}
	tmep=familyMemId;
	if(tmep!='' && tmep!=null && tmep!='null')
	{	
		document.getElementById('familyPersonName').value=tmep;
	}
	else
	{
		document.getElementById('familyPersonName').value='Select';
		document.getElementById('familyEmergencyRelation').value='';
		resetAddress('otherEmergencyAddress');
		closeAddress('otherEmergencyAddress'); 
	}
	
	if(DbContactType=='O' || DbContactType=='o')
	{
		document.getElementById('otherEmergencyResidencePhone').value='';
		document.getElementById('otherEmergencyMobile').value='';
		document.getElementById('otherEmergencyEmail').value='';
		document.getElementById('otherEmergencyOfficePhone').value='';
		document.getElementById('otherEmergencyFax').value='';
	}else
	{
		populateEmerContactDtls();
	}
}
	
function onLoadOtherMemberDetails()
{
	document.getElementById('otherPersonFName').value=otherFName;
	document.getElementById('otherPersonMName').value=otherMName;
	document.getElementById('otherPersonLName').value=otherLName;
	document.getElementById('otherRelation').value=otherEmerRelation;
	
	resetAddress('otherEmergencyAddress');
	closeAddress('otherEmergencyAddress'); 
	
	sendAjaxRequestForOtherAddress();
	
	if(DbContactType=='F' || DbContactType=='f')
	{
		document.getElementById('otherEmergencyResidencePhone').value='';
		document.getElementById('otherEmergencyMobile').value='';
		document.getElementById('otherEmergencyEmail').value='';
		document.getElementById('otherEmergencyOfficePhone').value='';
		document.getElementById('otherEmergencyFax').value='';
	}else
	{
		populateEmerContactDtls();
	}
}
	
function populateEmerContactDtls()
{
	if (emerResidencePhone!='0')
			document.getElementById('otherEmergencyResidencePhone').value=emerResidencePhone;
		else				
			document.getElementById('otherEmergencyResidencePhone').value='';
		
		if (emerOfficePhone!='0')	
			document.getElementById('otherEmergencyOfficePhone').value=emerOfficePhone;
		else
			document.getElementById('otherEmergencyOfficePhone').value='';
				
		document.getElementById('otherEmergencyMobile').value=emerMobileNo;
		document.getElementById('otherEmergencyEmail').value=emerEmail;
		
		if (emerFaxNo!='0')
			document.getElementById('otherEmergencyFax').value=emerFaxNo;
		else
			document.getElementById('otherEmergencyFax').value='';	
}

/*****Start----->For Populating Other Addtess Dtls For Other Family Member*****/
function sendAjaxRequestForOtherAddress()
{
	var xmlFileName = OtherAddressDtlsXmlFile;
	
	if (xmlFileName == null || xmlFileName == '')
		return false;
	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) 
	{
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
	var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
	showProgressbar(); 
	xmlHttp.onreadystatechange = populateFormOtherAddress;
	xmlHttp.open("POST",encodeURI(url),false);
	xmlHttp.send(null);
}
function populateFormOtherAddress()
{
	 if (xmlHttp.readyState == 4) 
	 {
	  	var decXML = xmlHttp.responseText;
		var xmlDOM = getDOMFromXML(decXML);
		var addrXPath = 'cmnAddressMst';
		editAddress('otherEmergencyAddress',xmlDOM,addrXPath);	
	}
	hideProgressbar();
}
/*****Ends----->For Populating Other Addtess Dtls For Other Family Member*****/


function validateEmergencyContactDtls()
{
	var empEmergencyMobileContact=document.frmBF.otherEmergencyMobile;				
	var MobileValue= empEmergencyMobileContact.value;
	if(MobileValue.length!=10)
	{
		alert(alrtMobileNoLength);
		setFocusSelection('otherEmergencyMobile');
		return false;
	}
	
	return true;
}

function checkDecimalNumber(key)
{
	var num;
	num = window.event.keyCode;
	if (eval(num)<=47 ||eval(num)>58)
		return false;
	else
	 	return true;
}

function closeWindow()
{
	document.frmBF.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
   	document.frmBF.submit();
}