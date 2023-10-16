function SubmitAction()
{  		
	var flags=messageDisplay();			
	if(flags)
	{		  			
		var ans=true;	
		if(ans)
		{
			var num = document.getElementById('QuaDtlsTab').rows.length;											
			for(var i=1;i<num;i++)
			{							
				trow=document.getElementById('QuaDtlsTab').rows[i];
				rowId=trow.id;
				try {				
					var hField = rowId.substring(3, rowId.length);
					var xmlFileName = document.getElementById(hField).value;
					if(xmlFileName.indexOf("$/$")!=-1)
					{
						rowIdArr=xmlFileName.split("$/$");
						document.getElementById(hField).value=rowIdArr[0];
					}							
				}catch(e){}
			}
			for (x in deleteArr)
			{
				var xmlFileName = deleteArr[x].toString();
				if(xmlFileName.indexOf("$/$")!=-1)
				{
					rowIdArr=xmlFileName.split("$/$");
					deleteArr[x]=rowIdArr[0];
				}					
			}
			if(workFlowEnabled)								
			{
				document.frmEdu.action = "hrms.htm?actionFlag=EducationDtls&flag=SubmitEducationDtls&draft=0&deleteEduDtls="+deleteArr;
				document.frmEdu.submit();			
			}
			else
			{
				document.frmEdu.action = "hrms.htm?actionFlag=EducationDtls&workFlowEnabled="+ workFlowEnabled + "&flag=SubmitEducationDtls&draft=0&deleteEduDtls="+deleteArr; //IFMS
				document.frmEdu.submit();				   
			}
		}
	}
}

function SubmitSaveAsDraft()
{			
	if(confirm(EducationDtlsAlertMsgArr[1])==true)    
	{
		var num = document.getElementById('QuaDtlsTab').rows.length;											
		for(var i=1;i<num;i++)
		{							
			trow=document.getElementById('QuaDtlsTab').rows[i];
			rowId=trow.id;
			try {				
				var hField = rowId.substring(3, rowId.length);
				var xmlFileName = document.getElementById(hField).value;
				if(xmlFileName.indexOf("$/$")!=-1)
				{
					rowIdArr=xmlFileName.split("$/$");
					document.getElementById(hField).value=rowIdArr[0];
				}							
			}catch(e){}
		}
		for (x in deleteArr)
		{
			var xmlFileName = deleteArr[x].toString();
			if(xmlFileName.indexOf("$/$")!=-1)
			{
				rowIdArr=xmlFileName.split("$/$");
				deleteArr[x]=rowIdArr[0];
			}			
		}		
		document.frmEdu.action="hrms.htm?actionFlag=EducationDtls&flag=SubmitEducationDtls&draft=1&deleteEduDtls="+deleteArr;
		document.frmEdu.submit();
	}
}		
function showme(flag)
{							
	if(flag==1)
	{
		document.getElementById('QuaApproveDtlsTab').style.display='none';
	}
	else 
	{
		document.getElementById('RequestTable').style.display='none';
	}
}
function startupAjaxFormValidation(buttonName)
{
	if(buttonName == 'Close')
	{
		document.frmEdu.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
		document.frmEdu.submit();
	}
	else
	{
		document.frmEdu.Submit.disabled=false;
		if (workFlowEnabled)document.frmEdu.btnSave_Draft.disabled=false;	//IFMS
		var totalArray=new Array('Qualification','SubQualification','UniBoard','yrpassing','unit','Marks');				
		var statusValidation =  Education_validateSpecificFormFields(totalArray);					
		
		if(statusValidation == 'error'){}
		else if(statusValidation)
		{						
			statusValidation = checkForDot(document.getElementById('Marks').value);
			if(!statusValidation)
			{												
				alert(EducationDtlsAlertMsgArr[5]);			
			}	
			else
			{		
				document.getElementById('errorMessage').style.display='none';	
				if(!workFlowEnabled)//IFMS
				{
					var submitFlag = false; 
					EduCombo=document.getElementById('Qualification');													
					showEduElement=EduCombo.value;
					if(showEduElement=='edu_Higher_Secondary_School'  || showEduElement== 'edu_Secondary_School' || showEduElement=='edu_Primary_School')
					{
						var fieldArray = new Array('Qualification','SubQualification','UniBoard','yrpassing','unit','Marks');
						var submitFlag = validateSpecificFormFields(fieldArray); 
					}
					else
					{
						var fieldArray = new Array('Qualification','SubQualification','courseCategory','UniBoard','yrpassing','unit','Marks');
						var submitFlag = validateSpecificFormFields(fieldArray); 
					}

				}
				else {var submitFlag =true;}
				if(submitFlag)
				{
					var checkFlag=compaireWithDOB(document.getElementById("yrpassing").value,empDOB);
					if(checkFlag)
					{
					startProcess();
					if(buttonName == 'Add')
					{
						var num=0;
						addOrUpdateRecord('addRecord','EducationDtls&flag=AddOrEditEducationDtls&workFlowEnabled='+ workFlowEnabled +'&srno='+num,new Array('Qualification','SubQualification','UniBoard','yrpassing','unit','Marks','courseCategory','discipline'));	// IFMS			 
					}	
					else
					{
						trow= document.getElementById(editRowId);														
						if(draftArrCount != -1)
						{						  		
							var x;
							for (x in draftArr)
							{																	
								if(draftArr[x] == editRowId)
								{																		 
									draftArr.splice(x,1);							
									if(draftArrCount==0){draftArrCount=-1;}
									else {draftArrCount = draftArrCount-1;}
								}
							}										  		
						}							  	
						var num=document.getElementById('srNo').value;
						if(isNaN(num)){num=0;}							

						// IFMS
						if(workFlowEnabled)
						{   
							addOrUpdateRecord('updateRecord','EducationDtls&flag=AddOrEditEducationDtls&workFlowEnabled='+ workFlowEnabled +'&srno='+num,new Array('Qualification','SubQualification','UniBoard','yrpassing','unit','Marks','courseCategory','discipline')); // IFMS
						}
						else 
							addOrUpdateRecord('saveDataInTable','EducationDtls&flag=AddOrEditEducationDtls&workFlowEnabled='+ workFlowEnabled +'&srno='+num,new Array('Qualification','SubQualification','UniBoard','yrpassing','unit','Marks','courseCategory','discipline')); // IFMS

						parentId=0;
						dbRecord=0;
					}
					}
					else
					{
						 alert(EducationDtlsAlertMsgArr[26]);
						 document.getElementById('yrpassing').focus();
					}
				}	
			}												
		}	
	}		
} 		
function saveAsDraft()
{				
	if(globalFlagDeleteDisable == true)
	{										
		var num=document.getElementById('srNo').value;
		if(draftFlag == 3){draftFlag = 4;}														
		var chkArr = new Array('QualificationCmb','SubQualificationCmb','UniBoard','yrpassingCmb','unitCmb','Marks','courseCategoryCmb','disciplineCmb');
		addOrUpdateRecord('updateRecord','EducationDtls&flag=EducationSaveAsDraft&srno='+num,chkArr);
	}
	else 
	{									
		var	num = 0;
		draftFlag = 1;		 						
		addOrUpdateRecord('addRecord','EducationDtls&flag=EducationSaveAsDraft&srno='+num,new Array('Qualification','SubQualification','UniBoard','yrpassing','unit','Marks','courseCategory','discipline'));
	}

}	
function MyCmbFieldValue(chkArr)
{
	for (var i=0;i<chkArr.length;i++)
	{	     	
		var val = document.getElementById(chkArr[i]).value;
		var field = document.getElementById(chkArr[i]);
		if(field.type == 'select-one')
		{
			if(val=='Select' || val=='select' || val=='')
			{
				document.getElementById(chkArr[i]+'Cmb1').value='-';	
			}
			else
			{
				document.getElementById(chkArr[i]+'Cmb1').value=field.options[field.selectedIndex].text;
			}
		}
		else
		{
			if(val=='' || val=='null')
			{
				document.getElementById(chkArr[i]+'Cmb1').value='-';	
			}
			else
			{
				document.getElementById(chkArr[i]+'Cmb1').value=val;
			}
		}
	}
}
function resetData(rowNum) 
{			
	document.getElementById('errorBox').value='';		
	document.getElementById('errorMessage').style.display='none';
	
	removeRowFromTableattachmentBiometricEducation(rowNum,'frmEdu');
	RelationCombo=document.getElementById('yrpassing');
	RelationCombo.options[0].selected=true;	
	document.getElementById('yrpassing').value = 'Select';
	document.getElementById('UniBoard').value = '';			        			    
	document.frmEdu.Marks.value='';		
	document.getElementById('discipline').value='Select';
	document.getElementById('courseCategory').value='Select';
	document.frmEdu.unit.value='Select';				
	document.frmEdu.Qualification.value='Select';

	var discipline =document.getElementById('discipline');
	for (var i=discipline.length;i>=0;i--)
	{	     				     					
		discipline.remove(discipline.value);
		discipline.remove(i);
	}
	var disciplineAdd=document.createElement('option');
	disciplineAdd.text='--'+EducationDtlsAlertMsgArr[2]+'--';
	disciplineAdd.value='Select';
	try
	{
		discipline.add(disciplineAdd,null); 	    						
	}
	catch(ex)
	{	   			 
		discipline.add(disciplineAdd);	   			 				 
	} 
	var z=document.getElementById('SubQualification');				
	for (var i=z.length;i>=0;i--)
	{	     				     					
		z.remove(z.value);
		z.remove(i);
	}   				
	var y=document.createElement('option');
	y.text='--'+EducationDtlsAlertMsgArr[2]+'--';
	y.value='Select';
	try
	{
		z.add(y,null); 	    						
	}
	catch(ex)
	{	   			 
		z.add(y);	   			 				 
	}				
	if(globalFlagDeleteDisable==true) 
	{
		globalFlagDeleteDisable = false;
		document.frmEdu.Save.disabled=true;											
		document.frmEdu.Add.disabled=false;
	}  
	document.getElementById('diciplineId').style.display='none';					
	resetAllFlag();
	dbRecordUpdateOrDelete=false;  

}  

