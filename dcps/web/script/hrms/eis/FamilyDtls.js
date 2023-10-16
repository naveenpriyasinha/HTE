function resetAllFlag(){updateRow = null;}

function employmentStatus(employment)
{
	var id= employment.options[employment.selectedIndex].value;
	document.getElementById('Name_of_Company').value='';
	document.getElementById('Occupation').value='Select';
	document.getElementById('annual_income').value='';
	document.getElementById('designation').value='';
	if(id == '' || id=='Select')
	{
		document.getElementById('employedData').style.display='none';				
		return;
	}				
	else if(id == 'fm_Employed' || id=='fm_self_employeed')
	{
		document.getElementById('employedData').style.display='';					
	}
	else
	{
		document.getElementById('employedData').style.display='none';
	}			
}

function genderCmbEnableDisable(id)
{
	if(id == 'fm_rel_Other')
	{
		document.getElementById('Gender').value='Select';				
		document.frmFamily.Gender.disabled=false;
	}
	else if(id=='fm_rel_Son' || id=='fm_rel_Father' || id=='fm_rel_Brother' || id=='fm_Husband')
	{			
		document.getElementById('Gender').value='fm_Male';
		document.frmFamily.Gender.disabled=true;
	}
	else if(id=='fm_rel_Mother' || id=='fm_rel_Sister' || id=='fm_rel_Daughter' || id=='fm_Wife')
	{			
		document.getElementById('Gender').value='fm_Female';
		document.frmFamily.Gender.disabled=true;
	}
	else
	{
		document.getElementById('Gender').value='Select';
		document.frmFamily.Gender.disabled=false;
	}
}

function OtherRelation(RelationCombo)
{			
	var id = RelationCombo.value;	
	genderCmbEnableDisable(id);
	if(id == '')
	{
		return;
	}				
	if(id == 'fm_rel_Other')
	{
		document.getElementById('other1').style.display='';
	}
	else
	{
		document.getElementById('other1').style.display='none';	
		document.getElementById('otherRelation').value='';
	}
	if(id=='fm_Husband' || id=='fm_Wife')
	{
		document.getElementById("MaritalStatus").value='fm_Married';
	}
	else
		document.getElementById("MaritalStatus").value='Select';
}

function makeReadOnly()
{
	document.frmFamily.DOB.readOnly=true;
	document.frmFamily.date_Demise.readOnly=true;
}

