function setBlankInRoleId()
{
	document.getElementById("roleId").value="";
	document.getElementById('tabModule').style.display='none';
	document.getElementById('moduleSearchTbl').style.display='none';
}

function setBlankInModuleId()
{
	document.getElementById("moduleElementId").value="";
}
function getModuleDetails()
{
	CheckSelectedValues('MODULE');
}
function setBlankInScreenId()
{
	document.getElementById("screenElementId").value="";
}
function setBlankInFieldId()
{
	document.getElementById("fieldElementId").value="";
}

function sendAjexRequestForSelectedModule()//This funtion is Calling form showUserDtl.jsp
{
	addElementMapping('MODULE');
}

function responseForSelectedUserDtls()
{
	if (xmlHttp.readyState == 4) 
	{     
		if (xmlHttp.status == 200) 
		{
			var xmlStr = xmlHttp.responseText;	
			var XMLDoc=getDOMFromXML(xmlStr);
			var displayFieldArray = new Array();
			var mappedRoleList = XMLDoc.getElementsByTagName('roleMapped');
			var tableRows = document.getElementById("roleDtls").rows.length;
			if(tableRows > 1)
			{
				for (var iCnt=tableRows-1;iCnt>=1;iCnt--)
				{
					document.getElementById("roleDtls").deleteRow(iCnt);
				}
			}
			if(mappedRoleList!=null)
			{
				document.getElementById("roleDtls").style.display='';
				//document.getElementById("savebtnTableId").style.display='';
				//document.getElementById("getRoles").disabled=false;

			    for ( var i = 0 ; i < mappedRoleList.length ; i++ )
			    {
					displayFieldArray[0]=XMLDoc.getElementsByTagName('roleId')[i].text; 
					displayFieldArray[1]=XMLDoc.getElementsByTagName('roleName')[i].text; 
					displayFieldArray[2]=XMLDoc.getElementsByTagName('startDate')[i].text; 
					displayFieldArray[3]=XMLDoc.getElementsByTagName('endDate')[i].text; 
					displayFieldArray[4]=XMLDoc.getElementsByTagName('activeFlag')[i].text;
					addDataInTable("roleDtls","encXMLRole",displayFieldArray);
				}
			}
		}
	}	
}			
		function addElementMapping(screenName)
		{
			
			if(screenName == 'MODULE')
			{
		   		if(document.form1.txtModuleName.value == ''){
		    		
		    		alert(aclElementMpgAlerts[2]);
		    		return false;
		    	}
		   		document.form1.Module_Save.disabled = false;
				var displayArray = new Array();
				displayArray[0]=document.getElementById('moduleElementId').value;
				displayArray[1]=document.getElementById('txtModuleName').value;
				displayArray[2]=1;
				displayArray[3]=document.getElementById('roleId').value;
				displayArray[4]=screenName;
				displayArray[5]=1;
				document.getElementById('txtModuleName').value="";
				document.getElementById('moduleElementId').value="";
				addDataInTableElement("ModuleTable",displayArray,'deleteDBRecordForElement',
	 					'editDBRecord','N',false);
		 	}
			else if(screenName == 'SCREEN')
			{
	   			var userScreenSel=document.getElementById("ScreenCombo");
	   			for(var i=0;i<userScreenSel.length;i++)
	   			{
	   				if(userScreenSel.options[i].selected)
	   				{
	   					var displayArray = new Array();
	   					displayArray[0]=userScreenSel.options[i].value;
	   					displayArray[1]=userScreenSel.options[i].text;
	   					displayArray[2]=document.getElementById('parent_perm').value;;
	   					displayArray[3]=document.getElementById('roleId').value;
	   					displayArray[4]=screenName;
	   					displayArray[5]=document.getElementById('parent_perm').value;;
	   					//document.getElementById('txtModuleName').value="";
	   					//document.getElementById('moduleElementId').value="";
	   					addDataInTableElement("ScreenTable",displayArray,'deleteDBRecordForElement',
	   		 					'editDBRecord','N',false);
	   				}
	   			}
		   		document.form1.Screen_Save.disabled = false;
				
				removeFunction(screenName);
		 	}
			else if(screenName == 'FIELD')
			{
	   			var userScreenSel=document.getElementById("FieldCombo");
	   			for(var i=0;i<userScreenSel.length;i++)
	   			{
	   				if(userScreenSel.options[i].selected)
	   				{
	   					var displayArray = new Array();
	   					displayArray[0]=userScreenSel.options[i].value;
	   					displayArray[1]=userScreenSel.options[i].text;
	   					displayArray[2]=document.getElementById('parent_permForField').value; //Modified By Krunal
	   					displayArray[3]=document.getElementById('roleId').value;
	   					displayArray[4]=screenName;
	   					displayArray[5]=document.getElementById('parent_permForField').value; //Modified By Krunal
	   					//document.getElementById('txtModuleName').value="";
	   					//document.getElementById('moduleElementId').value="";
	   					addDataInTableElement("FieldTable",displayArray,'deleteDBRecordForElement',
	   		 					'editDBRecord','N',false);
	   				}
	   			}
		   		document.form1.Field_Save.disabled = false;
				
				removeFunction(screenName);
		 	}
			
		}
		function removeFunction(screenName)
		{
		
			
			if(screenName == 'SCREEN')	
			{
				var UserEntries=document.getElementById("ScreenCombo");
			}
			else if(screenName=='FIELD')	
			{	
				var UserEntries=document.getElementById("FieldCombo");
			}	
			for(var i=0;i<UserEntries.length;i++)
			{
				if(UserEntries.options[i].selected)
				{
					UserEntries.remove(i);
					i = i - 1;
				}
			}
		}
		
		
		function saveElements(screenName,tableId) 
		{
			 
			showProgressbar();
		 	document.getElementById('ActionType').value=tableId;
		 	document.form1.action="hdiits.htm?actionFlag=insertRoleElementMapping";
		 	document.form1.submit();
			
		}
		
		/*function saveElements(screenName,tableId) 
		{			
			
			document.form1.ActionType.value=tableId;
			var temp1=document.form1.roleId.value;
			var temp2=document.form1.roleName.value;
			var temp3=document.form1.parentCode.value;
			var temp4=document.getElementById('parent_perm').value;
			 
			
			insertElements();
			 
			alert(aclElementMpgAlerts[1]);
			
			document.form1.roleId.value=temp1;
			document.form1.roleName.value=temp2;
			document.form1.parentCode.value=temp3;
			document.getElementById('parent_perm').value=temp4;
			//alert(screenName);
			
			ajaxfunction(screenName);
			 
			if(screenName=='MODULE')
			{
				document.form1.Module_Save.disabled = true;
			}
			else if(screenName=='SCREEN')
			{
				document.form1.Screen_Save.disabled = true;
			}
			else if(screenName=='FIELD')
			{
				document.form1.Field_Save.disabled = true;
			}
		}*/
			
		function deleteTableValues(tableName)
		{
			var tableRows = document.getElementById(tableName).rows.length;
			if(tableRows > 1)
			{
				for (var iCnt=tableRows-1;iCnt>=1;iCnt--)
				{
					document.getElementById(tableName).deleteRow(iCnt);
				}
			}
		}

		