function resetAllFlag(){updateRow = null;}

function editRecord(rowId,rowNum) 
{		
	eprofileRowNum=rowNum;			
	var x,flag=0;
	var saveToAddMissingData='';			
	try {				
		var hField = rowId.substring(3, rowId.length);
		var xmlFileName = document.getElementById(hField).value;
		saveToAddMissingData=xmlFileName;
		rowIdArr=xmlFileName.split("$/$");
		document.getElementById(hField).value=rowIdArr[0];								
	}catch(e){}	
	for(x in dbRowsId)
	{
		var ojbOfdbRowsId = dbRowsId[x].toString();				
		if(rowId==ojbOfdbRowsId)
		{					
			flag=1;
		}		
	}	

	if(flag==1)
	{
		dbRecordUpdateOrDelete = true;
	}
	else			
	{
		dbRecordUpdateOrDelete = false;
	}
	var x1;
	for (x1 in mappingArr)
	{
		var map =mappingArr[x1].toString();													
		var v1=map.split("/");				
		if(v1[1] == rowId)
		{
			dbRecord=1;
			dbRowId=v1[0];					
		}
	}						
	editRowId=rowId;		
	var x,flag1=0;
	for (x in draftArr)
	{																	
		if(draftArr[x] == rowId)
		{										 
			flag1=1;
		}
	}
	if(flag1==1){}
	else 			
	{
		draftFlag = 3;				
	}						
	document.frmEdu.Save.disabled=false;													
	document.frmEdu.Add.disabled=true;
	sendAjaxRequestForEditAttachment(rowId, 'populateForm','attachmentBiometricEducation',rowNum);
	if(saveToAddMissingData!='')
	{
		document.getElementById(hField).value=saveToAddMissingData;
	}
	globalFlagDeleteDisable = true;				
}

function deleteEmpEducationRecordFromDB(rowId)
{

	deleteRowId=rowId;
	var delId=rowId.substring(9, rowId.length);
	try{   
		xmlHttp=new XMLHttpRequest();    
	}
	catch (e)
	{    // Internet Explorer    
		try{
			xmlHttp=new 
			ActiveXObject("Msxml2.XMLHTTP");      
		}
		catch (e){
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
			}
			catch (e)
			{
				alert(EducationDtlsAlertMsgArr[3]);       
				return false;        
			}
		}			 
	}	
	var url = "hrms.htm?actionFlag=EducationDtls&flag=deleteEmpEducationDtls&delid="+delId;    
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange =message;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}	