function SubmitSaveAsDraft()
{									
	if(document.getElementById('FamilyDataTable').rows.length==1)
	{
		alert(FamilyDtlsAlertMsgArr[0]);
	}
	else if(confirm(FamilyDtlsAlertMsgArr[1])==true)
	{	
		var num = document.getElementById('FamilyDataTable').rows.length;											
		for(var i=1;i<num;i++)
		{							
			trow=document.getElementById('FamilyDataTable').rows[i];
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

		var x1,x;					
		for (x1 in deleteAppArr)
		{
			var map =deleteAppArr[x1].toString();																
			var map1=map.substring(3, map.length);
			if(map1.indexOf("encXML")!=-1)
			{
				var map2=map.substring(9, map.length);				
				deleteArr[deleteArrCount]=document.getElementById("encXML"+map2).value;
				deleteArrCount=deleteArrCount+1;
			}								
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
		document.frmFamily.action="hrms.htm?actionFlag=FamilyDetails&flag=SubmitFamilyDtls&draft=1&DeleteArr="+deleteArr;
		document.frmFamily.submit();			   	
	}
}		
function SubmitAction()
{	
	var ans=true;		 
	var flags=messageDisplay();
	if(flags==true)
	{	
		document.getElementById('errorMessage').style.display='none';
		if(allowSubmit==true && document.getElementById('FamilyDataTable').rows.length==1)
		{
			alert(FamilyDtlsAlertMsgArr[2]);
		}
		else if(confirm(FamilyDtlsAlertMsgArr[3])==true)    
		{
			var num = document.getElementById('FamilyDataTable').rows.length;											
			for(var i=1;i<num;i++)
			{							
				trow=document.getElementById('FamilyDataTable').rows[i];
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
			var x1;
			for (x1 in deleteAppArr)
			{
				var map =deleteAppArr[x1].toString();					
				var map1=map.substring(3, map.length);
				if(map1.indexOf("encXML")!=-1)
				{
					var map2=map.substring(9, map.length);				
					deleteArr[deleteArrCount]=document.getElementById("encXML"+map2).value;
					deleteArrCount=deleteArrCount+1;
				}								
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
				var arFinalDeleteRecord = new Array();
				var iCounter=0;
				for (itr=0;itr<deleteAppArr.length;itr++)
				{
					var deletedRowId =deleteAppArr[itr].toString();					
					var recordNo=deletedRowId.substring(9, deletedRowId.length);
					
					arFinalDeleteRecord[iCounter] = dbRowsSrNo[recordNo-1];
					iCounter++;
				}
				var vldActionFlag = "checkSubmitFamilyDtls&DeleteArr="+arFinalDeleteRecord;
				sendAjaxRequestHRMS("duplicateRecResponse", vldActionFlag, new Array('encXML'), false, "POST");
			}
			else
			{
				document.frmFamily.action = "hrms.htm?actionFlag=FamilyDetails&workFlowEnabled="+ workFlowEnabled +"&flag=SubmitFamilyDtls&draft=0&DeleteArr="+deleteArr;  //IFMS
				document.frmFamily.submit();			    	
			}
		}
	}			   
}

function showme(v)
{						
	if(v==2){document.getElementById('RequestTable').style.display='none';}
	else if(v==1) {
		document.getElementById('FamilyApproveDataTable').style.display='none';			
	}
}

function AddDependentNomineeArr(str)
{
	dependentNomineeArr[dependentNomineeArrCount]=str;
	dependentNomineeArrCount=parseInt(dependentNomineeArrCount)+1;
}	
	
function startupAjaxFormValidation(buttonName)
{
	if(buttonName == 'Close')
	{
		document.frmFamily.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
		document.frmFamily.submit();
	}
	else
	{
		var addrName ='familyPersonAddress';
		var addrLookUpName= 'Permanent Address';			
		var addressArray = addressParameters(addrName, addrLookUpName);  // returns an array of address parameters			

		var depedentCmbText=document.getElementById('depedentCmb').options[document.getElementById('depedentCmb').selectedIndex].value;
		if(depedentCmbText=='Fm_dep_Yes')
		{	
			document.getElementById('dependentValue').value='Y';
		}
		else if(depedentCmbText=='Fm_dep_No')
		{
			document.getElementById('dependentValue').value='N';
		}
		else
		{
			document.getElementById('dependentValue').value='';
		}
		var validationArr=new Array('lastName','firstName','Relation','status','DOB','Employment','Gender','Occupation','MaritalStatus','Qualification');			 													 
		var totalArray=new Array('lastName','firstName','middleName','Relation','status','DOB','Employment','Gender','Occupation','MaritalStatus','Qualification'
				,'Remarks','otherRelation','dependentValue','date_Demise','designation','annual_income','Name_of_Company','sendAttachmentId','other_occu','Nationality','Dept','SubQualification','discipline'); 

		var statusValidation = Family_validateSpecificFormFields(validationArr);			
		totalArray = totalArray.concat(addressArray);
		document.frmFamily.Submit.disabled=false;	
		if (workFlowEnabled)document.frmFamily.Save_Draft.disabled=false;	//IFMS


		if(statusValidation=='error'){}		
		else if(statusValidation == true)
		{		
			document.getElementById('errorMessage').style.display='none';	
			if(workFlowEnabled==false)				
			{			
				var submitFlag = false; 

				employmentCombo=document.getElementById('employment');
				showemployment=employmentCombo.value;

				RelationCombo=document.getElementById('Relation');													
				showOtherRelation=RelationCombo.value;	

				occupationCombo=document.getElementById('Occupation');	
				showOccupation=occupationCombo.value;

				statusCombo=document.getElementById('status').value;

				if(statusCombo=='fm_Dead')
				{
					var fieldArray = new Array('lastName','firstName','Relation','Gender','DOB','date_Demise','status','MaritalStatus');		
					var submitFlag = validateSpecificFormFields(fieldArray); 
				}
				else if(showOtherRelation=='fm_rel_Other' && statusCombo=='fm_Dead')
				{
					var fieldArray = new Array('lastName','firstName','otherRelation','Gender','DOB','date_Demise','status','MaritalStatus');		
					var submitFlag = validateSpecificFormFields(fieldArray); 
				}

				if(statusCombo!='fm_Dead')
				{
					if(showOtherRelation=='fm_rel_Other')
					{
						var fieldArray = new Array('lastName','firstName','otherRelation','Gender','DOB','status','MaritalStatus','depedentCmb','Employment','Nationality');	
						var submitFlag = validateSpecificFormFields(fieldArray); 
					} 
					else if(showOtherRelation=='fm_rel_Other' && (showemployment == 'fm_Employed' || showemployment=='fm_self_employeed')) 
					{
						var fieldArray = new Array('lastName','firstName','otherRelation','Gender','DOB','status','MaritalStatus','depedentCmb','Nationality','Occupation'); 	
						var submitFlag = validateSpecificFormFields(fieldArray); 
					}
					else if(showemployment == 'fm_Employed' &&  showOccupation=='Government_org')// Change By sunil on 04/06/08 for Employment Dtls 
					{
						var fieldArray = new Array('lastName','firstName','Relation','Gender','DOB','status','MaritalStatus','depedentCmb','Nationality','Dept');			
						var submitFlag = validateSpecificFormFields(fieldArray); 
					}
					else if(showemployment == 'fm_Employed' || showemployment=='fm_self_employeed')
					{
						var fieldArray = new Array('lastName','firstName','Relation','Gender','DOB','status','MaritalStatus','depedentCmb','Nationality','Occupation');			
						var submitFlag = validateSpecificFormFields(fieldArray); 
					}
					else
					{
						var fieldArray = new Array('lastName','firstName','Relation','Gender','DOB','status','MaritalStatus','depedentCmb','Employment','Nationality');			
						var submitFlag = validateSpecificFormFields(fieldArray); 
					}
				}

				if(submitFlag)
				{
					/* Address Validation Starts */
					var addrName ='familyPersonAddress';
					var addrLookUpName= 'Permanent Address'; 
					var addressArray = addressParameters(addrName, addrLookUpName);  // returns an array of address parameters												

					if(isAddressClosed(addrName)== false)				
					{
						submitFlag =  validateSpecificFormFields(addressArray);		
					}
					/* Address Validation Ends */
				}
			}
			else{var submitFlag = true;}
			if(submitFlag==true)
			{	startProcess();		
			if(buttonName == 'Add')
			{
				document.getElementById('srNo').value=0;	
				if(workFlowEnabled)
				{
					addOrUpdateRecord('addRecord','addOrEditFamilyVOGEN&workFlowEnabled='+ workFlowEnabled +'&parentId='+0,totalArray);
				}
				else
				{
					addOrUpdateRecord('addRecordInTable','addOrEditFamilyVOGEN&workFlowEnabled='+ workFlowEnabled +'&parentId='+0,totalArray);
				}				
			}
			else
			{																								
				var num=document.getElementById('srNo').value;
				if(isNaN(num)==true){num=0;};	
				if(workFlowEnabled)
				{
					addOrUpdateRecord('updateRecord','addOrEditFamilyVOGEN&workFlowEnabled='+ workFlowEnabled +'&parentId='+num,totalArray);
				}
				else
				{
					addOrUpdateRecord('saveDataInTable','addOrEditFamilyVOGEN&workFlowEnabled='+ workFlowEnabled +'&parentId='+num,totalArray);
				}
				dbRecord=0;parentId=0;
			}
			/*Chekc Valid Data Or Not*/
			if(draftArrCount != -1)
			{						  		
				var x;
				for (x in draftArr)
				{			
					if(draftArr[x] == chkValidateRow)
					{																		 
						draftArr.splice(x,1);
						if(draftArrCount==0){draftArrCount=-1;}
						else {draftArrCount = draftArrCount-1;}
					}
				}										  		
			}
			/*End Of Chk*/													
			}
		}
	}			
} 		

function resetData(rowNum) 
{						
	if(document.frmFamily.status.value=='fm_Dead')	
		document.frmFamily.date_Demise.value=''; 
	removeRowFromTableattachmentBiometricFamily(rowNum,'frmFamily');									
	document.frmFamily.lastName.value='';
	document.frmFamily.firstName.value='';
	document.frmFamily.middleName.value='';
	document.frmFamily.Relation.value='Select';
	document.frmFamily.status.value='Select';
	document.frmFamily.Gender.value='Select';
	document.frmFamily.otherRelation.value='';				
	document.frmFamily.DOB.value='';
	document.frmFamily.Employment.value='Select';
	document.frmFamily.Occupation.value='Select';
	document.frmFamily.MaritalStatus.value='Select';				
	document.frmFamily.Qualification.value='Select';
	
	document.frmFamily.SubQualification.length=1;
	document.frmFamily.discipline.length=1;
	document.getElementById('diciplineId').style.display='none';
	
	document.frmFamily.Remarks.value='';				
	document.frmFamily.personAge.value='';	
	document.frmFamily.Dept.value='Select'; 
	resetAddress('familyPersonAddress');
	closeAddress('familyPersonAddress'); 

	document.frmFamily.Nationality.value='Select';
	document.frmFamily.depedentCmb.value='Select';

	document.getElementById('Name_of_Company').value='';
	document.getElementById('annual_income').value='';
	document.getElementById('designation').value='';
	globalFlagDeleteDisable = false;
	document.getElementById('employedData').style.display='none';
	document.getElementById('otherRelation').value='';
	document.getElementById('other1').style.display='none';	
	document.getElementById("member_Date_of_Demise").style.display='none';
	document.frmFamily.Save.disabled=true;
	document.frmFamily.Add.disabled=false;
	dbRecordUpdateOrDelete=false;				
	depedenetMember=-1;
	resetAllFlag();	

	if (document.getElementById('FamilyDataTable') != null)	
	{
		var rows = document.getElementById('FamilyDataTable').rows.length;
		if (rows > 1 && document.getElementById('familyCurrentNoRecords')!=null)
			document.getElementById('familyCurrentNoRecords').style.display='none';
	}	
	
	hideInfoForDeadMember(document.frmFamily.status); //update by sandip
	
	document.getElementById('errorBox').value='';		
	document.getElementById('errorMessage').style.display='none';	
}  
		
function editDBRecord(rowId, rowNum)
{
	globalflagForDBRecord=true;
	sendAjaxRequestForEditAttachment(rowId, 'populateForm','attachmentBiometricFamily',rowNum);
	document.frmFamily.Add.disabled=true;		
	document.frmFamily.Save.disabled=false;		
}

function saveDataInTable()
{
	if (xmlHttp.readyState == 4) 
	{
		if (xmlHttp.status == 200) 
		{	
			combineFMName();
			var displayFieldArray = new Array('firstName','middleName','lastName','Gender','Relation','status','DOB','Employment','Occupation','MaritalStatus','Qualification','Remarks');

			eprofileMyCmbFieldValue(displayFieldArray);	
			
			var checkFlag=false;
			checkFlag=checkRecForMotherAndFather();
			
			if(checkFlag)
			{
				if(globalflagForDBRecord==false)														
				{
					rowNum = updateDataInTableAttachment('FamilyDataTable','encXML', displayFieldArrayInTable);
				}
				else
				{
					rowNum = updateDataInTableAttachment('FamilyDataTable','addedPunch', displayFieldArrayInTable);
				}
			}
			else
			{
				checkRecForMotherAndFatherResponse();
				return;
			}
			
			document.frmFamily.Add.disabled=false;		
			document.frmFamily.Save.disabled=true;
			resetData(rowNum);	
			endProcess();
		}
	}
}

function editRecord(rowId,rowNum) 
{				
	updateRow=null;
	eprofileRowNum=rowNum;	
	var saveToAddMissingData='';			
	try {				
		var hField = rowId.substring(3, rowId.length);
		var xmlFileName = document.getElementById(hField).value;
		saveToAddMissingData=xmlFileName;
		rowIdArr=xmlFileName.split("$/$");
		document.getElementById(hField).value=rowIdArr[0];								
	}catch(e){}			
	var x,flag=0;
	for(x in dbRowsId)
	{
		var ojbOfdbRowsId = dbRowsId[x].toString();				
		if(rowId==ojbOfdbRowsId)
		{					
			flag=1;
		}		
	}				

	chkValidateRow=rowId;
	if(flag==1)
	{
		dbRecord=1;
		dbRowId=rowId;
		dbRecordUpdateOrDelete = true;
	}else	{dbRecordUpdateOrDelete = false;}											
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
	editRowId=rowId;
	document.frmFamily.Save.disabled=false;	
	document.frmFamily.Add.disabled=true;					
	sendAjaxRequestForEditAttachment(rowId, 'populateForm','attachmentBiometricFamily',rowNum);
	if(saveToAddMissingData!='')
	{
		document.getElementById(hField).value=saveToAddMissingData;							
	}				
	globalFlagDeleteDisable = true;	
}		
	
function deleteEmpFamilyRecord(rowId,flag)
{
	if(globalFlagDeleteDisable == false)
	{
		var answer;					
		answer = deleteRecord(rowId);					
		var dbFalg=0;					
		if(answer==true)
		{		
			/***Mapping Array Code for **/																		
			var x1,checkMe=0;														
			for (x1 in mappingArr)
			{
				var map =mappingArr[x1].toString();									
				var v1=map.split("/");	
				if(v1[1] == rowId)
				{								
					var x2,showFlag=0,showme;
					var x ;
					for(x in dbRowsSrNo)
					{
						var getSrNo = dbRowsSrNo[x].toString();		
						var srNoObj=getSrNo.split('/');										
						if(v1[0] == srNoObj[0])
						{
							trow= document.getElementById(srNoObj[1]);																								
							trow.childNodes[11].style.display='';		
							dbFalg=1;
							mappingArr.splice(x1,1);																			
						}
					}
					if(dbFalg==0)
					{
						trow= document.getElementById(v1[0]);																								
						trow.childNodes[11].style.display='';	
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
		}					
		if(document.getElementById('FamilyDataTable').rows.length==1) 
		{						
			document.getElementById('FamilyDataTable').style.display='none';	
			document.getElementById('familyCurrentNoRecords').style.display='';	
		}							
	}
	else
	{
		alert(FamilyDtlsAlertMsgArr[4]);
	}
}	

function Reload(id)
{									
	try 
	{
		var delRow = document.getElementById("deleteAppArrCount"+id);
		trow= document.getElementById(id);
		trow.childNodes[11].style.display='';			
		delRow.parentNode.deleteRow(delRow.rowIndex);								

		document.getElementById('FamilyApproveDataTable').style.display='';					

		if(document.getElementById('FamilyDataTable').rows.length==1) {
			document.getElementById('FamilyDataTable').style.display='none';					
		}			
		/***Mapping Array Code for **/						
		var x1;
		for (x1 in mappingArr)
		{
			var map =mappingArr[x1].toString();									
			var v1=map.split("/");	
			if(v1[1] == id)
			{
				trow= document.getElementById(v1[0]);																
				trow.childNodes[11].style.display=''; 
				mappingArr.splice(x1,1);
			}
		}																					
		/**End of Mapping Code **/												
		/***For Draft Counting ****/												
	}catch(e)
	{					
		var delRow=document.getElementById(id);	
		delRow.parentNode.deleteRow(delRow.rowIndex);			
		var x1,checkMe=0;														
		for (x1 in mappingArr)
		{
			var map =mappingArr[x1].toString();									
			var v1=map.split("/");	
			if(v1[1] == id)
			{								
				var x2,showFlag=0,showme;
				var x 
				for(x in dbRowsSrNo)
				{
					var getSrNo = dbRowsSrNo[x].toString();		
					var srNoObj=getSrNo.split('/');										
					if(v1[0] == srNoObj[0])
					{
						trow= document.getElementById(srNoObj[1]);																								
						trow.childNodes[11].style.display='';	
						mappingArr.splice(x1,1);																			
					}
				}
			}
		}
	}			
	var x1;					
	for (x1 in deleteAppArr)
	{
		var map =deleteAppArr[x1].toString();						
		if(map == id)
		{					
			deleteAppArr.splice(x1,1);
			if(deleteAppArrCount==0){}
			else{deleteAppArrCount=deleteAppArrCount-1;}
		}
	}
	if(draftArrCount != -1)
	{						  		
		var x;
		for (x in draftArr)
		{																	
			if(draftArr[x] == id)
			{										 
				draftArr.splice(x,1);							
				if(draftArrCount==0){draftArrCount=-1;}
				else {draftArrCount = draftArrCount-1;}
			}
		}										  		
	}			
	/***End of Draft Counting***/
}	

function deleteFamilyDBRecord(id)
{		
	if(workFlowEnabled){			
		var i=1;	
		document.frmFamily.Submit.disabled=false;	
		if(workFlowEnabled)document.frmFamily.Save_Draft.disabled=false;//IFMS
		trow= document.getElementById(id);								
		deleteAppArr[deleteAppArrCount]=id;
		deleteAppArrCount=deleteAppArrCount+1;				
		document.getElementById("FamilyDataTable").style.display='';
		var trow1=document.getElementById("FamilyDataTable").insertRow();
		trow1.id="deleteAppArrCount"+id;
		trow1.insertCell(0).innerHTML = "<INPUT type='hidden' name='aERB' id='aERB' value=''/>";				
		trow1.cells[0].style.display = 'none';
		trow1.insertCell(i).innerHTML="<font color='blue'>"+trow.cells[i].innerText+"</font>";
		trow1.insertCell(i+1).innerHTML="<font color='blue'>"+trow.cells[i+1].innerText+"</font>";
		trow1.insertCell(i+2).innerHTML="<font color='blue'>"+trow.cells[i+2].innerText+"</font>";
		trow1.insertCell(i+3).innerHTML="<font color='blue'>"+trow.cells[i+3].innerText+"</font>";
		trow1.insertCell(i+4).innerHTML="<font color='blue'>"+trow.cells[i+4].innerText+"</font>";
		trow1.insertCell(i+5).innerHTML="<font color='blue'>"+trow.cells[i+5].innerText+"</font>";
		trow1.insertCell(i+6).innerHTML="<font color='blue'>"+trow.cells[i+6].innerText+"</font>";
		trow1.insertCell(i+7).innerHTML="<font color='blue'>"+trow.cells[i+7].innerText+"</font>";
		trow1.insertCell(i+8).innerHTML="<font color='blue'>"+trow.cells[i+8].innerText+"</font>";
		trow1.insertCell(i+9).innerHTML="<font color='blue'>"+trow.cells[i+9].innerText+"</font>";
		trow1.insertCell(i+10).innerHTML="<a href=javascript:void('Reload') onclick=javascript:Reload('"+id+"')>Undo Delete</a>";
		trow.childNodes[11].style.display='none';
	}
	else
	{
		var flag = deleteDBRecord(id);	
		if(flag)
		{					
			if(document.getElementById('FamilyDataTable').rows.length==1)
			{
				trow=document.getElementById(id);
				trow.cells[3].innerText='0';
				document.getElementById('FamilyDataTable').style.display='none';
			}
			else
			{
					trow=document.getElementById(id);
					trow.cells[3].innerText='0';
			}	
		}
	}
}			

function addRecord() 
{   		  	    
	if (xmlHttp.readyState == 4)
	{		  		
		count=parseInt(count)+1;	
		combineFMName();
		var displayFieldArray = new Array('firstName','middleName','lastName','Gender','Relation','status','DOB','Employment','Occupation','MaritalStatus','Qualification','Remarks');
		eprofileMyCmbFieldValue(displayFieldArray);	
		var rowNum = addDataInTableAttachment('FamilyDataTable','encXML',displayFieldArrayInTable,'editRecord','deleteEmpFamilyRecord','');				
		var addedRecordId='rowencXML'+parseInt(counter-1);
		if(draftFlag == 1)
		{
			draftArrCount= draftArrCount+1;  		
			draftArr[draftArrCount] = addedRecordId;
			draftFlag = 0;
		}
		resetData(rowNum);
		endProcess(); 
	}			   	
}			

function addRecordInTable()
{
	var checkFlag=false;
	checkFlag=checkRecForMotherAndFather();
	
	if(checkFlag)
	{
		count=parseInt(count)+1;	
		combineFMName();
		var displayFieldArray = new Array('firstName','middleName','lastName','Gender','Relation','status','DOB','Employment','Occupation','MaritalStatus','Qualification','Remarks');
		eprofileMyCmbFieldValue(displayFieldArray);	
		var rowNum = addDataInTableAttachment('FamilyDataTable','encXML',displayFieldArrayInTable,'editRecord','deleteEmpFamilyRecord','');				
		var addedRecordId='rowencXML'+parseInt(counter-1);
		resetData(rowNum);
	}
	else
	{
		checkRecForMotherAndFatherResponse();
		return;
	}
	endProcess(); 
}

function updateRecord() 
{		
	if (xmlHttp.readyState == 4) 
	{ 	
		if(document.getElementsByName('Remarks').value == '' || document.getElementsByName('Remarks').value==null){document.getElementsByName('Remarks').value='';}

		var displayFieldArray = new Array('firstName','middleName','lastName','Gender','Relation','status','DOB','Employment','Occupation','MaritalStatus','Qualification','Remarks');
		eprofileMyCmbFieldValue(displayFieldArray);	
		
		
		combineFMName();	
				  			  								
		if (dbRecordUpdateOrDelete==true)
		{
			trow= document.getElementById(editRowId);														
			trow.childNodes[11].style.display='none';
			dbRecordUpdateOrDelete=false;	
			count=parseInt(count)+1;				  		

			if(workFlowEnabled) 
			{
				var rowNum = eprofileAddDataInTableAttachment('FamilyDataTable', 'encXML', displayFieldArrayInTable,'editRecord','deleteEmpFamilyRecord','',document.getElementById('sendAttachmentId').value);
			}
			else
			{
			 	mappingId = addDataInTable('FamilyDataTable', 'encXML', displayFieldArrayInTable,'editRecord','deleteEmpFamilyRecord','');
			 }

			var mappingId = 'rowencXML'+parseInt(counter-1);
			/*For Mapping Purpose Between */
			var map1 =editRowId;
			var map2 ='/'+mappingId;						
			var map = map1.concat(map2);
			mappingArr[mappingArrCount]=map;
			mappingArrCount= mappingArrCount+1;
			/*End of Mapping a Code*/												
		}
		else 
		{
			var rowNum = updateDataInTableAttachment('FamilyDataTable','encXML', displayFieldArrayInTable);
		}			  															
		globalFlagDeleteDisable = false;
		resetData(rowNum);							
		endProcess();
	}
}

function populateForm() 
{				
	if (xmlHttp.readyState == 4) 
	{ 				  
		var decXML = xmlHttp.responseText;

		// Start For Multipal  Attachment
		var xmlDOM = populateAttachment(decXML,'frmFamily');	
		try {					
			var str = getXPathValueFromDOM(xmlDOM, 'cmnAttachmentMst/attachmentId');
			if(str!='' || str!=null || isNaN(str)==true)
				document.getElementById('sendAttachmentId').value=str;					
			else
				document.getElementById('sendAttachmentId').value=0;
		}catch(ex){document.getElementById('sendAttachmentId').value=0;}
		// Ends For Multipal  Attachment					

		var str;
		try
		{
			document.getElementById('srNo').value=getXPathValueFromDOM(xmlDOM, 'memberId');
			if(document.getElementById('srNo').value=='null' || document.getElementById('srNo').value==null || document.getElementById('srNo').value=='')
			{
				document.getElementById('srNo').value=getXPathValueFromDOM(xmlDOM, 'rowNumber');
			}
		}catch(e)
		{
			document.getElementById('srNo').value=getXPathValueFromDOM(xmlDOM, 'rowNumber');
		}

		//For Address
		getFieldGroupObj(document.getElementById("countryStateTablefamilyPersonAddress"));
		var addrXPath = 'cmnAddressMst';
		editAddress('familyPersonAddress',xmlDOM,addrXPath); 	

		str=getXPathValueFromDOM(xmlDOM, 'fmLastName');
		if(str==null || str=='' || str=='null'){document.frmFamily.lastName.value='';}
		else{document.frmFamily.lastName.value=str;}
		str=getXPathValueFromDOM(xmlDOM, 'fmFirstName');
		if(str==null  || str=='' || str=='null'){str='';}
		document.frmFamily.firstName.value=str;
		str=getXPathValueFromDOM(xmlDOM, 'fmMiddleName');
		if(str==null  || str=='' || str=='null'){str='';}
		document.frmFamily.middleName.value=str;
		str=getXPathValueFromDOM(xmlDOM, 'fmRelationOther');
		if(str==null  || str=='' || str=='null'){str='';}
		document.frmFamily.otherRelation.value=str;

		str=getXPathValueFromDOM(xmlDOM, 'fmDateOfBirth');
		if(str==null  || str=='' || str=='null'){str='';}					
		else
		{
			try{
				str = str.substring(0,10);
				document.frmFamily.DOB.value=setAgeDateFormat(str);
			}
			catch(e){}
		}							

		str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByFmRelation/lookupName');	
		if(str==-1 || str=='null'|| str==null){str='Select';}									
		if(isNaN(str)==false)	{document.frmFamily.Relation.value=str;}
		else 
		{
			RelationCombo=document.frmFamily.Relation;
			for(var i=0; i<RelationCombo.options.length;i++)
			{
				if(RelationCombo.options[i].value==str)
				{
					RelationCombo.options[i].selected=true;
					genderCmbEnableDisable(str);
				}										
			}						
		}	

		if(str == 'fm_rel_Other')
		{
			document.getElementById('other1').style.display='';
		}				


		str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByFmGender/lookupName');							
		if(str==-1 || str=='null'|| str==null){str='Select';}
		if(isNaN(str)==false)	{document.frmFamily.Gender.value=str;}
		else 
		{
			RelationCombo=document.frmFamily.Gender;
			for(var i=0; i<RelationCombo.options.length;i++)
			{
				if(RelationCombo.options[i].value==str)
				{
					RelationCombo.options[i].selected=true;
				}										
			}
		}

		str=getXPathValueFromDOM(xmlDOM, 'cmnCountryMstByFmNationality/countryCode');	
		if(str==-1 || str=='null'|| str==null || str=='0'){str='Select';}
		if(isNaN(str)==false){document.frmFamily.Nationality.value=str;}
		else 
		{
			RelationCombo=document.frmFamily.Nationality;
			for(var i=0; i<RelationCombo.options.length;i++)
			{
				if(RelationCombo.options[i].value==str)
				{
					RelationCombo.options[i].selected=true;
				}										
			}
		}

		var strOccupation=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByFmOccupation/lookupName');		 					
		if(strOccupation==-1 || strOccupation=='null'|| strOccupation==null || strOccupation=='SELECT' || strOccupation=='select'){strOccupation='Select';}
		if(isNaN(strOccupation)==false){document.frmFamily.Occupation.value=strOccupation;}
		else{setOccuCombo=strOccupation;}

		strEmployment=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByFmEmploymentStatus/lookupName');
		if(strEmployment==-1 || strEmployment=='null'|| strEmployment==null){strEmployment='Select';}
		if(isNaN(strEmployment)==false)	{}
		else 
		{
			RelationCombo=document.frmFamily.Employment;
			for(var i=0; i<RelationCombo.options.length;i++)
			{
				if(RelationCombo.options[i].value==strEmployment)
				{
					RelationCombo.options[i].selected=true;
					if(strEmployment == 'fm_Employed' || strEmployment=='fm_self_employeed')
					{
						document.getElementById('employedData').style.display='';		
						getEmploymentComboList(document.frmFamily.Employment);		
					}
					else
					{
						document.getElementById('employedData').style.display='none';
					}
				}										
			}
		}

		str=getXPathValueFromDOM(xmlDOM, 'orgDepartmentMstByFmDept/depCode');
		if(str==-1 || str=='null'|| str==null)
		{
			if(strEmployment=='fm_Employed' && strOccupation=='Government_org'){str='other';}
			else{str='Select';}
		}
		document.getElementById('Dept').value=str;
		showForOtherDept();

		str=getXPathValueFromDOM(xmlDOM, 'otherOccupation');
		if(str==null || str=='' || str=='null'){str='';}
		document.getElementById('other_occu').value=str;	

		str=getXPathValueFromDOM(xmlDOM, 'companyName');
		if(str==null || str=='' || str=='null'){str='';}
		document.getElementById('Name_of_Company').value=str;					

		str=getXPathValueFromDOM(xmlDOM, 'designation');
		if(str==null || str=='' || str=='null'){str='';}
		document.getElementById('designation').value=str;

		str=getXPathValueFromDOM(xmlDOM, 'annualIncome');	
		if(str==null || str=='' || str=='null'){str='';}
		document.getElementById('annual_income').value=str;

		str=getXPathValueFromDOM(xmlDOM, 'dateOfDemise');	
		if(str==null || str=='' || str=='null'){str='';}
		else
		{						
			try{
				str = str.substring(0,10);
				document.frmFamily.date_Demise.value=setAgeDateFormat(str);
			}
			catch(e){}
		}					
		
		str=getXPathValueFromDOM(xmlDOM, 'dependentOrNot');
		document.getElementById('dependentValue').value=str;
		if(str==-1 || str=='null'|| str==null){}
		if(isNaN(str)==false)
		{
			document.frmFamily.depedentCmb.value="Select";
		}
		else 
		{
			RelationCombo=document.frmFamily.depedentCmb;
			for(var i=0; i<RelationCombo.options.length;i++)
			{
				var id = RelationCombo.options[i].value;
				if('Y'==str && id=='Fm_dep_Yes')
				{
					RelationCombo.options[i].selected=true;
				}
				if('N'==str && id=='Fm_dep_No')										
				{
					RelationCombo.options[i].selected=true;
				}
			}
		}

		str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByFmOccupation/lookupName');		
		if(str==-1 || str=='null'|| str==null){str='Select';}
		if(isNaN(str)==false)	{document.frmFamily.Occupation.value=str;}
		else 
		{
			RelationCombo=document.frmFamily.Occupation;
			for(var i=0; i<RelationCombo.options.length;i++)
			{
				if(RelationCombo.options[i].value==str)
				{
					RelationCombo.options[i].selected=true;
					displayOtherOccu();
					if(str=='fm_other_occupation')
					{
						str=getXPathValueFromDOM(xmlDOM, 'otherOccupation');
						if(str==null || str=='' || str=='null'){str='';}
						document.getElementById('other_occu').value=str;	
					}
				}										
			}
		}				
		try{	
			depedenetMember=getXPathValueFromDOM(xmlDOM, 'memberId');
		}catch(e){
			try {
				depedenetMember=getXPathValueFromDOM(xmlDOM, 'id.memberId');
			}catch(e1){depedenetMember=-1;}						
		}
		str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByFmMaritalStatus/lookupName');
		if(str==-1  || str=='null' || str==null){str='Select';}
		if(isNaN(str)==false)	{document.frmFamily.MaritalStatus.value=str;}
		else 
		{
			RelationCombo=document.frmFamily.MaritalStatus;
			for(var i=0; i<RelationCombo.options.length;i++)
			{
				if(RelationCombo.options[i].value==str)
				{
					RelationCombo.options[i].selected=true;
				}										
			}
		}

		str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstBySubQualification/lookupName');
		if(str==null || str=='' || str=='null' || str.search(/select/i)!=-1){setCombo='Select';}	
		else{setCombo=str;}

		var str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByFmQualification/lookupName');	
		var id = str;
		if(str==null || str=='' || str=='null' || str.search(/select/i)!=-1) {document.forms[0].Qualification.value='Select';}
		else
		{
			document.forms[0].Qualification.value=str;
			getSubqualificationsLst(document.frmFamily.Qualification);
		}						
		if(id=='null' || id==null || id=='edu_Higher_Secondary_School'  || id== 'edu_Secondary_School' || id=='edu_Primary_School' || id=='' || id.search(/select/i)!=-1)
		{
			document.getElementById('diciplineId').style.display='none';					
		}
		else
			document.getElementById('diciplineId').style.display='';

		str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByDiscipline/lookupName');	
		if(str==null || str=='' || str=='null'  || str.search(/select/i)!=-1){setdiscipline='Select';}	
		else{setdiscipline=str;}
					

		str=getXPathValueFromDOM(xmlDOM, 'fmRemarks');
		if(str==null  || str=='null'|| str==null){str='';}
		document.frmFamily.Remarks.value=str;

		str=getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByFmDeadOrAlive/lookupName');	
		if(str==-1  || str=='null'|| str==null){str='Select';}
		if(isNaN(str)==false)	{document.frmFamily.status.value=str;}
		else 
		{
			RelationCombo=document.frmFamily.status;
			for(var i=0; i<RelationCombo.options.length;i++)
			{
				if(RelationCombo.options[i].value==str)
				{
					RelationCombo.options[i].selected=true;
					if(str=='fm_Dead')
					{
						document.getElementById("member_Date_of_Demise").style.display='';
						hideInfoForDeadMember(document.frmFamily.status);	
					}
					else
						document.getElementById("member_Date_of_Demise").style.display='none';
					break;
				}										
			}
		}	
		
		countAge('',"personAge","value",'',"DOB","date_Demise","frmFamily","status");
		
		var id= document.frmFamily.Relation.options[document.frmFamily.Relation.selectedIndex].text;
		if(id == 'Other')
		{
			document.getElementById('other1').style.display='';
		}
		else 
		{
			document.getElementById('other1').style.display='none';	
		}
	}
}		

function countRows(countRows){if(count<countRows){count=countRows;dbRows=count;}}

/*function getAge(birthDate)
{
	try 
	{
		birthDate=birthDate.substring(0,10);
	}
	catch(e){}
	var v =birthDate;			
	v=setAgeDateFormat(v);
	countAge(v);
	return v;
}

function setAgeDateFormat(birthDate)
{
	var v= birthDate;
	if(birthDate.indexOf("-")!=-1)
	{				
		var splitDate=birthDate.split("-");				
		var byr=splitDate[0];
		var bmo=splitDate[1];
		var bday=splitDate[2];				
		v= bday+'/'+bmo+'/'+byr;
	}
	return v;
}

function countAge(birthDate)
{
	if(document.frmFamily.status.value=='fm_Dead')		
	{				
		var age=0; 
		var demiseValue=document.getElementById('date_demise').value;				
		var dobValue=document.frmFamily.DOB.value;	
		if(demiseValue!='' && dobValue!=='')
		{
			dobValue=setAgeDateFormat(dobValue);
			demiseValue=setAgeDateFormat(demiseValue);

			var splitDate=demiseValue.split("/");
			var bday_demise=parseInt(splitDate[0]);
			var bmo_demise=(parseInt(splitDate[1])-1);
			var byr_demise=parseInt(splitDate[2]);

			var splitDate=dobValue.split("/");
			var bday_dobValue=parseInt(splitDate[0]);
			var bmo_dobValue=(parseInt(splitDate[1])-1);
			var byr_dobValue=parseInt(splitDate[2]);

			if((bmo_demise > bmo_dobValue)||(bmo_demise==bmo_dobValue & bday_demise>=bday_dobValue)) {age=byr_dobValue;}				
			else  {age=byr_dobValue+1;}

			if(isNaN(byr_demise-age)==true){}
			else if((byr_demise-age)>150 || (byr_demise-age)<=-1)			
			{						
				document.getElementById('date_demise').value='';
				document.frmFamily.personAge.value='';
				alert(FamilyDtlsAlertMsgArr[5]);
			}
			else {document.frmFamily.personAge.value=byr_demise-age;}
		}					
	}
	else
	{
		var splitDate=birthDate.split("/");							
		var bday=parseInt(splitDate[0]);
		var bmo=(parseInt(splitDate[1])-1);
		var byr=parseInt(splitDate[2]);
		var age;
		var now = new Date();		
		document.getElementById('date_demise').value='';
		tday=now.getUTCDate();
		tmo=(now.getUTCMonth());
		tyr=(now.getUTCFullYear());
		if((tmo > bmo)||(tmo==bmo & tday>=bday)) {age=byr;}
		else  {age=byr+1}
		if(isNaN(tyr-age)==true)
		{
			document.frmFamily.DOB.value='';
			document.frmFamily.personAge.value='';
		}
		else if((tyr-age)>150 || (tyr-age)<=-1)			
		{			
			document.frmFamily.personAge.value='';
			document.frmFamily.DOB.value='';	
			alert(FamilyDtlsAlertMsgArr[5]);
		}
		else {	document.frmFamily.personAge.value=tyr-age;	}
	}
}

function Age()
{		
	birthDate=document.frmFamily.DOB.value;
	if(birthDate!=null)	{countAge(birthDate);}
	else {document.frmFamily.personAge.value='';}
}*/	

/**For the Pending Request Dtls Table*/	

function increment()
{			
	v1=v1+1;
	return v1;
}

function increment_pending()
{
	v1_pen=v1_pen+1;
	return v1_pen;
}

function viewPendingRequestDtls(reqId)
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
				alert(FamilyDtlsAlertMsgArr[6]);       
				return false;        
			}
		}			 
	}	
	checkDraftOrPendingReq=false;
	var url = "hdiits.htm?actionFlag=FamilyDetails&flag=getFamilyPendingRecordForView&reqId="+reqId;    
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
			if(checkDraftOrPendingReq==false) {delete_pendingReqRows(1);document.getElementById('FamilyPendingDataTable').style.display='';}
			else if(checkDraftOrPendingReq==true)	{delete_pendingReqRows(2);document.getElementById('FamilyDraftDataTable').style.display='';}																	

			var xmlStr = xmlHttp.responseText;	
			var XMLDoc=getDOMFromXML(xmlStr);
			var fname=XMLDoc.getElementsByTagName('FirstName');
			var mname=XMLDoc.getElementsByTagName('MidName');
			var lname=XMLDoc.getElementsByTagName('LastName');
			var empstatus = XMLDoc.getElementsByTagName('EmpStatus');
			var mstatus=XMLDoc.getElementsByTagName('Marital');
			var occu=XMLDoc.getElementsByTagName('Occupation');
			var remarks=XMLDoc.getElementsByTagName('Remarks'); 
			var qua=XMLDoc.getElementsByTagName('Qualification');
			var gen=XMLDoc.getElementsByTagName('Gender');
			var da=XMLDoc.getElementsByTagName('DeadOrAlive');
			var dob=XMLDoc.getElementsByTagName('DOB');			    	
			var relation=XMLDoc.getElementsByTagName('Relation');
			var reqFlag=XMLDoc.getElementsByTagName('RequestFlag');
			var j=0,k=1;
			for ( var i = 0 ; i < fname.length ; i++ )
			{     
				var fname1=fname[i].childNodes[0].text;	
				var mname1=mname[i].childNodes[0].text;  					     		
				var lname1=lname[i].childNodes[0].text;
				var empstatus1=empstatus[i].childNodes[0].text;
				var mstatus1=mstatus[i].childNodes[0].text;
				var occu1=occu[i].childNodes[0].text;
				var remarks1=remarks[i].childNodes[0].text;
				var qua1=qua[i].childNodes[0].text;
				var gen1=gen[i].childNodes[0].text;
				var da1=da[i].childNodes[0].text;     					     					     					
				var dob1=dob[i].childNodes[0].text;
				var relation1=relation[i].childNodes[0].text;
				var reqFlag1=reqFlag[i].childNodes[0].text;

				if(fname1=='null'){fname1="-";}	 	     						     					
				if(mname1=='null'){mname1="";} 
				if(lname1=='null'){lname1='-';}

				var reqFmName='';
				if(fname1=='-' && mname1=='' && lname1=='-')
				{
					reqFmName='-';
				}
				else
				{
					reqFmName=fname1+" "+mname1+" "+lname1;
				}
				
				if(empstatus1=='null'||empstatus1==FamilyDtlsAlertMsgArr[7]){empstatus1='-';}
				if(occu1=='null'||occu1==FamilyDtlsAlertMsgArr[7]){occu1='-';}
				if(mstatus1=='null'||mstatus1==FamilyDtlsAlertMsgArr[7]){mstatus1='-';}	
				if(remarks1=='null'){remarks1='-';}	
				if(dob1=='null'){dob1='-';}	
				if(gen1=='null'||gen1==FamilyDtlsAlertMsgArr[7]){gen1='-';}
				if(occu1=='null'||occu1==FamilyDtlsAlertMsgArr[7]){occu1='-';}
				if(qua1=='null'||qua1==FamilyDtlsAlertMsgArr[7]){qua1='-';}
				if(da1=='null'){da1='-';}
				if(relation1=='null'||relation1==FamilyDtlsAlertMsgArr[7]){relation1='-';}
				if(reqFlag1=='null'){reqFlag1='';}

				if(checkDraftOrPendingReq==false)
				{
					trow= document.getElementById('FamilyPendingDataTable').insertRow();	
					trow.id= 'pendingDtlsRows';					    		
				}
				else if(checkDraftOrPendingReq==true)
				{
					trow= document.getElementById('FamilyDraftDataTable').insertRow();
					trow.id= 'draftDtlsRows';
				}				  				    	  	   						   					
				trow.insertCell(j).innerHTML = k;
				k=parseInt(k)+1;	
				trow.insertCell(j+1).innerHTML=reqFmName;
				trow.insertCell(j+2).innerHTML=gen1;	   					
				trow.insertCell(j+3).innerHTML=relation1;
				trow.insertCell(j+4).innerHTML=da1;	
				trow.insertCell(j+5).innerHTML=dob1;	   					
				trow.insertCell(j+6).innerHTML=empstatus1;	
				trow.insertCell(j+7).innerHTML=occu1;	
				trow.insertCell(j+8).innerHTML=mstatus1;	
				trow.insertCell(j+9).innerHTML=qua1;		   						   						   					
				trow.insertCell(j+10).innerHTML=remarks1;

				if(reqFlag1=='U') 
				{
					trow.insertCell(j+11).innerHTML=FamilyDtlsAlertMsgArr[8];
				}
				else if(reqFlag1=='D') 
				{
					trow.insertCell(j+11).innerHTML=FamilyDtlsAlertMsgArr[9];
				}
				else 
				{
					trow.insertCell(j+11).innerHTML=FamilyDtlsAlertMsgArr[10];
				}
				if(checkDraftOrPendingReq==false)
				{			   						   																								
					trow.insertCell(j+12).innerHTML="<a href=javascript:void('Hide') onclick=javascript:eprofileHideObj('FamilyPendingDataTable')>"+FamilyDtlsAlertMsgArr[11]+"</a>";
				}
				else
				{
					trow.insertCell(j+12).innerHTML="<a href=javascript:void('Hide') onclick=javascript:eprofileHideObj('FamilyDraftDataTable')>"+FamilyDtlsAlertMsgArr[11]+"</a>";     					
				}
				j=0;
			}
		}
	}
}

