function com(cmb)
{							
	var id=cmb.options[cmb.selectedIndex].text;				
	var id=cmb.value;	
	var comboSubType=document.getElementById('TypeOfCoCurricular').length;	
	for(i=1;i<comboSubType;i++)
	{
		lgth = document.getElementById("TypeOfCoCurricular").options.length -1;
		document.getElementById("TypeOfCoCurricular").options[lgth] = null;
	}	
	if(id=='Select' || id==''){return;}
	try
	{   
		xmlHttp=new XMLHttpRequest();    
	}
	catch (e)
	{    // Internet Explorer    
		try
		{
			xmlHttp=new 
			ActiveXObject("Msxml2.XMLHTTP");      
		}
		catch (e)
		{
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
			}
			catch (e)
			{
				alert(CoCurricularAlertMsgArr[0]);        
				return false;        
			}
		}			 
	}        	
	var url = "hrms.htm?actionFlag=CoCurricularDetails&flag=getSubTypeOfCoCurricular&cmbid="+id;
	xmlHttp.open("POST", encodeURI(url) , true);

	xmlHttp.onreadystatechange = processResponse;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}
function processResponse()
{					    							
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{          		        				
			var textStr;						
			var comboSubType=document.getElementById('TypeOfCoCurricular'); 
			var xmlStr = xmlHttp.responseText;  // taking an XML file					    	
			var XMLDoc=getDOMFromXML(xmlStr);					    	  					    					    	
			var SubCoCurrStr = XMLDoc.getElementsByTagName('SubTypeOfCoCurricular');      
			var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('SubTypeOfCoCurricularID'); 					    	     		     							
			for (var i=comboSubType.length;i>0;i--)  // removing a previous value of combo
			{	     				     					
				comboSubType.remove(comboSubType.value);
			}	
			for ( var i = 0 ; i < SubCoCurrStr.length ; i++ ) // adding a new vlaue in combo
			{	     		     								
				value=SubCoCurrStr[i].childNodes[0].text;	     				    
				textStr=SubCoCurrStr_ID[i].childNodes[0].text;
				if(value=='--Select--')
				{
					value='--'+CoCurricularAlertMsgArr[1]+'--'
				}
				var y=document.createElement('option');
				y.text=value;
				y.value=textStr;			 					
				try
				{
					comboSubType.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
					comboSubType.add(y);	   			 				 
				}     						     					
			}
			if(setCombo!=-1)
			{
				document.frmCoCurricular.TypeOfCoCurricular.value=setCombo;
				setCombo=-1;
			}
		}
	}
}											
function SubmitAction()
{			  
	if(submiteFlag==false)
	{
		alert(CoCurricularAlertMsgArr[2]);
	}
	else
	{
		document.frmCoCurricular.action = "hrms.htm?actionFlag=CoCurricularDetails&flag=SubmitCoCurricularDtls&workFlowEnabled="+ workFlowEnabled; // IFMS
		document.frmCoCurricular.submit();
	}			   
}
function resetData(rowNum) 
{	
	document.frmCoCurricular.CoCurricular.value='Select';										
	document.frmCoCurricular.Competed_at.value='Select';

	RelationCombo=document.frmCoCurricular.year_combo;
	RelationCombo.options[0].selected=true;	

	document.getElementById('Specialachievement').value = '';			    

	document.frmCoCurricular.TypeOfCoCurricular.value='Select';
	document.getElementById("TypeOfCoCurricular").length=1;
	updateRow = null
	dbRecordForEdit=false;
	removeRowFromTableattachmentBiometricCocurricular(rowNum,'frmCoCurricular'); 
	globalFlagDeleteDisable = false;
	document.frmCoCurricular.Add.disabled=false; 
	document.frmCoCurricular.Save.disabled=true;   	

	if (document.getElementById('coCurricularTable') != null)
	{
		var rows = document.getElementById('coCurricularTable').rows.length;

		if (rows > 1 && document.getElementById('coCurrNoRecords')!=null)
			document.getElementById('coCurrNoRecords').style.display='none';
	}

}  
function editRecord(rowId,rowNum) 
{					
	editRowId=rowId;
	dbRecordForEdit=false;
	document.frmCoCurricular.Save.disabled=false;										
	document.frmCoCurricular.Add.disabled=true;	
	sendAjaxRequestForEditAttachment(rowId, 'populateForm','attachmentBiometricCocurricular',rowNum);
	globalFlagDeleteDisable = true;	
}	
function editRecordForDB(rowId,rowNum)
{
	editRowId=rowId;
	dbRecordForEdit=true;
	document.frmCoCurricular.Save.disabled=false;		
	document.frmCoCurricular.Add.disabled=true;								
	sendAjaxRequestForEditAttachment(rowId, 'populateForm','attachmentBiometricCocurricular',rowNum);
	globalFlagDeleteDisable = true;	
}
function deleteCoCurricularRecord(rowId)
{
	if(globalFlagDeleteDisable == false)
	{		
		submiteFlag=true;			
		var answer = deleteRecord(rowId);															
		var num = document.getElementById('coCurricularTable').rows.length;
		if(num==1){
			document.getElementById('coCurricularTable').style.display='none';																				
		}						
	}
	else
	{
		alert(CoCurricularAlertMsgArr[4]);
	}
}			
function addRecord() 
{   		  	    
	if (xmlHttp.readyState == 4)
	{ 	
		if (xmlHttp.status == 200) 
		{		
			submiteFlag=true; 
			document.getElementById('srNo').value=document.getElementById('coCurricularTable').childNodes[0].childNodes.length-dbRows;	
			dbRecordForEdit=false;			
			removeSelect();//Added by Sunil
			var displayFieldArray = new Array('CoCurricular','TypeOfCoCurricular','hdnCompeteted','year_combo','Specialachievement');	//Added by Sunil				
			var rowNum = addDataInTableAttachment('coCurricularTable', 'encXML', displayFieldArray, 'editRecord','deleteCoCurricularRecord','');
			resetData(rowNum);			   	    	  															   	    	
		}
	}			   	
}	
		