function deleteEmpEducationRecord(rowId,flagDelete)
{					
	document.frmEdu.Submit.disabled=false;
	document.frmEdu.btnSave_Draft.disabled=false;					 	
	if(!globalFlagDeleteDisable)
	{								
		var showPanchTableIdRowLength = document.getElementById('QuaDtlsTab').rows.length;
		var showPanchTableIdRowLengthInt = parseInt(showPanchTableIdRowLength);
		showPanchTableIdRowLengthInt--;
		var delStr1 = rowId.substring(3, rowId.length);
		delStr = document.getElementById(delStr1).value;						
		var answer = deleteRecord(rowId);								
		if(answer)
		{							
			/***Mapping Array Code for **/								
			var x1,dbFalg=0;																
			for (x1 in mappingArr)
			{
				var map =mappingArr[x1].toString();									
				var v1=map.split("/");
				if(v1[1]==rowId)
				{										
					var x 
					for(x in dbRowsSrNo)
					{
						var getSrNo = dbRowsSrNo[x].toString();		
						var srNoObj=getSrNo.split('/');										
						if(v1[0] == srNoObj[0])
						{
							trow= document.getElementById(srNoObj[1]);																								
							trow.childNodes[9].style.display='';
							dbFalg=1;
							mappingArr.splice(x1,1);												
						}
					}
					if(dbFalg==0)
					{
						trow= document.getElementById(v1[0]);
						trow.childNodes[9].style.display='';
						mappingArr.splice(x1,1);
					}
				}
			}
			/**End of Mapping Code **/												
			/***For Draft Counting ****/							
			if(draftArrCount != -1)
			{						  		
				var x;
				for (x in draftArr)
				{																	
					if(draftArr[x] == rowId)
					{										 
						draftArr.splice(x,1);							
						if(draftArrCount==0){draftArrCount=-1;}
						else {draftArrCount = draftArrCount-1;}
					}
				}										  		
			}				
			/***End of Draft Counting***/

			if(document.getElementById("QuaApproveDtlsTab").rows.length==1)
			{
				document.getElementById("QuaApproveDtlsTab").style.display='none';
			}
			if(document.getElementById("QuaDtlsTab").rows.length==1)
			{
				document.getElementById("QuaDtlsTab").style.display='none';
			}
			if(flagDelete==1)
			{						
				deleteEmpEducationRecordFromDB(rowId);flagDelete=0;
			}
		}															
	}
	else
	{
		alert(EducationDtlsAlertMsgArr[6]);       
	}			
}			
function addRecord() 
{   		  	    
	if (xmlHttp.readyState == 4)
	{ 				  		
		if (xmlHttp.status == 200) 
		{ 							  				  		
			count=parseInt(count)+1;	
			if(draftFlag == 1)
			{
				draftArrCount= draftArrCount+1;  		
				draftArr[draftArrCount] = 'rowencXML'+count;		  			
				draftFlag = 0;
			}		  			  		

			eprofileMyCmbFieldValue(new Array('Qualification','SubQualification','UniBoard','Marks','courseCategory','discipline','yrpassing','unit'));						

			var rowNum;

			mergeUnitAndMarks();

			if (workFlowEnabled) 
			{
				rowNum = addDataInTableAttachment('QuaDtlsTab', 'encXML', displayFieldArrayInTable,'editRecord','deleteEmpEducationRecord','');
			}
			else
			{
				rowNum = addDataInTableAttachment('QuaDtlsTab', 'encXML', displayFieldArrayInTable,'editDBRecord','deleteDBRecord','');
			}
			resetData(rowNum);
			endProcess(); 
		}	   	    	  				   	    			   	    
	}		   				   	
}			


//IFMS Start

function editDBRecord(rowId, rowNum)
{
	globalFlagDeleteDisable=true;
	sendAjaxRequestForEditAttachment(rowId, 'populateForm','attachmentBiometricEducation',rowNum);
	document.frmEdu.Add.disabled=true;		
	document.frmEdu.Save.disabled=false;		

}
//IFMS Ends

//IFMS Start
function saveDataInTable()
{
	if (xmlHttp.readyState == 4) 
	{
		if (xmlHttp.status == 200) 
		{
			eprofileMyCmbFieldValue(new Array('Qualification','SubQualification','UniBoard','Marks','courseCategory','discipline','yrpassing','unit'));	

			mergeUnitAndMarks();

			if(!globalFlagDeleteDisable)
			{
				rowNum = updateDataInTableAttachment('QuaDtlsTab','encXML', displayFieldArrayInTable);
			}
			else
			{
				rowNum = updateDataInTableAttachment('QuaDtlsTab','addedPunch', displayFieldArrayInTable);
			}

			document.frmEdu.Add.disabled=false;		
			document.frmEdu.Save.disabled=true;
			resetData(rowNum);	
			endProcess();
		}
	}
}
// ENDS