/***delete previously opend pending rows ***/

function delete_pendingReqRows(flag)
{
	for(i=0;i<document.frmFamily.elements.length;i++)
	{   	            					
		if(document.getElementById('pendingDtlsRows') && flag==1)
		{
			var delRow = document.getElementById('pendingDtlsRows');						
			delRow.parentNode.deleteRow(delRow.rowIndex);													
		}
		if(document.getElementById('draftDtlsRows') && flag==2)
		{
			var delRow = document.getElementById('draftDtlsRows');						
			delRow.parentNode.deleteRow(delRow.rowIndex);
		}
	}
}
/****end of delete_pendingReqRows**/
/**End of Pending Request Js*/

/**####################### Save As Draft Request Related Script--START ##########################################*/

function increment_draft()
{			
	v1_d=v1_d+1;
	return v1_d;
}

function saveFamilyDtlsAsDraft(totalArray,buttonName)
{			

	var num=document.getElementById('srNo').value;
	if(isNaN(num)==true){num=0;};
	if(buttonName !='Add')
	{						
		var num=0;											
		if(draftFlag == 3){draftFlag = 4;}																		
		addOrUpdateRecord('updateRecord','addOrEditFamilyVOGEN&parentId='+num,totalArray);				
	}
	else 
	{		
		draftFlag = 1;		 
		addOrUpdateRecord('addRecord','addOrEditFamilyVOGEN&parentId='+0,totalArray);																			
	}

}	

