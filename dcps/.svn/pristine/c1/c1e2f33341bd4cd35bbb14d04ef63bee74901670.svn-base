function saveEditDataForm()
{
	var submitFlag = false;  
	var fieldArray = new Array('emp_last_name','emp_first_name','gu_emp_last_name','gu_emp_first_name','emp_dob','Religion','Category','emp_mother_tongue','Marital_Status','Nationality','emp_Phone_std','emp_Phone_Num','emp_Mobile_No','emp_email');
	var submitFlag = validateSpecificFormFields(fieldArray); 

	if(submitFlag==true)
	{
		var contactStatus=validateEmpContactDtls();
		if (contactStatus)
		{
			document.frmBF.action="hrms.htm?actionFlag=saveNewEmpData";
			document.frmBF.submit();
		}
	}
}

function validateEmpContactDtls()
{
	var empMobileContact=document.frmBF.emp_Mobile_No;				
	var MobileValue= empMobileContact.value;
	if(MobileValue.length!=0)
	{
		if(MobileValue.length!=10)
		{
			alert(ChangeEmpProfileAlertMsgArr[2]);
			setFocusSelection('emp_Mobile_No');
			return false;
		}
	}
	return true;
}

/************* On Relation / Caste Combo Change Event Populate Cmb Box *************/
	
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
			document.frmBF.emp_caste_id.length=1;
			document.frmBF.emp_sub_caste.length=1;
			
			cmbSubCastObject = document.getElementById('emp_sub_caste');
			var optionCreate=document.createElement('option');
		}
		else if(cmbObjFlag==2)
		{
			tempCmbSubCastName ="";
			document.frmBF.emp_sub_caste.length=1;
		}
		
		var optionCreate=document.createElement('option');
		if(cmbValue.value != 'Select'  && cmbValue.value != 'select' && cmbValue.value!='')
		{					
			addOrUpdateRecord('populateCmbBox','getNextComboValueData_WF&cmbId='+cmbValue.value,new Array());	
		}
		else	
			return;
	}
	function getNextCmbValueOnTextForCast(value,flag)
	{
		if (value != 'SELECT'  && value != 'Select' && value != 'select' && value!='')
		{
			try
			{   
	    			 xmlHttp=new XMLHttpRequest();    
		    }
			catch (e)
			{    // Internet Explorer    
				try
				{
      					xmlHttp=new  ActiveXObject("Msxml2.XMLHTTP");      
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
			var url = "hrms.htm?actionFlag=getNextComboValueData_WF&cmbId="+value;
			xmlHttp.open("POST", encodeURI(url) , true);
				
			xmlHttp.onreadystatechange = populateCmbBoxForCastId;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));		
		}
	}
	
	function populateCmbBoxForCastId()	
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
				if(tempCmbCastName!='' && tempCmbCastName!="SELECT")
				{
					getNextCmbValueOnText(tempCmbCastName,2);	
					document.getElementById('emp_caste_id').value = tempCmbCastName; 
					tempCmbCastName='';
				}								
			}
		}	
	}
	function getNextCmbValueOnText(value,flag)
	{		
		cmbObjFlag=flag;		
		if(value != 'SELECT' && value != 'Select'  && value != 'select' && value!='' && flag==2)
		{					
			addOrUpdateRecord('populateCmbBox','getNextComboValueData_WF&cmbId='+value,new Array());	
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
							
				if(tempCmbSubCastName!='' && tempCmbSubCastName!="SELECT" && cmbObjFlag==2)
				{
					document.getElementById('emp_sub_caste').value = tempCmbSubCastName;
					tempCmbSubCastName='';
				}												
			}
		}
	}

function closeWindow()
{
	document.frmBF.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	document.frmBF.submit();
}	

function onLoadDataForChangeEmpProfile()
{
	if(langName !='' && langName !=null && langName !='null')
		document.frmBF.emp_mother_tongue.value = langName;
		
	if(tempCmbCastName!='' || tempCmbCastName!=null || tempCmbCastName!='null')
	{
		window.setTimeout("getNextCmbValueOnTextForCast(tempCmbEmpReligionName,1)",1000);
	}			
 	
	countAge(birthDate,"emp_age","value"); 
	
	if(countryCode!='' && countryCode!=null && countryCode!='null')
	{
		document.frmBF.Nationality.value = countryCode;
	}
	else
		document.frmBF.Nationality.value = "1";
		
	if(empMobileNo!='' && empMobileNo!='0')
	{
		document.frmBF.emp_Mobile_No.value = empMobileNo;
	}
}

/* START----JAVA SCRIPT FUNTIONS FOR APPROVE EMPLOYEE PROFILE DTLS */
function onLoadDataForApproveChangeEmpProfile()
{
	document.getElementById('target_uploadphotoAttachment').style.display='none';
	document.getElementById('formTable1photoAttachment').firstChild.firstChild.style.display='none';
		
	document.getElementById('target_uploadAprPhotoAtchmnt').style.display='none';
	document.getElementById('formTable1AprPhotoAtchmnt').firstChild.firstChild.style.display='none';
		
	document.getElementById('tabDivId').style.display='';
	document.getElementById('msg').style.display='none';
	
	if(engLookupDescApr==null || engLookupDescApr=='null' || engLookupDescApr=='')
	{
		document.getElementById("aprEmpPersonalDtlsTbl").style.display='none';
		document.getElementById("notAvlblMsgId").style.display='';
		
		document.getElementById("aprPhotoAtchmntTr").style.display='none';
		document.getElementById("aprPhotoAtchmntMsgTr").style.display='';
	}
	if(!empContactDtls || empContactDtls == false || empContactDtls == 'false')
	{
		document.getElementById("aprEmpContactDtlsTblId").style.display='none';
		document.getElementById("aprCntcNotAvlblMsgId").style.display='';
	}
}