function updateRecord() 
{		
	if (xmlHttp.readyState == 4) 
	{			  	
		if (xmlHttp.status == 200) 
		{  	
			if(draftFlag == 4)
			{
				draftArrCount= draftArrCount+1;  		
				draftArr[draftArrCount] = 'rowencXML'+count;		  			
				draftFlag = 0;
			}			  				

			eprofileMyCmbFieldValue(new Array('Qualification','SubQualification','UniBoard','Marks','courseCategory','discipline','yrpassing','unit'));	

			mergeUnitAndMarks();				  		

			if (dbRecordUpdateOrDelete)
			{
				trow= document.getElementById(editRowId);														
				trow.childNodes[8].style.display='none';
				dbRecordUpdateOrDelete=false;
				count=parseInt(count)+1;						  						  			  		
				var rowNum = eprofileAddDataInTableAttachment('QuaDtlsTab', 'encXML', displayFieldArrayInTable,'editRecord','deleteEmpEducationRecord','',document.getElementById('sendAttachmentId').value);
				var mappingId ='rowencXML'+parseInt(counter-1);

				/*For Mapping Purpose Between */
				var map1 =editRowId;
				var map2 ="/"+mappingId;				
				var map = map1.concat(map2);
				mappingArr[mappingArrCount]=map;
				mappingArrCount= mappingArrCount+1;
				/*End of Mapping a Code*/
			}
			else
			{
				rowNum = updateDataInTableAttachment('QuaDtlsTab','encXML', displayFieldArrayInTable);
			}
			globalFlagDeleteDisable = false;
			document.frmEdu.Save.disabled=true;																	
			document.frmEdu.Add.disabled=false;
			resetData(rowNum);	
			endProcess();						
		}
	}
}
function populateForm() 
{					
	if (xmlHttp.readyState == 4) 
	{ 	
		if (xmlHttp.status == 200) 
		{ 									  		
			var decXML = xmlHttp.responseText;				  					  	
			var xmlDOM = populateAttachment(decXML,'frmEdu');	

			try {					
				var str = getXPathValueFromDOM(xmlDOM, 'cmnAttachmentMst/attachmentId');
				if(str!='' || str!=null || isNaN(str)==true)
					document.getElementById('sendAttachmentId').value=str;					
				else
					document.getElementById('sendAttachmentId').value=0;
			}catch(ex){document.getElementById('sendAttachmentId').value=0;}


			str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstBySubQualificationId/lookupName');	
			if(str==null || str=='' || str=='null' || str.search(/select/i)!=-1){setCombo='Select';}	
			else{setCombo=str;}

			var str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByQualificationId/lookupName');		
			var id = str;
			if(str==null || str=='' || str=='null' || str.search(/select/i)!=-1) {document.forms[0].Qualification.value='Select';}
			else
			{
				document.forms[0].Qualification.value=str;
				getSubqualificationsLst(document.frmEdu.Qualification);
			}						
			if(id=='edu_Higher_Secondary_School'  || id== 'edu_Secondary_School' || id=='edu_Primary_School' || id=='' || id.search(/select/i)!=-1 || id=='null' || id==null)
			{
				document.getElementById('diciplineId').style.display='none';					
			}
			else
				document.getElementById('diciplineId').style.display='';

			str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByDicipline/lookupName');						
			if(str==null || str=='' || str=='null'  || str.search(/select/i)!=-1){setdiscipline='Select';}	
			else{setdiscipline=str;}

			var str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByCourseCategory/lookupName');												
			if(str==null || str=='' || str=='null'  || str.search(/select/i)!=-1) {document.forms[0].courseCategory.value='Select';}							
			else
			{
				document.forms[0].courseCategory.value=str;							
			}

			str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByUnitsOfMarks/lookupName');						
			if(str==null || str=='' || str=='null' || str.search(/select/i)!=-1){document.getElementById('unit').value='Select';}	
			else{document.getElementById('unit').value=str;}

			str=getXPathValueFromDOM(xmlDOM, 'yearOfPassing');	
			if(str==null || str=='' || str=='null' || str=='0')
			{
				RelationCombo=document.getElementById('yrpassing');
				RelationCombo.options[0].selected=true;
			}	
			else{document.getElementById('yrpassing').value=str;}								  								

			str = getXPathValueFromDOM(xmlDOM, 'uniInstituteBoard');					
			if(str==null || str=='' || str=='null'){str='';}
			document.getElementById('UniBoard').value = str;

			str = getXPathValueFromDOM(xmlDOM, 'marksScored');	
			if(str==null || str=='' || str=='null'){str='';}
			document.getElementById('Marks').value = str;

			var str;
			try
			{
				document.getElementById('srNo').value=getXPathValueFromDOM(xmlDOM, 'srNo');
				if(document.getElementById('srNo').value=='null' || document.getElementById('srNo').value==null || document.getElementById('srNo').value=='')
				{
					document.getElementById('srNo').value=getXPathValueFromDOM(xmlDOM, 'rowNumber');
				}
			}catch(e){document.getElementById('srNo').value=getXPathValueFromDOM(xmlDOM, 'rowNumber');}						
		}														 
	}  			  
}
function qualificationDeleteDBRecord(rowId)
{		
	var map1=rowId.substring(3, rowId.length);
	if(map1.indexOf("encXML")!=-1)
	{
		map=document.getElementById(rowId.substring(3, rowId.length)).value;
	}
	var flag = deleteRecord(rowId);
	if(flag)
	{
		document.frmEdu.Submit.disabled=false;
		document.frmEdu.btnSave_Draft.disabled=false;	
		deleteArr[delArrCount]=map;
		delArrCount=delArrCount+1;				
		submiteFlag=true;
		var num = document.getElementById('QuaApproveDtlsTab').rows.length;																
		var rowLen=num;
		for(var i=0;i<num;i++)	
		{
			trow= document.getElementById('QuaApproveDtlsTab').rows[i];																																		
			try{
				if(trow.style.display=='none')
				{	
					rowLen=rowLen-1;								
				}
			}catch(e){break;}	
		}								
		if(rowLen==1)
		{
			document.getElementById('QuaApproveDtlsTab').style.display='none';																				
		}								
	}
}
function countRows(countRows){if(count<countRows){count=countRows;}}

/***Script for the Pending Request Table*/
var v1_inc=0;
function increment()
{			
	v1_inc=v1_inc+1;
	return v1_inc;
}
var v1_pen_srno=0;
function increment_pending()
{
	v1_pen_srno=v1_pen_srno+1;
	return v1_pen_srno;
}
function delete_pendingReqRows()
{			
	for(i=0;i<document.frmEdu.elements.length;i++)
	{   	            					
		if(document.getElementById('pendingDtlsRows'))
		{
			var delRow = document.getElementById('pendingDtlsRows');						
			delRow.parentNode.deleteRow(delRow.rowIndex);													
		}
	}		
}
function viewPendingRequestDtls(reqId)
{		
	try
	{   
		xmlHttp=new XMLHttpRequest();	    	    
	}
	catch (e)
	{    // Internet Explorer    
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
				alert(EducationDtlsAlertMsgArr[3]);       
				return false;        
			}
		}			 
	}		        	
	var url = "hdiits.htm?actionFlag=EducationDtls&flag=getPendingRecord&reqId="+reqId;    
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = pendingRequestResponse;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));		
}					