function viewDraftRequestDtls(reqId)
{
	checkDraftOrPendingReq=true;
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
				alert(FamilyDtlsAlertMsgArr[6]);       
				return false;        
			}
		}			 
	}					
	var url = "hdiits.htm?actionFlag=FamilyDetails&flag=getFamilyDraftRecordForView&reqId="+reqId; 
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange=pendingRequestResponse; 
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}

function deleteDraftRequestDtls(reqId)
{
	if(confirm('Are you sure do you want to delete Request?')==true)
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
					alert(FamilyDtlsAlertMsgArr[6]);       
					return false;        
				}
			}			 
		}		        	
		var url = "hdiits.htm?actionFlag=FamilyDetails&flag=deleteFamilyDraftRecord&reqId="+reqId; 
		xmlHttp.open("POST", encodeURI(url) , true);			
		xmlHttp.onreadystatechange=deleteDraftresponse; 
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}
}

function deleteDraftresponse()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{ 					
			var xmlStr = xmlHttp.responseText;					    	
			var XMLDoc=getDOMFromXML(xmlStr);   					    			    					    					    							    	
			var SrNo = XMLDoc.getElementsByTagName('DeleteDraft');	
			var link = XMLDoc.getElementsByTagName('Link');	
			for ( var i = 0 ; i < link.length ; i++ )
			{
				var showlink= link[i].childNodes[0].text;
				if(showlink!='null'  && showlink!='')
				{
					var check = 'rowencXML'+showlink;
					var x1,deleteFlag=0;
					for (x1 in mappingArr)
					{
						var map =mappingArr[x1].toString();													
						var v1=map.split("/");				
						if(v1[0] == check)
						{
							deleteFlag=1;						
						}
					}

					if(deleteFlag==0)
					{
						for(x in dbRowsSrNo)
						{
							var getSrNo = dbRowsSrNo[x].toString();		
							var srNoObj=getSrNo.split('/');										
							if(showlink == srNoObj[0])
							{
								trow= document.getElementById(srNoObj[1]);																								
								trow.childNodes[11].style.display='';		
							}
						}     														
					}
				}
			}
			var deleteId= SrNo[0].childNodes[0].text;				    				    	
			var delRow = document.getElementById(deleteId);						
			delRow.parentNode.deleteRow(delRow.rowIndex);
			if(document.getElementById("FamilyDraftDtlsTab").rows.length==1)
			{
				document.getElementById("FamilyDraftDtlsTab").style.display='none';
			}
			else
			{
				var srno=1;	
				var num = document.getElementById('FamilyDraftDataTable').rows.length;								
				for(var i=1;i<num;i++)
				{
					trow= document.getElementById('FamilyDraftDtlsTab').rows[i];																																		
					try{
						trow.childNodes[0].innerHTML=srno;								
					}catch(e){break;}
					srno=srno+1;
				}
			}
			document.getElementById('FamilyDraftDataTable').style.display='none';	
		}
	}
}