function updateRecord() 
{		
	if (xmlHttp.readyState == 4) 
	{ 				
		if (xmlHttp.status == 200) 
		{
			submiteFlag=true;
			document.getElementById('srNo').value=updateSrNo;			
			removeSelect(); ////Added by Sunil
			var displayFieldArray = new Array('CoCurricular','TypeOfCoCurricular','hdnCompeteted','year_combo','Specialachievement');//Added by Sunil
			var rowNum;							
			if(dbRecordForEdit==false)														
			{
				rowNum = updateDataInTableAttachment('coCurricularTable','encXML', displayFieldArray);
			}
			else
			{
				rowNum = updateDataInTableAttachment('coCurricularTable','addedencXML', displayFieldArray);
			}
			globalFlagDeleteDisable = false;
			document.frmCoCurricular.Save.disabled=true;	
			resetData(rowNum);													
		}
	}
}
function coCurrDeleteDBRecord (rowId)
{
	if(globalFlagDeleteDisable == false)
	{
		var flag = deleteDBRecord(rowId);
		if(flag)
		{
			submiteFlag=true;
			var num = document.getElementById('coCurricularTable').rows.length;																
			var rowLen=num;
			for(var i=0;i<num;i++)	
			{
				trow= document.getElementById('coCurricularTable').rows[i];																																		
				try{
					if(trow.style.display=='none')
					{	
						rowLen=rowLen-1;								
					}
				}catch(e){break;}	
			}								
			if(rowLen==1)
			{
				document.getElementById('coCurricularTable').style.display='none';																				
			}
		}
	}
	else
	{
		alert(CoCurricularAlertMsgArr[4]);
	}
}