function pendingRequestResponse()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{  					
			delete_pendingReqRows();
			var xmlStr = xmlHttp.responseText;				    	
			var XMLDoc=getDOMFromXML(xmlStr);				    				    	

			var qua = XMLDoc.getElementsByTagName('PendingQua');
			var Sub = XMLDoc.getElementsByTagName('PendingSubQua');
			var UnitInst = XMLDoc.getElementsByTagName('PendingUnitInst');
			var Year = XMLDoc.getElementsByTagName('PendingYear');
			var Unit = XMLDoc.getElementsByTagName('PendingUnit');				    	
			var Marks = XMLDoc.getElementsByTagName('PendingMark');
			var reqFlag = XMLDoc.getElementsByTagName('PendingreqFlag');
			var dici=XMLDoc.getElementsByTagName('PendingDici');
			var cate=XMLDoc.getElementsByTagName('PendingCate');
			document.getElementById('QuaPendingDtlsTab').style.display='';
			var j=0,k=1;
			for ( var i = 0 ; i < qua.length ; i++ )
			{     					
				var qua1=qua[i].childNodes[0].text;	     					
				var sub1=Sub[i].childNodes[0].text;
				var unitInst1=UnitInst[i].childNodes[0].text;
				var unit1=Unit[i].childNodes[0].text;
				var marks1=Marks[i].childNodes[0].text;
				var year1=Year[i].childNodes[0].text;
				var reqFlag1=reqFlag[i].childNodes[0].text;
				var dici1=dici[i].childNodes[0].text;
				var cate1=cate[i].childNodes[0].text;

				
				if(marks1=='null' || marks1=='' || marks1==null){marks1="-";}
				if(unit1=='null' || unit1==EducationDtlsAlertMsgArr[2]){unit1="-";}
				
				var pendingUnitMarks='';
				if(marks1=='-' && unit1=='-')
				{
					pendingUnitMarks='-';
				}
				else
				{
					pendingUnitMarks=marks1+" "+unit1;
				}

				if(dici1=='null'||dici1==EducationDtlsAlertMsgArr[2]){dici1="-";} 
				if(cate1=='null'||cate1==EducationDtlsAlertMsgArr[2]){cate1="-";} 
				if(qua1=='null'){qua1='-';}
				if(sub1=='null'){sub1='-';}
				if(year1=='null'){year1='-';}	
				if(unitInst1=='null'){unitInst1='-';}


				trow= document.getElementById('QuaPendingDtlsTab').insertRow();	
				trow.id= 'pendingDtlsRows';   						   					
				trow.insertCell(j).innerHTML = k;
				k=parseInt(k)+1;	
				trow.insertCell(j+1).innerHTML=qua1;
				trow.insertCell(j+2).innerHTML=sub1;
				trow.insertCell(j+3).innerHTML=dici1;	   					
				trow.insertCell(j+4).innerHTML=cate1;
				trow.insertCell(j+5).innerHTML=unitInst1;	   					
				trow.insertCell(j+6).innerHTML=year1;
				trow.insertCell(j+7).innerHTML=pendingUnitMarks;	   					
				if(reqFlag1=='U' || reqFlag1=='N') {trow.insertCell(j+8).innerHTML=EducationDtlsAlertMsgArr[7];}
				else {trow.insertCell(j+8).innerHTML=EducationDtlsAlertMsgArr[8];}	
				trow.insertCell(j+9).innerHTML="<a href=javascript:void('Hide') onclick=javascript:eprofileHideObj('QuaPendingDtlsTab')>"+EducationDtlsAlertMsgArr[22]+"</a>";
				j=0;
			}
		}
	}
}
/************ End of Pending Request Table Script *************/	
/********************Scripts for the draft request ***********/
var v1_draft_inc= 0,v2_draft_inc=0;		
function increment_draft()
{
	v1_draft_inc=v1_draft_inc+1;
	return v1_draft_inc;
}
function increment_approve()
{
	v2_draft_inc=v2_draft_inc+1;
	return v2_draft_inc;
}