function openDraftRequestDtls(reqId)
{
	openReqId = reqId;
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
				alert(FamilyDtlsAlertMsgArr[6]);       
				return false;        
			}
		}			 
	}					
	var url = "hdiits.htm?actionFlag=FamilyDetails&flag=openFamilyDraftRequest&reqId="+reqId; 
	xmlHttp.open("POST", encodeURI(url) , true);						
	xmlHttp.onreadystatechange=openFamilyDraftRequest; 
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}	
	
function openFamilyDraftRequest()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;	
			var XMLDoc=getDOMFromXML(xmlStr);			    	
			var fname=XMLDoc.getElementsByTagName('FirstName'); 
			var mname=XMLDoc.getElementsByTagName('MidName');
			var lname=XMLDoc.getElementsByTagName('LastName');
			var empstatus = XMLDoc.getElementsByTagName('EmpStatus');
			var mstatus=XMLDoc.getElementsByTagName('Marital');
			var occu=XMLDoc.getElementsByTagName('Occupation');
			var remarks=XMLDoc.getElementsByTagName('Remarks'); 
			var qua=XMLDoc.getElementsByTagName('Qualification');
			var gen=XMLDoc.getElementsByTagName('Gender');
			var da=XMLDoc.getElementsByTagName('DeadOrAlive');
			var dob=XMLDoc.getElementsByTagName('DOB');			    	
			var relation=XMLDoc.getElementsByTagName('Relation');					
			var reqFlag=XMLDoc.getElementsByTagName('RequestFlag');
			var removeRow = XMLDoc.getElementsByTagName('RemoveRow');
			var XML=  XMLDoc.getElementsByTagName('XMLFile');
			var attch = XMLDoc.getElementsByTagName('Attchment');
			var j=0,k=1;
			var addFlag=0,valK=0;

			if (fname.length >=1 && document.getElementById('familyCurrentNoRecords')!=null)
				document.getElementById('familyCurrentNoRecords').style.display='none';
			document.getElementById('FamilyDataTable').style.display='';

			for ( var i = 0 ; i < fname.length ; i++ )
			{     			
				var fname1=fname[i].childNodes[0].text;	     					
				var mname1=mname[i].childNodes[0].text;
				var lname1=lname[i].childNodes[0].text;
				var empstatus1=empstatus[i].childNodes[0].text;
				var mstatus1=mstatus[i].childNodes[0].text;
				var occu1=occu[i].childNodes[0].text;
				var remarks1=remarks[i].childNodes[0].text;
				var qua1=qua[i].childNodes[0].text;
				var gen1=gen[i].childNodes[0].text;
				var da1=da[i].childNodes[0].text;     					     					     					
				var dob1=dob[i].childNodes[0].text;
				var relation1=relation[i].childNodes[0].text;
				var reqFlag1=reqFlag[i].childNodes[0].text;
				var xml1 = XML[i].childNodes[0].text;   
				var sendAttchmentId =attch[i].childNodes[0].text;    					 
				if(sendAttchmentId=='null'){sendAttchmentId="";}
				
				if(fname1=='null'){fname1='-';}	 	     						     					
				if(mname1=='null'){mname1='';} 
				if(lname1=='null'){lname1='-';}
				var openReqFMName='';
				if(fname1=='-' && mname1=='' && lname1=='-')
				{
					openReqFMName='-';
				}
				else
				{
					openReqFMName=fname1+" "+mname1+" "+lname1;
				}
				
				if(empstatus1=='null'||empstatus1==FamilyDtlsAlertMsgArr[7]){empstatus1='-';}
				if(occu1=='null'|| occu1==FamilyDtlsAlertMsgArr[7]){occu1='-';}
				if(mstatus1=='null'||mstatus1==FamilyDtlsAlertMsgArr[7]){mstatus1='-';}	
				if(remarks1=='null'){remarks1='-';}	
				if(dob1=='null'){dob1='-';}	
				if(gen1=='null'||gen1==FamilyDtlsAlertMsgArr[7]){gen1='-';}
				if(occu1=='null'||occu1==FamilyDtlsAlertMsgArr[7]){occu1='-';}
				if(qua1=='null'||qua1==FamilyDtlsAlertMsgArr[7]){qua1='-';}
				if(da1=='null'){da1='-';}
				if(relation1=='null' || relation1==FamilyDtlsAlertMsgArr[7]){relation1='-';}
				if(reqFlag1=='null'){reqFlag1='';}

				/****Delete Array Start*****/				
				deleteArr[deleteArrCount]=xml1;
				deleteArrCount=deleteArrCount+1;
				/****Delete Array END*****/   					
				allowSubmit=false;	   			

				var displayFieldArray= new Array(openReqFMName,gen1,relation1,da1,dob1,empstatus1,occu1,mstatus1,qua1,remarks1);
				displayFieldArray= removeNull(displayFieldArray);
				if(reqFlag1=='D') 
				{	 																								
					addDBDataInTableAttachment('FamilyDataTable','encXML',displayFieldArray,xml1,sendAttchmentId,'', 'Reload','');
					var showDraftLinkId='rowencXML'+parseInt(counter-1);
					var linkId=1;
					trow=document.getElementById(showDraftLinkId);
					trow.cells[linkId].innerHTML   ="<font color='blue'>"+trow.cells[linkId].innerText+"</font>";
					trow.cells[linkId+1].innerHTML ="<font color='blue'>"+trow.cells[linkId+1].innerText+"</font>";
					trow.cells[linkId+2].innerHTML ="<font color='blue'>"+trow.cells[linkId+2].innerText+"</font>";
					trow.cells[linkId+3].innerHTML ="<font color='blue'>"+trow.cells[linkId+3].innerText+"</font>";
					trow.cells[linkId+4].innerHTML ="<font color='blue'>"+trow.cells[linkId+4].innerText+"</font>";
					trow.cells[linkId+5].innerHTML ="<font color='blue'>"+trow.cells[linkId+5].innerText+"</font>";
					trow.cells[linkId+6].innerHTML ="<font color='blue'>"+trow.cells[linkId+6].innerText+"</font>";
					trow.cells[linkId+7].innerHTML ="<font color='blue'>"+trow.cells[linkId+7].innerText+"</font>";
					trow.cells[linkId+8].innerHTML ="<font color='blue'>"+trow.cells[linkId+8].innerText+"</font>";
					trow.cells[linkId+9].innerHTML ="<font color='blue'>"+trow.cells[linkId+9].innerText+"</font>";
					trow.cells[linkId+10].innerHTML="<font color='blue'>"+trow.cells[linkId+10].innerText+"</font>";
					trow.cells[linkId+11].innerHTML="<font color='blue'>"+trow.cells[linkId+11].innerText+"</font>";
				}
				else
				{
					addDBDataInTableAttachment('FamilyDataTable','encXML',displayFieldArray,xml1,sendAttchmentId,'editRecord', 'deleteEmpFamilyRecord','');
					var showDraftLinkId='rowencXML'+parseInt(counter-1);
				}	   					
				if(reqFlag1=='null' || reqFlag1=='N')
				{
					draftArrCount = draftArrCount+1;
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
			var delRow = document.getElementById(openReqId);						
			delRow.parentNode.deleteRow(delRow.rowIndex);
			if(document.getElementById("FamilyDraftDtlsTab").rows.length==1)
			{
				document.getElementById("FamilyDraftDtlsTab").style.display='none';
			}					
			document.getElementById('FamilyDraftDataTable').style.display='none';	
			getFieldGroupObj(document.getElementById("FamilyDataTable"));		
		}
	}
}	

