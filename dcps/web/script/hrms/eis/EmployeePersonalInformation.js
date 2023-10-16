	var addressDivId="addressDiv";
	var attachmentDivId="attachmentDiv";
	var imgAddressExpandId="AddressExpandAgent";	
	var imgAttachmentExpandId="AttachmentExpandAgent";
	
	function validateEmail(field)
	{
		var field_value=field.value;
        var periodpos="";
		var atpos="";
        var rule_num="0123456789qwertyuiopasdfghjklzxcvbnmASDFGHJKLPOIUYTREWQzXCVBNM.@_-";
		if(field_value!="")
		{
			for(var i=0;i<field_value.length;i++)
			{
       			var cchar=field_value.charAt(i);
        		if(rule_num.indexOf(cchar)==-1)
        		{
          			alert(empPersonalInfoAlertMsgArr[1]);
          			field.focus();
          			return false;
        		}
    		} 
	 		atpos=field_value.indexOf("@",1)
  			if(atpos==-1)
	 		{
       			alert(empPersonalInfoAlertMsgArr[2]);
				field.focus();
				return false;
	 		}
	 		periodpos=field_value.lastIndexOf(".")
	 		if(periodpos==-1)
	 		{
	      		alert(empPersonalInfoAlertMsgArr[3]);
				field.focus();
	        	return false;
      		}
			if(!((periodpos + 3 == field_value.length) || (periodpos + 4 == field_value.length)))
			{
				alert(empPersonalInfoAlertMsgArr[4]);
				return false;
			}
		}
 		return true;
 	}

	function saveEditDataForm()
	{
		var submitFlag = false;  
		var fieldArray = new Array('emp_last_name','emp_first_name','Religion','Category','emp_mother_tongue','Marital_Status','EmpType','Nationality');
		var submitFlag = validateSpecificFormFields(fieldArray); 
		
		if(submitFlag)
		{
			/* Address Validation Starts */
			var birthAddrName ='birthPlaceAddress';
			var nativeAddrName ='nativePlaceAddress';
			var permanentAddrName ='permanentPlaceAddress';
			var currentAddrName ='currentPlaceAddress';
			
			var permanentAddrLookUpName = 'Permanent Address'; 
			var presentAddrLookUpName = 'Present Address';
			
			var birthAddressArray = addressParameters(birthAddrName, permanentAddrLookUpName);  // returns an array of address parameters												
			var nativeAddressArray = addressParameters(nativeAddrName, permanentAddrLookUpName);  // returns an array of address parameters												
			var permanentAddressArray = addressParameters(permanentAddrName, permanentAddrLookUpName);  // returns an array of address parameters												
			var currentAddressArray = addressParameters(currentAddrName, presentAddrLookUpName);  // returns an array of address parameters												
			
			if(isAddressClosed(birthAddrName)== false)	
				submitFlag =  validateSpecificFormFields(birthAddressArray);		
				
			if(submitFlag && isAddressClosed(nativeAddrName)== false && getCheckedRadioValue('radNativeAddress')=='N')	
				submitFlag =  validateSpecificFormFields(nativeAddressArray);
				
			if(submitFlag && isAddressClosed(permanentAddrName)== false && getCheckedRadioValue('radPermanentAddress')=='N')	
				submitFlag =  validateSpecificFormFields(permanentAddressArray);	
				
			if(submitFlag && isAddressClosed(currentAddrName)== false && getCheckedRadioValue('radCurrentAddress')=='N')	
				submitFlag =  validateSpecificFormFields(currentAddressArray);
			/* Address Validation Ends */
		}
		
		document.getElementById("hdNativeAddress").value = getCheckedRadioValue('radNativeAddress');
		document.getElementById("hdPermanentAddress").value = getCheckedRadioValue('radPermanentAddress');
		document.getElementById("hdCurrentAddress").value = getCheckedRadioValue('radCurrentAddress');
		
		empWeightValidation = checkForDot(document.getElementById('empWeight').value,document.getElementById('empWeight'));
		empHeightValidation = checkForDot(document.getElementById('empHeight').value,document.getElementById('empHeight'));	
		empChestValidation = checkForDot(document.getElementById('empChest').value,document.getElementById('empChest'));
		if(!empWeightValidation)
		{
			alert(empPersonalInfoAlertMsgArr[5]);
			setFocusSelection('empWeight');
			return;
		} 
		if(!empHeightValidation)
		{
			alert(empPersonalInfoAlertMsgArr[6]);
			setFocusSelection('empHeight');
			return;
		} 
		if(!empChestValidation) 
		{
			alert(empPersonalInfoAlertMsgArr[7]);
			setFocusSelection('empChest');
		        return;
		}
		
	   	if(submitFlag)
	   	{
			if(empHeightValidation==true && empWeightValidation==true && empChestValidation==true)
			{		
					document.frmBF.action="hrms.htm?actionFlag=saveNewEmpInfoData";
					document.frmBF.submit();
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
			if(dotCount==0 && fieldValue.length>3){valueFlag=false;}
		}
		if(fieldId.name=='empHeight')
		{
			if(dotCount==0 && fieldValue.length>3){valueFlag=false;}
		}
		if(fieldId.name=='empChest')
		{
			if(dotCount==0 && fieldValue.length>3){valueFlag=false;}
		}
		return valueFlag;
	}	

	function getCheckedRadioValue(radioName)
	{
		var radioValue = "";
		objRadio = eval("document.getElementsByName(\""+ radioName +"\")");
		
		for (iter=0;iter<objRadio.length;iter++)
		{
			if (objRadio[iter].checked)
			{
				radioValue = objRadio[iter].value;
				break;			
			}
		}
		return 	radioValue;
	}

/************* On Relation / Caste Combo Change Event Populate Cmb Box *************/
	
	var cmbObjFlag=''  // This Object Store tha Combo flag and according to this flag next combo will populate
	var getNextComboData = false;  // This Object will send Next Request for the next combo dtls
	var tempCmbCastName='',tempCmbSubCastName='';

	function getNextCmbValue(cmbValue,flag)
	{
		cmbObjFlag=flag;
		if(cmbObjFlag==3)
		{
			getNextComboData=true;
			cmbObject=document.getElementById('emp_caste_id');			
		}		
		else if(cmbObjFlag==1)
		{	
			cmbObject = document.getElementById('emp_caste_id');			
			while(document.frmBF.emp_caste_id.length != 0) 
			{
				document.frmBF.emp_caste_id.remove(0);
			}
			while(document.frmBF.emp_sub_caste.length != 0) 
			{
				document.frmBF.emp_sub_caste.remove(0);
			}
			cmbSubCastObject = document.getElementById('emp_sub_caste');
			var optionCreate=document.createElement('option');
			optionCreate.text=empPersonalInfoAlertMsgArr[18];
			optionCreate.value='Select';			 										
			try
			{
			   	cmbSubCastObject.add(optionCreate,null); 	    						
			}
			catch(ex)
			{	   			 
				cmbSubCastObject.add(optionCreate);	   			 				 
			}
		}
		else
		{								
			while(document.frmBF.emp_sub_caste.length != 0) 
			{
				document.frmBF.emp_sub_caste.remove(0);
			}
			cmbObject = document.getElementById('emp_sub_caste');			
		}
		var optionCreate=document.createElement('option');
		optionCreate.text=empPersonalInfoAlertMsgArr[18];
		optionCreate.value='Select';			 										
		try
		{
		    	cmbObject.add(optionCreate,null); 	    						
		}
		catch(ex)
		{	   			 
			cmbObject.add(optionCreate);	   			 				 
		}		
		if(cmbValue.value != 'Select'  && cmbValue.value != 'select' && cmbValue.value!='')
		{					
			addOrUpdateRecord('populateCmbBox','getNextComboValue&cmbId='+cmbValue.value,new Array());	
		}
		else	
			return;
	}

	function getNextCmbValueOnTextForCast(value,flag)
	{
		if (value != 'Select'  && value != 'select' && value!='')
		{
			try
			{   
    			 xmlHttpCaste=new XMLHttpRequest();    
	    	}
			catch (e)
			{   // Internet Explorer    
				try
				{
      				xmlHttpCaste=new  ActiveXObject("Msxml2.XMLHTTP");      
      			}
		   		catch (e)
		    	{
		        	try
        			{
                   		xmlHttpCaste=new ActiveXObject("Microsoft.XMLHTTP");        
        			}
				   	catch (e)
				   	{				           	   	  
			    		return false;        
			      	}
			 	}			 
	        }        	
			var url = "hrms.htm?actionFlag=getNextComboValue&cmbId="+value;
			xmlHttpCaste.open("POST", encodeURI(url) , true);
				
			xmlHttpCaste.onreadystatechange = populateCmbBoxForCastId;
			xmlHttpCaste.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttpCaste.send(encodeURIComponent(null));		
		}
	}
	
	function getNextCmbValueOnText(value,flag)
	{		
		cmbObjFlag=flag;		
		if(value != 'Select'  && value != 'select' && value!='' && flag==2)
		{					
			addOrUpdateRecord('populateCmbBox','getNextComboValue&cmbId='+value,new Array());	
		}				 
	}
	
	function populateCmbBox()
	{
		if (xmlHttp.readyState == 4) 
		{
			if (xmlHttp.status == 200) 
			{
				var decXML = xmlHttp.responseText;				  					
				var XMLDoc = null;
				XMLDoc = getDOMFromXML(decXML);					
				if(XMLDoc!=null)
				{
					var id='';
					var cmbObject = '';
					id=XMLDoc.getElementsByTagName('Id'); 

					var valueStr='';
					valueStr=XMLDoc.getElementsByTagName('Value'); 					
					if(cmbObjFlag==1 || cmbObjFlag==3)
					{
						cmbObject = document.getElementById('emp_caste_id');							
					}
					else
					{						
						cmbObject = document.getElementById('emp_sub_caste');									
					}
					
					for ( var i = 0 ; i < id.length ; i++ ) // adding a new vlaue in combo
		     		{	     		     								
		     			value=valueStr[i].childNodes[0].text;	     				    
		     			textStr=id[i].childNodes[0].text;
			
		     			var optionCreate=document.createElement('option');
			 			optionCreate.text=value;
						optionCreate.value=textStr;			 					
						
						try
		   				{
		    				cmbObject.add(optionCreate,null); 	    						
		   				}
		 				catch(ex)
		   				{	   			 
		   					cmbObject.add(optionCreate);	   			 				 
		   				}
		           	}
				}	
				if(tempCmbSubCastName!='' && cmbObjFlag==2 && tempCmbSubCastName!="SELECT")
				{
					document.getElementById('emp_sub_caste').value = tempCmbSubCastName;
					tempCmbSubCastName='';
				}												
			}
		}
	}
	function populateCmbBoxForCastId()	
	{
		if (xmlHttpCaste.readyState == 4) 
		{
			if (xmlHttpCaste.status == 200) 
			{
				var decXML = xmlHttpCaste.responseText;				  					
				var XMLDoc = null;
				XMLDoc = getDOMFromXML(decXML);					
				if(XMLDoc!=null)
				{
					var id='';
					var cmbObject = '';
					id=XMLDoc.getElementsByTagName('Id'); 

					var valueStr='';
					valueStr=XMLDoc.getElementsByTagName('Value'); 					
					var cmbObject1 = document.getElementById('emp_caste_id');																	
					for ( var i = 0 ; i < id.length ; i++ ) // adding a new vlaue in combo
		     		{	     		     								
		     			value=valueStr[i].childNodes[0].text;	     				    
		     			textStr=id[i].childNodes[0].text;			
		     			var optionCreate=document.createElement('option');
			 			optionCreate.text=value;
						optionCreate.value=textStr;			 					
						
						try
		   				{
		    				cmbObject1.add(optionCreate,null); 	    						
		   				}
		 				catch(ex)
		   				{	   			 
		   					cmbObject1.add(optionCreate);	   			 				 
		   				}
		           	}
				}				
				if(tempCmbCastName!=''&& tempCmbCastName!="SELECT")
				{
					getNextCmbValueOnText(tempCmbCastName,2);	
					document.getElementById('emp_caste_id').value = tempCmbCastName; 
					tempCmbCastName='';
				}	
				else
					document.getElementById('emp_caste_id').options[0].selected = true;							
			}
		}	
	}
	
/*Contact Details JAVA Script********/
	var globalflagForDBRecordContact='';
	function addContactInTable()
	{
		if (xmlHttp.readyState == 4) 
		{
			if (xmlHttp.status == 200) 
			{			
				var displayContactFieldArray = new Array('emp_contact_type','emp_contact');
				addDataInTable('multipleAddContactTable','contactXML',displayContactFieldArray ,'editContact','deleteContact');		
				resetContact();
			}
		}
	}	

	function addContactNumber(btnValue)
	{
		var contactType=document.getElementById('emp_contact_type').value;	
		var contact=document.getElementById('emp_contact').value;
		var name='emp_contact';
		
		if(contactType=='' || contactType=='Select' || contactType=='null')
		{
			alert(empPersonalInfoAlertMsgArr[8]);
			document.getElementById("emp_contact_type").focus();
		}
		else if(contact=='' || contact=='null' || contact=='Select')
		{
			alert(empPersonalInfoAlertMsgArr[9]);	
			setFocusSelection(name);
		}
		else 
		{	
			var returnValue= contactValidation(); //Starts test for validation
			if(returnValue!='error')
			{
				if(btnValue=='AddContact')
				{
					var hdnEmpContactId = 0;
					addOrUpdateRecord('addContactInTable','addEmpContact&hdnEmpContactId='+hdnEmpContactId,new Array('emp_contact_type','emp_contact'));			
				}
				else
				{
					if(globalflagForDBRecordContact==true)
					{
						var hdnEmpContactId=document.getElementById('hdnEmpContactId').value;				
						addOrUpdateRecord('saveContactInTable','addEmpContact&hdnEmpContactId='+hdnEmpContactId,new Array('emp_contact_type','emp_contact'));
					}
					else
					{
						addOrUpdateRecord('saveContactInTable','addEmpContact',new Array('emp_contact_type','emp_contact'));		
					}
				}
			}
			else
			{
				setFocusSelection(name);
			}
		}
	}
//Starts test for validation
	function contactValidation()
	{
		var num_rule="1230456789";
		var empContactType=document.frmBF.emp_contact_type;				
		var id= empContactType.value;
		var empContact=document.frmBF.emp_contact;				
		var empContactId= empContact.value;
		
		if(id=='emp_Contact_Mobile')			//FOR Mobile Number contact Validation
		{
			if(empContactId.length==10)
			{	
				for(var i=0;i<empContactId.length;i++)
				{
	     			var cchar=empContactId.charAt(i);
					if(num_rule.indexOf(cchar)==-1)
	    	   		{
	    	   			alert(empPersonalInfoAlertMsgArr[10]);
			          	return 'error';
		       		}
	    		} 
	    	}
	    	else 
	    	{
		    	alert(empPersonalInfoAlertMsgArr[11]);
		    	return 'error';
	    	}
		}
		if(id=='emp_Contact_Phone' || id=='emp_Contact_PhoneO')				//FOR Phone Number contact Validation
		{
			for(var i=0;i<empContactId.length;i++)
			{
     			var cchar=empContactId.charAt(i);
		        if(num_rule.indexOf(cchar)==-1)
    	   		{
        		  	alert(empPersonalInfoAlertMsgArr[12]);
		          	return 'error';
	      		}
	    	} 
		}
		if(id=='emp_Contact_Email' || id=='emp_Contact_EmailO') //FOR Email contact Validation
		{
			var result=validateEmail(empContact);
			if(result==false)
			{
				return 'error';
			}
		}
	}
//Ends test for validation

	function editDBContact(rowId)
	{
		globalflagForDBRecordContact=true;
		sendAjaxRequestForEdit(rowId, 'populateContact');
		document.frmBF.addContact.disabled=true;		
		document.frmBF.saveContact.disabled=false;		
	}
	
	function editContact(rowId)
	{
		globalflagForDBRecordContact=false;
		sendAjaxRequestForEdit(rowId,'populateContact');
		document.frmBF.addContact.disabled=true;		
		document.frmBF.saveContact.disabled=false;		
	}
	
	function populateContact()
	{
		if (xmlHttp.readyState == 4) 
		{	 	
			if (xmlHttp.status == 200) 
			{
			  	var decXMLContact = xmlHttp.responseText;				  	
				var xmlDOMContact = getDOMFromXML(decXMLContact);
				
				document.getElementById('emp_contact_type').value = getXPathValueFromDOM (xmlDOMContact, 'cmnLookupMst/lookupName');
				document.getElementById('emp_contact').value=getXPathValueFromDOM(xmlDOMContact, 'contactNumber');
				document.getElementById('hdnEmpContactId').value=getXPathValueFromDOM(xmlDOMContact, 'empContactId');
			}
		}
	}
	
	function deleteContact(rowId)
	{
		var flag = deleteRecord(rowId);	
		if(flag)
		{					
			if(document.getElementById('multipleAddContactTable').rows.length==1)
			{
				document.getElementById('multipleAddContactTable').style.display='none';
			}	
			resetContact();
		}
	}

	function deleteDBContact(rowId)
	{
		var flag = deleteDBRecord(rowId);	
		if(flag)
		{					
			if(document.getElementById('multipleAddContactTable').rows.length==1)
			{
				document.getElementById('multipleAddContactTable').style.display='none';
			}	
			resetContact();
		}
	}

	function saveContactInTable()
	{
		if (xmlHttp.readyState == 4) 
		{
			if (xmlHttp.status == 200) 
			{
				var displayContactFieldArray = new Array('emp_contact_type','emp_contact');
				if(globalflagForDBRecordContact==false)
				{
					updateDataInTable('contactXML', displayContactFieldArray);
				}
				else
				{
					updateDataInTable('addedcontactXML', displayContactFieldArray);
				}
				document.frmBF.addContact.disabled=false;		
				document.frmBF.saveContact.disabled=true;
				resetContact();	
			}
		}
	}
	
	function resetContact()
	{
		document.getElementById('emp_contact_type').value='';	
		document.getElementById('emp_contact').value='';
	}
/*End Contact Details JAVA Script********/	

/*Language Details JAVA Script********/
	var globalflagForDBRecord='';
	function addLangugeInTable()
	{
		if (xmlHttp.readyState == 4) 
		{
			if (xmlHttp.status == 200) 
			{			
				var displayFieldArray = new Array('emp_lang_known','emp_speak_proficiency','emp_read_proficiency','emp_write_proficiency');
				addDataInTable('multipleAddLangTable','langXML',displayFieldArray ,'editLanguage','deleteLanguage');		
				resetLanguage();
			}
		}
	}
	function addLangauge(btnValue)   // Script for Adding a Multiple Languge
	{
		var lang=document.getElementById('emp_lang_known').value;	
		var proficiency=document.getElementById('emp_speak_proficiency').value;
		var readProficiency=document.getElementById('emp_read_proficiency').value;
		var writeProficiency=document.getElementById('emp_write_proficiency').value;
		if(lang=='' || lang=='Select' || lang=='null')
		{
			alert(empPersonalInfoAlertMsgArr[13]);
			document.getElementById('emp_lang_known').focus();	
		}
		else if(proficiency=='' || proficiency=='null' || proficiency=='Select')
		{
			alert(empPersonalInfoAlertMsgArr[14]);	
			document.getElementById('emp_speak_proficiency').focus();
		}
		else if(readProficiency=='' || readProficiency=='null' || readProficiency=='Select')
		{
			alert(empPersonalInfoAlertMsgArr[15]);		
			document.getElementById('emp_read_proficiency').focus();
		}
		else if(writeProficiency=='' || writeProficiency=='null' || writeProficiency=='Select')
		{
			alert(empPersonalInfoAlertMsgArr[16]);	
			document.getElementById('emp_read_proficiency').focus();
		}
		else 
		{	
			var langStatus=checkForLanguageRec();
			if(langStatus)
			{
				if(btnValue=='Add')
				{	var srNo= 0;
					addOrUpdateRecord('addLangugeInTable','addEmpKnownLanguages&srNo='+srNo,new Array('emp_lang_known','emp_speak_proficiency','emp_read_proficiency','emp_write_proficiency'));			
				}
				else
				{
					if(globalflagForDBRecord==true)
					{
						var srNo=document.getElementById('srNo').value;				
						addOrUpdateRecord('saveLangaugeInTable','addEmpKnownLanguages&srNo='+srNo,new Array('emp_lang_known','emp_speak_proficiency','emp_read_proficiency','emp_write_proficiency'));
					}
					else
					{
						addOrUpdateRecord('saveLangaugeInTable','addEmpKnownLanguages',new Array('emp_lang_known','emp_speak_proficiency','emp_read_proficiency','emp_write_proficiency'));		
					}
				}
			}
			else
			{       
				alert(empPersonalInfoAlertMsgArr[17]);
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
		sendAjaxRequestForEdit(rowId,'populateLanguage');
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
				}
				catch(ex){document.getElementById('srNo').value='';}
				
				/*Change By Sunil For language*/
				var str = '';
				str= getXPathValueFromDOM(xmlDOM, 'cmnLanguageMstByLanguageId/lookupName');
				if(str==null || str=='')
					document.getElementById('emp_lang_known').value='Select';
				else
					document.getElementById('emp_lang_known').value=str;
				
				str='';
				str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMst/lookupName');
				if(str==null || str=='')
					document.getElementById('emp_speak_proficiency').value='';
				else
					document.getElementById('emp_speak_proficiency').value=str;
				
				str='';
				str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstReadProf/lookupName');
				if(str==null || str=='')
					document.getElementById('emp_read_proficiency').value='';
				else	
					document.getElementById('emp_read_proficiency').value=str;
				
				str='';
				str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstWriteProf/lookupName');	
				if(str==null || str=='')
					document.getElementById('emp_write_proficiency').value='';
				else
					document.getElementById('emp_write_proficiency').value=str;
			}
		}
	}
	function deleteLanguage(rowId)
	{
		var flag = deleteRecord(rowId);	
		if(flag)
		{					
			if(document.getElementById('multipleAddLangTable').rows.length==1)
			{
				document.getElementById('multipleAddLangTable').style.display='none';
			}	
			resetLanguage();
		}
	}
	
	function deleteDBLanguage(rowId)
	{
		var flag = deleteDBRecord(rowId);	
		if(flag)
		{					
			if(document.getElementById('multipleAddLangTable').rows.length==1)
			{
				trow=document.getElementById(rowId);
				trow.cells[1].innerText='0';
				document.getElementById('multipleAddLangTable').style.display='none';
			}
			else
			{
					trow=document.getElementById(rowId);
					trow.cells[1].innerText='0';
			}
			resetLanguage();
		}
	}
	
	function saveLangaugeInTable()
	{
		if (xmlHttp.readyState == 4) 
		{
			if (xmlHttp.status == 200) 
			{
				var displayFieldArray = new Array('emp_lang_known','emp_speak_proficiency','emp_read_proficiency','emp_write_proficiency');
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
		document.getElementById('emp_speak_proficiency').value='';
		document.getElementById('emp_read_proficiency').value='';
		document.getElementById('emp_write_proficiency').value='';
		document.frmBF.addLanguage.disabled=false;		
    	document.frmBF.saveLanguage.disabled=true;
		updateRow=null;
	}
	
	function checkForLanguageRec()
	{
		var currentLang='' ;
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
/*****End Of Language Details JAVA Script********/


	function openAppWindow(actionFlag)//IFMS
	{
		var userId = document.getElementById("userId").value;
		var href = "hrms.htm?actionFlag="+  actionFlag + "&userId="+ userId ;
		objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");			
	}

	/*function expandCollapse(divId,expandAgent)
	{
		if (document.getElementById(expandAgent) != null)
		{
			var imgSrcPath = new String(document.getElementById(expandAgent).src);
	
			if (imgSrcPath.indexOf("expand.gif") != -1)
			{
				document.getElementById(expandAgent).src = imgSrcPath.replace("expand.gif","collapse.gif");
				document.getElementById(divId).style.display='';	
			}
			else
			{
				document.getElementById(expandAgent).src = imgSrcPath.replace("collapse.gif","expand.gif");
				document.getElementById(divId).style.display='none';
			}
		}
	}*/
	
	function checkNumberForOnlyOneDot(fieldValue)
	{
		var num;
		num = window.event.keyCode;
		if ((eval(num)<46||eval(num)==47)||eval(num)>58)
		{
			return false;
		}	
		else
		{
	 		if (eval(num)==46)
		 	{
			 	var dotCount=0;
		 		var length = fieldValue.length;
			 	if (length == 0)
			 		return false;	
			 	for(var i=0;i<length;i++)
				{
					var keyId = fieldValue.charCodeAt(i);		
					if(keyId==46){dotCount=parseInt(dotCount)+1;}
				}
				if(dotCount>=1)
				{
					return false;
				}
			}
			return true;
		}
	}
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

/********Starts Address Show Script ***********/
	function showHideAddress(addressType,btnValue)
	{
		if(addressType==1){
			if(btnValue=='N'){
				document.getElementById('row_personNativeAddress').style.display='';			
			}
			else{
				closeAddress('nativePlaceAddress');
				document.getElementById('row_personNativeAddress').style.display='none';					
			}
		}	
		if(addressType==2){
			if(btnValue=='N'){
				document.getElementById('row_personPermanentAddress').style.display='';
			}
			else{
				closeAddress('permanentPlaceAddress');
				document.getElementById('row_personPermanentAddress').style.display='none';					
			}
		}
		if(addressType==3){
			if(btnValue=='N'){
				document.getElementById('row_personCurrentAddress').style.display='';			
			}
			else{
				closeAddress('currentPlaceAddress');	
				document.getElementById('row_personCurrentAddress').style.display='none';					
			}
		}
	}
/******** End Address Show Script***********/	