function openDraftRequestDtls(reqId)	
{
	var reqIdArr = new Array();
	var j=0;			
	var flag=0;
	reqIdArr[0]=reqId;

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
				alert(EducationDtlsAlertMsgArr[3]);       
				return false;        
			}
		}			 
	}

	/*Hideing A Draft Table Row*/		        					

	trow= document.getElementById(reqId);			     																												
	trow.style.display='none'; 
	var srno=1,flag=0;	
	var num = document.getElementById('QuaDraftDtlsTab').rows.length;								
	for(var i=1;i<num;i++)
	{
		trow= document.getElementById('QuaDraftDtlsTab').rows[i];																																		
		try
		{
			if(trow.style.display!='none')
			{
				trow.childNodes[0].innerHTML=srno;								
				srno=srno+1;
				flag=1;
			}
		}catch(e){break;}						
	}					
	if(flag==0)
	{
		document.getElementById("QuaDraftDtlsTab").style.display='none';
	}
	document.getElementById('QuaDraftTable').style.display='none';  //Hiding a Draft table
	var url = "hrms.htm?actionFlag=EducationDtls&flag=AddDraftDtlsinTable&reqId="+reqIdArr;    
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = addDraftDataInTable;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));							
}
function response()
{					
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{  
			var xmlStr = xmlHttp.responseText;				    						
			var XMLDoc = getDOMFromXML(xmlStr);			    					    

			addDraftDataInTable(XMLDoc);					
		}
	}			
}
function viewDraftRequestDtls(reqId)
{				
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
				alert(EducationDtlsAlertMsgArr[3]);
				return false;        
			}
		}			 
	}        	       		
	var url = "hrms.htm?actionFlag=EducationDtls&flag=getDraftData&reqId="+reqId;    
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = processResponseforDraft;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));						
}		
function deleteDraftRequestDtls(reqId)
{				
	if(confirm(EducationDtlsAlertMsgArr[9]))
	{
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
					alert(EducationDtlsAlertMsgArr[3]);
					return false;        
				}
			}			 
		}        	
		var url = "hrms.htm?actionFlag=EducationDtls&flag=deleteDraftData&reqId="+reqId;    
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange = deleteDraft;
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
}
function deleteDraft()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{        					   
			var xmlStr = xmlHttp.responseText;					    	
			var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    					    					    							    	
			var SrNo = XMLDoc.getElementsByTagName('DeleteDraft');	
			var link = XMLDoc.getElementsByTagName('Link');	
			var deletDraftDbLinkShow;
			for ( var i = 0 ; i < link.length ; i++ )
			{
				var showlink= link[i].childNodes[0].text;	
				if(showlink!='null'  && showlink!='')
				{	     						    	
					var deleteFlag=1;
					var x;
					for(x in dbRowsSrNo)
					{
						var getSrNo = dbRowsSrNo[x].toString();		
						var srNoObj=getSrNo.split('/');										
						if(showlink == srNoObj[0])
						{
							deleteFlag=0;
							deletDraftDbLinkShow=srNoObj[1];																			
						}
					}								
					if(deleteFlag==0)
					{									
						trow= document.getElementById(deletDraftDbLinkShow);			     																												
						trow.childNodes[9].style.display='';
						deleteFlag=1;
					}
				}
			}
			var deleteId= SrNo[0].childNodes[0].text;				    				    	
			var delRow = document.getElementById(deleteId);						
			delRow.parentNode.deleteRow(delRow.rowIndex);																																
			if(document.getElementById("QuaDraftDtlsTab").rows.length==1)
			{
				document.getElementById("QuaDraftDtlsTab").style.display='none';
			}
			else
			{
				var srno=1;	
				var num = document.getElementById('QuaDraftDtlsTab').rows.length;								
				for(var i=1;i<num;i++)
				{
					trow= document.getElementById('QuaDraftDtlsTab').rows[i];																																		
					try{
						trow.childNodes[0].innerHTML=srno;								
					}catch(e){break;}
					srno=srno+1;
				}
			}
			document.getElementById('QuaDraftTable').style.display='none';							
		}
	}
}		
function deleteRows()
{							
	try 
	{
		for(i=0;i<document.frmEdu.elements.length;i++)
		{   	            					
			if(document.getElementById('srno1'))
			{
				var delRow = document.getElementById('srno1');						
				delRow.parentNode.deleteRow(delRow.rowIndex);													
			}
		}
	}catch(e) {	}	
}
function processResponseforDraft()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{  
			deleteRows();
			var xmlStr = xmlHttp.responseText;
			var XMLDoc=getDOMFromXML(xmlStr);  		    			    					    					    						    					    	
			var SrNO = XMLDoc.getElementsByTagName('DraftSrNo');
			var qua = XMLDoc.getElementsByTagName('DraftQua');
			var Sub = XMLDoc.getElementsByTagName('DraftSubQua');
			var UnitInst = XMLDoc.getElementsByTagName('DraftUnitInst');
			var Year = XMLDoc.getElementsByTagName('DraftYear');
			var Unit = XMLDoc.getElementsByTagName('DraftUnit');				    	
			var Marks = XMLDoc.getElementsByTagName('DraftMark');
			var dici=XMLDoc.getElementsByTagName('DraftDici');
			var cate=XMLDoc.getElementsByTagName('DraftCate');
			var Request = XMLDoc.getElementsByTagName('RequestFlag');

			document.getElementById('QuaDraftTable').style.display='';
			var j=0;				    					    	
			for ( var i = 0 ; i < SrNO.length ; i++ )
			{
				var srno1=SrNO[i].childNodes[0].text;
				var qua1=qua[i].childNodes[0].text;
				var sub1=Sub[i].childNodes[0].text;
				var unitInst1=UnitInst[i].childNodes[0].text;
				var unit1=Unit[i].childNodes[0].text;
				var marks1=Marks[i].childNodes[0].text;
				var year1=Year[i].childNodes[0].text;
				var request1=Request[i].childNodes[0].text;
				var dici1=dici[i].childNodes[0].text;
				var cate1=cate[i].childNodes[0].text;
				
				if(marks1=='null' || marks1=='' || marks1==null){marks1="-";}	
				if(unit1=='null' || unit1==EducationDtlsAlertMsgArr[2]){unit1="-";}
				
				var viewUnitMarks="";
				if(marks1=='-' && unit1=='-')
				{
					viewUnitMarks="-";
				}
				else
				{
					viewUnitMarks=marks1+" "+unit1;
				}

				if(dici1=='null' || dici1==EducationDtlsAlertMsgArr[2]){dici1='-';}
				if(cate1=='null' || cate1==EducationDtlsAlertMsgArr[2] ){cate1='-';}
				if(qua1=='null' || qua1==EducationDtlsAlertMsgArr[2]){qua1='-';}
				if(sub1=='null' || sub1==EducationDtlsAlertMsgArr[2]){sub1='-';}
				if(year1=='null'){year1='-';}	
				if(unitInst1=='null'){unitInst1='-';}     						     					

				trow= document.getElementById('QuaDraftTable').insertRow();
				trow.id='srno1';
				trow.insertCell(j).innerHTML = "<INPUT type='hidden' id='srno1' value='" + srno1 + "'/>";				
				trow.cells[j].style.display='none';
				trow.insertCell(j+1).innerHTML=srno1;
				trow.insertCell(j+2).innerHTML=qua1;
				trow.insertCell(j+3).innerHTML=sub1;
				trow.insertCell(j+4).innerHTML=dici1;     					     																									     						     					
				trow.insertCell(j+5).innerHTML=cate1;
				trow.insertCell(j+6).innerHTML=unitInst1;     					     																									     						     					
				trow.insertCell(j+7).innerHTML=year1;	 
				trow.insertCell(j+8).innerHTML=viewUnitMarks;        						     					
				if(request1=='D'){trow.insertCell(j+9).innerHTML=EducationDtlsAlertMsgArr[23];}
				else if(request1=='U' || request1=='N')	{trow.insertCell(j+9).innerHTML=EducationDtlsAlertMsgArr[24];}
				else {trow.insertCell(j+9).innerHTML=EducationDtlsAlertMsgArr[25];}
				j=0;
			}
		}
		else 
		{  			
			alert(EducationDtlsAlertMsgArr[4]);
		}
	}
}