function removeNull(displayFieldArray)
{			
	for(i in  displayFieldArray)
	{								
		if(displayFieldArray[i]=='null' || displayFieldArray[i]==null)					
		{												
			displayFieldArray[i]='';
		}
	}
	return displayFieldArray;
}
/**####################### Save As Draft Request Related Script--START ##########################################*/

/**#######################-------- Validation Script Started-----------####################### */

function Family_validateSpecificFormFields(controlNames)
{				
	var returnValue = true;
	var field_value='';
	var rule_num="qwertyuiopasdfghjklzxcvbnmASDFGHJKLPOIUYTREWQZXCVBNM ";
	var annual_rule_num="0123456789";
	var last_name=document.getElementById('lastName').value;
	var first_name=document.getElementById('firstName').value;
	var middle_name=document.getElementById('middleName').value;
	var name_of_comp = document.getElementById('Name_of_Company').value;
	var annual_income = document.getElementById('annual_income').value;
	var designation = document.getElementById('designation').value;

	if(name_of_comp!="")
	{
		for(var i=0;i<name_of_comp.length;i++)
		{
			var cchar=name_of_comp.charAt(i);
			if(rule_num.indexOf(cchar)==-1)
			{
				alert(FamilyDtlsAlertMsgArr[12]);
				return 'error';
			}
		} 
	}		 
	if(annual_income!="")
	{
		for(var i=0;i<annual_income.length;i++)
		{
			var cchar=annual_income.charAt(i);
			if(annual_rule_num.indexOf(cchar)==-1)
			{
				alert(FamilyDtlsAlertMsgArr[13]);
				setFocusSelection("annual_income");
				return 'error';
			}
		} 
	}
	if(designation!="")
	{
		for(var i=0;i<designation.length;i++)
		{
			var cchar=designation.charAt(i);
			if(rule_num.indexOf(cchar)==-1)
			{
				alert(FamilyDtlsAlertMsgArr[14]);
				return 'error';
			}
		} 
	}
	var name_rule_num="qwertyuiopasdfghjklzxcvbnmASDFGHJKLPOIUYTREWQZXCVBNM";
	if(last_name!="")
	{
		for(var i=0;i<last_name.length;i++)
		{
			var cchar=last_name.charAt(i);
			if(name_rule_num.indexOf(cchar)==-1)
			{
				alert(FamilyDtlsAlertMsgArr[15]);
				return 'error';
			}
		} 
	}	
	if(first_name!="")
	{
		for(var i=0;i<first_name.length;i++)
		{
			var cchar=first_name.charAt(i);
			if(name_rule_num.indexOf(cchar)==-1)
			{
				alert(FamilyDtlsAlertMsgArr[16]);
				return 'error';
			}
		} 
	}
	if(middle_name!="")
	{
		for(var i=0;i<middle_name.length;i++)
		{
			var cchar=middle_name.charAt(i);
			if(name_rule_num.indexOf(cchar)==-1)
			{
				alert(FamilyDtlsAlertMsgArr[17]);
				return 'error';
			}
		} 
	}

	if(returnValue!='error')
	{
		var date_of_demise=document.getElementById('date_Demise').value;
		for (var i=0; i < document.frmFamily.status.length; i++)
		{
			if (document.frmFamily.status[i].checked)
			{
				var rad_val = document.frmFamily.status[i].value;		      		
				if(rad_val=='fm_Dead' && date_of_demise=='')
				{
					returnValue='error';
					alert(FamilyDtlsAlertMsgArr[18]);
				}
				break;
			}
		}
	}			
	if(returnValue != 'error')
	{	
		RelationCombo=document.frmFamily.Employment;
		dependentCombo=document.frmFamily.depedentCmb;	
		var id= RelationCombo.value;		
		var dependent  = dependentCombo.value;
		if((id == 'fm_Employed' || id=='fm_self_employeed') && dependent== 'Fm_dep_Yes')
		{
			alert(FamilyDtlsAlertMsgArr[19]);
			document.getElementById('employedData').style.display='';									
			returnValue ='error';
		}			
	}
	if(returnValue != 'error')
	{	
		RelationCombo=document.frmFamily.Employment;
		dependentCombo=document.frmFamily.depedentCmb;	
		var id= RelationCombo.value;		
		var dependent  = dependentCombo.value;
		if(document.frmFamily.status=='fm_Dead' && dependent== 'Fm_dep_Yes')
		{
			alert(FamilyDtlsAlertMsgArr[20]);								
			returnValue ='error';
		}
	}
	return returnValue;
}		