function populateForm() 
{				
	if (xmlHttp.readyState == 4) 
	{	 	
		if (xmlHttp.status == 200) 
		{
			var decXML = xmlHttp.responseText;				  	
			var xmlDOM = populateAttachment(decXML,'frmCoCurricular');

			document.getElementById('srNo').value = getXPathValueFromDOM (xmlDOM, 'srNo');
			document.getElementById('CoCurricular').value = getXPathValueFromDOM (xmlDOM, 'cmnLookupMstByCocurricularId/lookupName');										
			document.getElementById('year_combo').value = getXPathValueFromDOM (xmlDOM, 'yearId');    						

			if (getXPathValueFromDOM (xmlDOM, 'specialAchievement')  != null && getXPathValueFromDOM (xmlDOM, 'specialAchievement')  != '')
			{
				var specialAchievement = getXPathValueFromDOM (xmlDOM, 'specialAchievement'); 
				for(var i=0;i<specialAchievement.length;i++)
				{
					specialAchievement = specialAchievement.replace("&amp;#x0D;","\n");
					specialAchievement = specialAchievement.replace("&#x0D;","\n");
				}
				document.getElementById('Specialachievement').value = specialAchievement; 
			}

			setCombo = getXPathValueFromDOM(xmlDOM, 'cmnLookupMstBySubCocurricularId/lookupName');    						
			
			//Added by Sunil
			var str=getXPathValueFromDOM (xmlDOM, 'cmnLookupMstByCompetedAtId/lookupName');		
			if(str==-1  || str=='null'|| str==null || str=='SELECT'){str='Select';}
			document.getElementById('Competed_at').value = str;    											
			
			com(document.frmCoCurricular.CoCurricular);
		}   	    		   		    
	}
}

function startupAjaxFormValidation(buttonName)
{			
	if(buttonName == 'Close')
	{
		if(workFlowEnabled)
		{
			document.frmCoCurricular.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
			document.frmCoCurricular.submit();
		}
		else
		{
			window.close();
		} 
	}
	else
	{	
		var totalArray=new Array('CoCurricular','Specialachievement','Competed_at','TypeOfCoCurricular','year_combo');										 
		var statusValidation = validateSpecificFormFields(new Array('CoCurricular','TypeOfCoCurricular','year_combo'));	//Added by Sunil													
		if(statusValidation == true)
		{		
			var checkFlag=compaireWithDOB(document.getElementById("year_combo").value,empDOB);
			if(checkFlag)
			{												
				if(buttonName == 'Add')
				{		
					var num=0;																	
					addOrUpdateRecord('addRecord', 'CoCurricularDetails&flag=AddCoCurricularlDtls&srno='+num,new Array('CoCurricular','Specialachievement','Competed_at','TypeOfCoCurricular','year_combo'));
				}
				else
				{
					var	num = document.getElementById('srNo').value;							
					addOrUpdateRecord('updateRecord','CoCurricularDetails&flag=AddCoCurricularlDtls&srno='+num,new Array('CoCurricular','Specialachievement','Competed_at','TypeOfCoCurricular','year_combo'));
				}	
				 getFieldGroupObj(document.getElementById("coCurricularTable"));	
			}
			else
			{
				alert(CoCurricularAlertMsgArr[5]);
				document.getElementById('year_combo').focus();
			}											
		}			
	}
} 		

function showme()
{				
	document.getElementById('coCurricularTable').style.display='none';
}

function openAppWindow(actionFlag)  
{
	var userId = document.getElementById("userId").value;
	var href = "hrms.htm?actionFlag="+  actionFlag + "&userId="+ userId ;
	objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");			
}

function removeSelect()//Added by Sunil
{
	if(document.getElementById('Specialachievement').value=='')
	{
		document.getElementById('Specialachievement').value='-';
	}	 
	if(document.getElementById('Competed_at').value=='Select')
	{
		document.getElementById('hdnCompeteted').value='-';
	} 
	else
	{
		var selectedItem=document.frmCoCurricular.Competed_at;
		var option=selectedItem.options[selectedItem.selectedIndex].text;
   		document.getElementById('hdnCompeteted').value=option;
	}
}