function addDraftDataInTable()
{	
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{  
			var xmlStr = xmlHttp.responseText;		    						
			var XMLDoc = getDOMFromXML(xmlStr);
			document.frmEdu.Submit.disabled=false;
			document.frmEdu.btnSave_Draft.disabled=false;							    					    					
			var qua = XMLDoc.getElementsByTagName('DraftQua');
			var Sub = XMLDoc.getElementsByTagName('DraftSubQua');
			var UnitInst = XMLDoc.getElementsByTagName('DraftUnitInst');
			var Year = XMLDoc.getElementsByTagName('DraftYear');
			var Unit = XMLDoc.getElementsByTagName('DraftUnit');
			var dici=XMLDoc.getElementsByTagName('DraftDici');
			var cate=XMLDoc.getElementsByTagName('DraftCate');				    	
			var Marks = XMLDoc.getElementsByTagName('DraftMark');
			var requestFlag = XMLDoc.getElementsByTagName('RequestFlag');
			var Xml = XMLDoc.getElementsByTagName('XMLFile');
			var reqid = XMLDoc.getElementsByTagName('ReqId');
			var removeRow = XMLDoc.getElementsByTagName('RemoveRow');	
			var attch = XMLDoc.getElementsByTagName('Attchment');
			document.getElementById('QuaDtlsTab').style.display='';		    												
			var x,flags=1;		    	
			for ( var i = 0 ; i < reqid.length ; i++ )
			{
				var x;
				for (x in reqIdArr)
				{					
					var r = reqid[i].childNodes[0].text;												
					if(reqIdArr[x] == r)
					{
						flags=0;
						alert(EducationDtlsAlertMsgArr[10]);
					}												
				}					
			}
			for ( var i = 0 ; i < reqid.length ; i++ )
			{
				if(flags==1)
				{		
					var r = reqid[i].childNodes[0].text;				 
					reqIdArr[reqIdCounter] = r;
					reqIdCounter =reqIdCounter+1;
				}
			}

			if(flags==1)
			{
				var j=0,addFlag=0,valK=0;				    			    	
				for ( var i = 0 ; i < qua.length ; i++ )
				{
					var dici1=dici[i].childNodes[0].text;
					var cate1=cate[i].childNodes[0].text;
					var qua1=qua[i].childNodes[0].text;	     					
					var sub1=Sub[i].childNodes[0].text;
					var unitInst1=UnitInst[i].childNodes[0].text;
					var unit1=Unit[i].childNodes[0].text;
					var marks1=Marks[i].childNodes[0].text;
					var year1=Year[i].childNodes[0].text;
					var requestFlag1 = requestFlag[i].childNodes[0].text;
					var xml1 = Xml[i].childNodes[0].text;	
					var dici1= dici[i].childNodes[0].text;
					var cate1= cate[i].childNodes[0].text;
					var sendAttchmentId =attch[i].childNodes[0].text;
					if(sendAttchmentId=='null'){sendAttchmentId="";}
					if(marks1=='null'){marks1="-";}	 	     						     					
					if(unitInst1=='null'){unitInst1="-";} 
					if(dici1=='null'||dici1==EducationDtlsAlertMsgArr[2]){dici1="-";} 
					if(cate1=='null'||cate1==EducationDtlsAlertMsgArr[2]){cate1="-";} 
					if(qua1=='null'||qua1==EducationDtlsAlertMsgArr[2]){qua1='-';}
					if(sub1=='null'||sub1==EducationDtlsAlertMsgArr[2]){sub1='-';}
					if(year1=='null'){year1='-';}	
					if(unit1=='null'||unit1==EducationDtlsAlertMsgArr[2]){unit1='-';}
					
					var openUnitMarks="";
					if(marks1=='-' && unit1=='-')
					{
						openUnitMarks='-';
					}
					else
					{
						 openUnitMarks=marks1+" "+unit1;	
					}
					
					count = parseInt(count)+1;

					/*end of the Mapping */

					/**   Delete Array Count   **/
					deleteArr[delArrCount] = xml1;
					delArrCount = delArrCount+1; 			
					/**  Delete Array Count  **/

					var displayFieldArray= new Array(qua1,sub1,dici1,cate1,unitInst1,year1,openUnitMarks);						

					addDBDataInTableAttachment('QuaDtlsTab','encXML',displayFieldArray,xml1,sendAttchmentId,'editRecord', 'deleteEmpEducationRecord','');

					var showDraftLinkId = 'rowencXML'+parseInt(counter-1);							
					if(requestFlag1=='null' || requestFlag1=='N')
					{
						draftArrCount= draftArrCount+1;
						draftArr[draftArrCount] = showDraftLinkId;		  			
					}
					else
					{		   					   					
						for(var k=valK;k< removeRow.length ; k++ )	
						{
							if(addFlag==0)
							{
								var remove = removeRow[k].childNodes[0].text;  	
								if(remove!='null' )
								{				   							
									valK++;	   						   							
									var map1 =remove;
									var map2 ='/'+showDraftLinkId;				
									var map = map1.concat(map2);
									mappingArr[mappingArrCount]=map;										
									addFlag=1;
									mappingArrCount= mappingArrCount+1;
								}
							}
						}							
					}
					addFlag=0;
					j=0;
				}
			}
			getFieldGroupObj(document.getElementById("QuaDtlsTab"));
		}
	}				      			     																
}
/**************End of The Draft Scripts *****/

/************Validation Scripts******/
function Education_validateSpecificFormFields(controlNames)
{
	var returnValue = true,getIn=false;
	var validMark=true;
	var selNum  = document.getElementById('unit').selectedIndex;
	var selText = document.getElementById('unit').options[selNum].text;
	var selValue = document.getElementById('unit').value;

	var marks= document.getElementById('Marks').value;	

	var rule_num="qwertyuiopasdfghjklzxcvbnmASDFGHJKLPOIUYTREWQZXCVBNM- ";
	uniBoard=document.getElementById('UniBoard').value;
	if(uniBoard!="")
	{
		for(var i=0;i<uniBoard.length;i++)
		{
			var cchar=uniBoard.charAt(i);
			if(rule_num.indexOf(cchar)==-1)
			{
				alert(EducationDtlsAlertMsgArr[11]);
				setFocusSelection('UniBoard');
				return 'error';
			}
		} 
	}	

	if(selValue=='edu_CGPA' && validMark)
	{	
		getIn=true;						
		for(var i=0;i<marks.length;i++)
		{
			var keyId = marks.charCodeAt(i);									
			if(keyId >= 48 && keyId <= 57 || keyId==46) {}
			else 
			{
				alert(EducationDtlsAlertMsgArr[12]);
				returnValue = 'error';
				validMark=false;
				setFocusSelection('Marks');
				break;
			}
		}
		if(marks > 10.00 || marks < 0.00 && validMark)
		{
			alert(EducationDtlsAlertMsgArr[12]);
			returnValue = 'error';
			setFocusSelection('Marks');
			validMark=false;
		}
	}
	else if (selValue=='edu_Percentage' && validMark)
	{	
		getIn=true;			
		for(var i=0;i<marks.length;i++)
		{
			var keyId = marks.charCodeAt(i);					
			if(keyId >= 48 && keyId <= 57 || keyId==46) {}
			else 
			{
				alert(EducationDtlsAlertMsgArr[12]);
				returnValue = 'error';
				validMark=false;
				setFocusSelection('Marks');
				break;
			}
		}
		if(marks > 100.00 || marks < 0.00 && validMark)
		{
			alert(EducationDtlsAlertMsgArr[12]);
			returnValue = 'error';
			setFocusSelection('Marks');
			validMark=false;
		}
	}
	return returnValue;
}