/*Checking whether the family member is dependent or nominee */
function checkForMember(statusRadio)		
{	
	var id= statusRadio.value;									
	if(id=='fm_Dead')
	{
		document.frmFamily.status.value='fm_Dead';
		if(!workFlowEnabled)				
		{
			document.getElementById('depedentCmb').options[1].selected= true ;
		}
		var x,depenDentFlag=0; 
		for (x in dependentNomineeArr)
		{
			var chkDependentObj =dependentNomineeArr[x].toString();
			if(chkDependentObj==depedenetMember)
			{
				depenDentFlag=1;
			}
		}								
		if(depenDentFlag==1)
		{				
			alert(FamilyDtlsAlertMsgArr[21]);								
			for(var i=0; i<document.frmFamily.status.length;i++) 
			{
				if(document.frmFamily.status[i].value == 'fm_Alive') 
				{													
					document.frmFamily.status[i].status=true;
					break;
				}
			}
			document.getElementById("member_Date_of_Demise").style.display='none';
		}
		else
		{
			document.frmFamily.personAge.value='';
			document.getElementById("member_Date_of_Demise").style.display='';					
		}								
	}
	else 
	{
		document.getElementById("member_Date_of_Demise").style.display='none';
		document.getElementById("date_Demise").value='';
		var birthDate=document.frmFamily.DOB.value
		countAge(birthDate,"personAge","value");
		return;
	}
}	
	
function validDateDemis()
{								
	var demiseDate = document.getElementById("date_Demise").value;
	var dobDate = document.frmFamily.DOB.value;
	var myAlertFlag=false;
	if(dobDate=='')
	{
		myAlertFlag=true;
		alert(FamilyDtlsAlertMsgArr[22]);										
		document.getElementById("date_Demise").value='';
		document.frmFamily.personAge.value='';
	}
	if(demiseDate=='' && myAlertFlag==false)
	{
		alert(FamilyDtlsAlertMsgArr[23]);
		myAlertFlag=true;
		document.getElementById("date_Demise").value='';
	}
	else 
	{
		var alertFlag=datedifference();
		if(alertFlag==false)					
		{
			if(myAlertFlag==false)
			{
				alert(FamilyDtlsAlertMsgArr[24]);
				document.getElementById("date_Demise").value='';
				document.frmFamily.personAge.value='';
			}
		}				
		else
		{		
			countAge(dobDate,"personAge","value",demiseDate,'','','','status');
		}					
	}				
}

function datedifference() 
{ 
	var strDate1 = document.frmFamily.DOB.value;
	var strDate2 = document.getElementById("date_Demise").value ;			
	var bldatediff = false ;

	//Start date split to UK date format and add 31 days for maximum datediff 
	strDate1 = strDate1.split("/"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	starttime = new Date(starttime.valueOf()); 

	//End date split to UK date format 
	strDate2 = strDate2.split("/"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	endtime = new Date(endtime.valueOf()); 
	if(endtime >= starttime) 
	{ 
		bldatediff = true 
	} 

	return bldatediff 
} 
		
/*End of Checking the family mber is depedent or not*/

/**#######################-------- Validation Script Ended -----------#######################*/	

/*########################-------- Message Showing on Submit Stared ---------#######################*/
function messageDisplay()
{
	var str='',fir=true,countRows=0;
	var num = document.getElementById('FamilyDataTable').rows.length;
	for(var i=1;i<num;i++)
	{							
		trow=document.getElementById('FamilyDataTable').rows[i];
		rowId=trow.id;
		try 
		{				
			var hField = rowId.substring(3, rowId.length);
			var xmlFileName = document.getElementById(hField).value;				
			if(xmlFileName.indexOf("$/$")!=-1)
			{
				rowIdArr=xmlFileName.split("$/$");												
				if(fir==false)
				{
					str=str+"\n\n";												
				}
				else{fir=false;}
				countRows=countRows+3;
				str=str+'Row No '+i+' : ';													

				validationArray = rowIdArr[1].split(",");
				for (itr=0;itr<validationArray.length;itr++)
				{
					if (validationArray[itr] != '' && validationArray[itr]!='_N')
					{
						if (validationArray[itr] == 'eis.LAST_NAME')str = str + FamilyDtlsAlertMsgArr[25];
						else if (validationArray[itr] == 'eis.FIRST_NAME')str = str + FamilyDtlsAlertMsgArr[26];
						else if (validationArray[itr] == 'eis.Relation')str = str + FamilyDtlsAlertMsgArr[27];
						else if (validationArray[itr] == 'eis.GENDER')str = str + FamilyDtlsAlertMsgArr[28];
						else if (validationArray[itr] == 'eis.DATE_OF_BIRTH')str = str + FamilyDtlsAlertMsgArr[29];
						else if (validationArray[itr] == 'eis.status')str = str +FamilyDtlsAlertMsgArr[30];
						else if (validationArray[itr] == 'eis.date_Demise')str = str + FamilyDtlsAlertMsgArr[31];
						else if (validationArray[itr] == 'eis.Marital_Status')str = str +FamilyDtlsAlertMsgArr[32];
						else if (validationArray[itr] == 'eis.fm_dependant_dtls')str = str + FamilyDtlsAlertMsgArr[33];
						else if (validationArray[itr] == 'eis.Employment_Status')str = str + FamilyDtlsAlertMsgArr[34];
						else if (validationArray[itr] == 'eis.Nationality')str = str +FamilyDtlsAlertMsgArr[35];
						else if (validationArray[itr] == 'eis.occupation')str = str + FamilyDtlsAlertMsgArr[36];
						else if (validationArray[itr] == 'eis.Dept')str = str + FamilyDtlsAlertMsgArr[37];
						else if (validationArray[itr] == 'eis.FamilyMemAdd')str = str + FamilyDtlsAlertMsgArr[38];

						if (validationArray.length-2 != itr)
							str = str + " ,";
					}
				}
				
				if (validationArray.length > 0)
				{
					if(validationArray.length>2)
					{
						str=str.substring(0,str.length-1);
					}
					str = str +" " + FamilyDtlsAlertMsgArr[39];
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
/*########################-------- Message Showing on Submit Ends ---------#######################*/

function openAppWindow(actionFlag)
{
	var userId = document.getElementById("userId").value;
	var href = "hrms.htm?actionFlag="+  actionFlag + "&userId="+ userId ;
	objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");			
}

//Start IFMS
function displayOtherOccu()
{
	var occuCombo = document.getElementById("Occupation").value ;
	var employmentStatusCombo = document.getElementById("Employment").value ;
	var deptCombo = document.getElementById("Dept").value ;
	if(employmentStatusCombo=='fm_self_employeed')
	{
		if(occuCombo=='SelfOther')										
		{
			document.getElementById('occuDtlsTd').style.display='';
			document.getElementById('occuDtls').style.display='';
		}
		else
		{
			document.getElementById('occuDtlsTd').style.display='none';
			document.getElementById('occuDtls').style.display='none';
			document.getElementById('other_occu').value='';
		}	
		document.getElementById('DeptTd').style.display='none';
		document.getElementById('DeptTdList').style.display='none';
		document.getElementById('Dept').value='Select';
	}

	if(employmentStatusCombo=='fm_Employed')
	{
		if(occuCombo=='Government_org')									
		{
			document.getElementById('DeptTd').style.display='';
			document.getElementById('DeptTdList').style.display='';
			if(deptCombo=='other' || deptCombo=='Other')
			{
				document.getElementById('companyDtlsTd').style.display='';
				document.getElementById('companyDtls').style.display='';

				document.getElementById('occuDtlsTd').style.display='';
				document.getElementById('occuDtls').style.display='';
			}
			else
			{
				document.getElementById('companyDtlsTd').style.display='none';
				document.getElementById('companyDtls').style.display='none';
				document.getElementById('Name_of_Company').value='';

				document.getElementById('occuDtlsTd').style.display='none';
				document.getElementById('occuDtls').style.display='none';
				document.getElementById('other_occu').value='';
			}
		}
		else
		{
			document.getElementById('DeptTd').style.display='none';
			document.getElementById('DeptTdList').style.display='none';
			document.getElementById('Dept').value='Select';

			document.getElementById('companyDtlsTd').style.display='';
			document.getElementById('companyDtls').style.display='';

			document.getElementById('occuDtlsTd').style.display='';
			document.getElementById('occuDtls').style.display='';
		}
	}
}

function showForOtherDept()
{
	var occuCombo = document.getElementById("Occupation").value ;
	var employmentStatusCombo = document.getElementById("Employment").value ;
	var DeptCombo = document.getElementById("Dept").value ;

	if(employmentStatusCombo=='fm_Employed' && DeptCombo=='other')
	{		
		document.getElementById('companyDtlsTd').style.display='';
		document.getElementById('companyDtls').style.display='';

		document.getElementById('occuDtlsTd').style.display='';
		document.getElementById('occuDtls').style.display='';
	}
	else
	{
		document.getElementById('companyDtlsTd').style.display='none';
		document.getElementById('companyDtls').style.display='none';
		document.getElementById('Name_of_Company').value='';

		document.getElementById('occuDtlsTd').style.display='none';
		document.getElementById('occuDtls').style.display='none';
		document.getElementById('other_occu').value='';
	}
}

function getEmploymentComboList(cmb)
{
	var id=cmb.value;	
	var z=document.getElementById('Occupation');
	for (var i=z.length;i>=0;i--)
	{	     	   								
		z.remove(z.value);
		z.remove(i);										   				
	}   			 			
	var y=document.createElement('option');
	y.text='--'+FamilyDtlsAlertMsgArr[7]+'--';
	y.value='Select';
	try
	{
		z.add(y,null);
	}
	catch(ex)
	{	   			 
		z.add(y);	   			 				 
	}
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
				alert(FamilyDtlsAlertMsgArr[6]);
				return false;        
			}
		}			 
	}        
	var url = "hrms.htm?actionFlag=FamilyDetails&flag=selEmpType&cmbid="+id;    
	xmlHttp.open("POST", encodeURI(url) , true);			
	xmlHttp.onreadystatechange = processResponse;
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));
}			

function processResponse()
{						
	if (xmlHttp.readyState == 4) 
	{   
		endProcess();  
		if (xmlHttp.status == 200) 
		{          				
			var textId;												
			var z=document.getElementById('Occupation');			            		            			            	
			var xmlStr = xmlHttp.responseText;
			var XMLDoc=getDOMFromXML(xmlStr);   				    			    	
			var SubStr = XMLDoc.getElementsByTagName('Occupation');				    	
			var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('ID'); 				    		     		     							
			for ( var i = 0 ; i < SubStr.length ; i++ )
			{	     		     								
				value=SubStr[i].childNodes[0].text;	     				    
				textId=SubCoCurrStr_ID[i].childNodes[0].text;
				var y=document.createElement('option');
				y.text=value;
				y.value=textId;

				try
				{
					z.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
					z.add(y);	   			 				 
				}     						     					
			}
			if (workFlowEnabled) // IFMS
			{
				if(setOccuCombo!=-1)
				{
					if(setOccuCombo.search(/Select/i)==-1)
					{		           				
						document.getElementById('Occupation').value = setOccuCombo;
						setOccuCombo=-1;
					}
				}	
			}
			else if(setOccuCombo!=-1)
			{
				document.getElementById('Occupation').value = setOccuCombo;
				setOccuCombo=-1;
			}
		}
		else 
		{  			
			alert(FamilyDtlsAlertMsgArr[40]);					
		}
		displayOtherOccu();		
	}
}	

function createOtherOptionForDept() 	
{
	var otherLookupName='other';
	var otherLookupDesc=FamilyDtlsAlertMsgArr[41];

	var z=document.frmFamily.Dept;

	var optionLength  = document.getElementById('Dept').length;
	var flagStatus=true;
	for(var i=0; i<optionLength; i++)
	{
		if(otherLookupName==z[i].value)
		{
			flagStatus=false;
			break;
		}
	}

	if(flagStatus)
	{
		var y=document.createElement('option');

		y.text=otherLookupDesc;
		y.value=otherLookupName;
		z.add(y,1);
	}
}

function hideInfoForDeadMember(cmb)
{
	var id=cmb.value;
	document.getElementById('Qualification').value='Select';
	document.getElementById('depedentCmb').value='Select';
	document.getElementById('Employment').value='Select';
	document.getElementById('Nationality').value='Select';
	employmentStatus(document.frmFamily.Employment);
	if(id=='fm_Dead')
	{
		document.getElementById('quliTable').style.display='none';
		document.getElementById('quliTable2').style.display='none';
		document.getElementById('employmentTable').style.display='none';
		document.getElementById('remarkTable').style.display='none';
		document.getElementById('addressTable').style.display='none';
	}
	else
	{
		document.getElementById('quliTable').style.display='';
		document.getElementById('quliTable2').style.display='';
		document.getElementById('employmentTable').style.display='';
		document.getElementById('remarkTable').style.display='';
		document.getElementById('addressTable').style.display='';
	}
}
function combineFMName()
{
	var FName=document.getElementById('firstName').value;
	var Mname=document.getElementById('middleName').value;
	var Lname=document.getElementById('lastName').value;
	var fullName='';
	if(FName=='' || FName==null){FName='-';}
	if(Mname=='' || Mname==null){Mname='';}
	if(Lname=='' || Lname==null){Lname='-';}

	if(FName=='-' && Mname=='' && Lname=='-')
	{
		fullName='-';
	}
	else
	{
		fullName=FName+" "+Mname+" "+Lname;
	}
	document.getElementById('empFmFullName').value=fullName;	
	getFieldGroupObj(document.getElementById("FamilyDataTable"));
}

function onLoadFamilyDtls()
{
	if (document.getElementById('FamilyDataTable') != null)
	{
		var rows = document.getElementById('FamilyDataTable').rows.length;
		if (rows <= 1 && document.getElementById('familyCurrentNoRecords')!=null)
			document.getElementById('familyCurrentNoRecords').style.display='';
	}
	makeReadOnly();
	createOtherOptionForDept();
}

function checkRecForMotherAndFather()
{
	var checkRecFlag=true; 
	var currentRec='';
	var curRel=document.getElementById('Relation').value;
	if(curRel=='fm_rel_Father' || curRel=='fm_rel_Mother')
	{
		var num = document.getElementById('FamilyDataTable').rows.length;
		var selectedRec=document.getElementById('Relation');
		var relation=selectedRec.options[selectedRec.selectedIndex].text;
		for(var i=1;i<num;i++)
		{		
			trow=document.getElementById('FamilyDataTable').rows[i];
			currentRec=trow.cells[3].innerText;
			if(currentRec!='0')
			{
				rowId=trow.id;
				if (updateRow!=null && rowId == updateRow)
				continue;
			
				if(currentRec==relation) 
				{
					checkRecFlag=false;
				}
			}
		}
	}
	return	checkRecFlag;
}

function checkRecForMotherAndFatherResponse()
{
	var strCurRel=document.getElementById('Relation').value
	if(strCurRel=='fm_rel_Father')
		alert(FamilyDtlsAlertMsgArr[42]);
	else if(strCurRel=='fm_rel_Mother')
		alert(FamilyDtlsAlertMsgArr[43]);
	document.getElementById('Relation').focus();
}

function duplicateRecResponse()// Change For Father Records
{
	if (hrmsXmlHttp.readyState == 4) 
	{ 
		if (hrmsXmlHttp.status == 200) 
		{  							
			var xmlStr = hrmsXmlHttp.responseText;	
			var XMLDoc=getDOMFromXML(xmlStr);

			if (XMLDoc != null)
			{
				var SubStrFatherRec = XMLDoc.getElementsByTagName('FatherRec');				    	
				var SubStrMotherRec = XMLDoc.getElementsByTagName('MotherRec'); 
				var tempDuplicationMesg="";
				if(XMLDoc.getElementsByTagName('FatherRec'))
				{
					if(SubStrFatherRec[0].childNodes[0].text=='eis.DuplicateFatherRec')
					{
						tempDuplicationMesg=tempDuplicationMesg+FamilyDtlsAlertMsgArr[42]+",";
					}
				}
				if(XMLDoc.getElementsByTagName('MotherRec')!=null)
				{
					if(SubStrMotherRec[0].childNodes[0].text=='eis.DuplicateMotherRec')
					{
						tempDuplicationMesg=tempDuplicationMesg+FamilyDtlsAlertMsgArr[43]+",";
					}
				}
				if(tempDuplicationMesg!='' && tempDuplicationMesg!=null)
				{
					tempDuplicationMesg=tempDuplicationMesg.substring(0,tempDuplicationMesg.length-1);
					document.getElementById('errorMessage').style.display='';				
					document.getElementById('errorBox').value=tempDuplicationMesg;
				}
			}
			else
			{
				startProcess();	
				document.frmFamily.action = "hrms.htm?actionFlag=FamilyDetails&workFlowEnabled="+ workFlowEnabled +"&flag=SubmitFamilyDtls&draft=0&DeleteArr="+deleteArr;  //IFMS
				document.frmFamily.submit();
			}
		}
	}
}

/** SCRIPT FOR SHOW FAMILY APPROVE DETAILS ==========>> Started*/
function callMe(action)
{
	if(action=='A' || action=='R')
	{
		disabledFlag=true;
	}
}	

function disabledMe()
{
	if(!disabledFlag)
	{
		for (var i = 0; i < document.showFamilyDtls.elements.length; i++)
		{						
			if(document.showFamilyDtls.elements[i].type=="checkbox" )
			{					
				document.showFamilyDtls.elements[i].disabled=false;
			}
		}
	}
}	

function setFlags(flagStr)
{		
	if(flagStr=='Occu')
	{
		flgaOccu=false;
	}
	if(flagStr=='Qualification')
	{
		flagQualification=false;
	}
	if(flagStr=='EmploymentStatus')
	{
		flagEmploymentStatus=false
	}
	if(flagStr=='Country')
	{
		flagCountry=false
	}
}

function resetMyAllFlag()
{
	flgaMidName=true;
	flgaOccu=true;
	flagQualification=true;
	flagEmploymentStatus=true;
	flagCountry=true;
}

function setMidName(str)
{
	if(str=='')
	{
		return '-';
	}
	return str;
}	

function submitHiddenData() 
{	
	var j=0;
	for (var i = 0; i < document.showFamilyDtls.elements.length; i++)
	{						
		if(document.showFamilyDtls.elements[i].type=="checkbox" )
		{					
			var user_input = document.showFamilyDtls.elements[i].value;
			if(document.showFamilyDtls.elements[i].checked==true)
			{
				arr[j]=user_input;
				j=parseInt(j)+1;
			}
		}
	}
	document.getElementById("approveDtls").value=arr;
	var uri = "hrms.htm?actionFlag=";
	var url = uri + "FwdFamilyDtlsForApproval&approveDtls="+arr;   
	document.showFamilyDtls.action = url;
	document.showFamilyDtls.submit();
}
function onLoadApproveFamilyDtls()
{
	disabledMe();
	document.getElementById('target_uploadattachmentBiometric').style.display='none';
	document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='none';
	document.getElementById('attachmentTable').style.display='none';
}
function showFamilyAttachmentFieldGroup()
{
	getFieldGroupObj(document.getElementById("attachmentTable"));
}		
/** SCRIPT FOR SHOW FAMILY APPROVE DETAILS ==========>> Ended */