function checkForDot(fieldValue)
{
	var valueFlag=true,dotCount=0;
	for(var i=0;i<fieldValue.length;i++)
	{
		var keyId = fieldValue.charCodeAt(i);									
		if(keyId==46) {dotCount=parseInt(dotCount)+1;}
	}
	if(dotCount>=2){valueFlag=false;}
	return valueFlag;
}	

/*Message Showing on Submit*/
function messageDisplay()
{
	var str='',fir=true,countRows=0;
	var num = document.getElementById('QuaDtlsTab').rows.length;	

	for(var i=1;i<num;i++)
	{							
		trow=document.getElementById('QuaDtlsTab').rows[i];
		rowId=trow.id;
		try 
		{				
			var hField = rowId.substring(3, rowId.length);
			var xmlFileName = document.getElementById(hField).value;		

			if(xmlFileName.indexOf("$/$")!=-1)
			{
				rowIdArr=xmlFileName.split("$/$");		
				if(!fir)
				{
					str=str+"\n\n";												
				}
				else{fir=false;}
				countRows=countRows+3;
				str=str+'Row No '+i+' : ';	

				validationArray = rowIdArr[1].split(",");
				var checkCouCat=false;
				for (itr=0;itr<validationArray.length;itr++)
				{
					if (validationArray[itr] != '' && validationArray[itr]!='_N')
					{
						if (validationArray[itr] == 'eis.uni_ins_board')
							str = str + EducationDtlsAlertMsgArr[14]+",";
						else if (validationArray[itr] == 'eis.qualification')
						{
							checkCouCat=true;
							str = str + EducationDtlsAlertMsgArr[16]+",";
						}
						else if (validationArray[itr] == 'eis.subqualification')
							str = str + EducationDtlsAlertMsgArr[17]+",";
						else if (validationArray[itr] == 'eis.courseCategory' &&  !checkCouCat)
							str = str + EducationDtlsAlertMsgArr[18]+",";
						else if (validationArray[itr] == 'eis.unit')
							str = str + EducationDtlsAlertMsgArr[19]+",";
						else if (validationArray[itr] == 'eis.year_of_pass')
							str = str + EducationDtlsAlertMsgArr[20]+",";
						else if (validationArray[itr] == 'eis.marks')	
							str = str + EducationDtlsAlertMsgArr[21]+",";
					}
				}
				if (validationArray.length > 0)
				{
					str=str.substring(0,str.length-1);
					str = str +" " + EducationDtlsAlertMsgArr[15];
					getFieldGroupObj(document.getElementById("errorMessage"));
				}
			}										
		}catch(e){}	
		addLine=true;												
	}
	if(str=='')
	{
		return true;
	}			
	else 
	{				
		document.getElementById('errorBox').rows=countRows;
		document.getElementById('errorMessage').style.display='';				
		document.getElementById('errorBox').value=str;								
		return false;
	}			
}
/*End of Message*/	

function openAppWindow(actionFlag)//IFMS
{
	var userId = document.getElementById("userId").value;
	var href = "hrms.htm?actionFlag="+  actionFlag + "&userId="+ userId ;
	objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");			
}

function mergeUnitAndMarks()
{
	var addMarks=document.getElementById('Marks').value;
	var selectedUnit=document.frmEdu.unit;
	var addUnit=selectedUnit.options[selectedUnit.selectedIndex].text;

	var checkAddUnit=document.getElementById('unit').value;
	
	if(checkAddUnit.search(/select/i)!=-1){addUnit='-';}			  		
	if(addMarks==''){addMarks='-'}
	
	var addUnitMarks='';
	if(addUnit=='-' && addMarks=='-'){addUnitMarks='-';}
	else{addUnitMarks=addMarks+" "+addUnit;}
	
	document.getElementById('unitMarks').value=addUnitMarks;
	getFieldGroupObj(document.getElementById("QuaDtlsTab"));		
}


/**JAVA SCRIPT FOR SHOW EDUCATION REQUEST------START  */

function onLoadEducationDtlsData()
{
	disabledMe();
	document.getElementById('target_uploadattachmentBiometric').style.display='none';
	document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='none';
	document.getElementById('attachmentTable').style.display='none';
}
function increment_showRequest()
{
	v=v+1; 
	countMe=countMe+1;
	return v;
}
function increment_showApprove()
{
	v1=v1+1; 
	countMe=countMe+1;
	return v1;
}

function callMe(action)
{
	if(action=='P')
	{
		disabledFlag=false;
	}
}	
function disabledMe()
{
	if(disabledFlag)
	{								
		document.getElementById('msg_Id').style.display='';
	}
	else
	{
		for (var i = 0; i < document.showEdu.elements.length; i++)
		{						
			if(document.showEdu.elements[i].type=="checkbox" )
			{					
				document.showEdu.elements[i].disabled=false;
			}
		}
	}
}
function setFlags(flagStr)
{
	if(flagStr=='Discipline')
	{
		flgaDiscipline=false;
	}
	if(flagStr=='Category')
	{
		flgaCategory=false;
	}
}
function resetMyAllFlag()
{
	flgaDiscipline=true;
	flgaCategory=true;
}

function submitHiddenData() 
{	
	var j=0;
	for (var i = 0; i < document.showEdu.elements.length; i++)
	{						
		if(document.showEdu.elements[i].type=="checkbox" )
		{					
			var user_input = document.showEdu.elements[i].value;
			if(document.showEdu.elements[i].checked)
			{
				arr[j]=user_input;
				j=parseInt(j)+1;
			}
		}
	}
	document.getElementById("approveDtls").value=arr;
	var uri = "hrms.htm?actionFlag=";
	var url = uri + "FwdEducationDtlsForApproval&approveDtls="+arr;   
	document.showEdu.action = url;
	document.showEdu.submit();
}
function showEducationAttachmentFieldGroup()
{
	getFieldGroupObj(document.getElementById("attachmentTable"));
}
/**JAVA SCRIPT FOR SHOW EDUCATION REQUEST------ENDS  */