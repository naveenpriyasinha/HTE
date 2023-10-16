function addOrUpdateElement(methodName, actionFlag, fieldArray, progressBarFlag) 
{	
	 
	/*xmlHttp=GetXmlHttpObject();
	 
	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	}*/ 
	if(progressBarFlag != false)
	{
		showProgressbar("Please Wait...");	
	}
	 	
	var reqBody = getRequestBody(fieldArray);	
	 
	var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
	 
	/*methodName = methodName + "()";
	 
	xmlHttp.onreadystatechange=function() {
		if(xmlHttp.readyState == 4) {
			eval(methodName);
			if(progressBarFlag != false)
			{
				hideProgressbar();
			}
		}	
	}
	xmlHttp.open("POST",encodeURI(url),false);
	xmlHttp.send(null);*/
	
	new Ajax.Request(encodeURI(url),
	{
		method: 'POST',
		onSuccess: function(resXmlHttp)
		{
			window[methodName](resXmlHttp);
			if(progressBarFlag != false)
			{
				hideProgressbar();
			}
		},
	    onFailure: alertOnFailureForMstScr,
	    asynchronous: false
	} );
}

 function sendAjexRequestForSelectedUserDtls()
 {  
	 addOrUpdateElement("showUserDetails", "getUserDetailsForUserElement", new Array('userId'), true);
	 document.getElementById("userTableId").style.display='';
 }
 
 

 function  saveElements(screenName,tableId)
 {
 	showProgressbar();
 	document.getElementById('ActionType').value=tableId;
 	document.form1.action="hdiits.htm?actionFlag=insertUserELementMapping";
 	document.form1.submit();
 }
 
 function showUserDetails(resXmlHttp)
 { 
	var xmlStr = resXmlHttp.responseText;
	var XMLDoc=resXmlHttp.responseXML.documentElement;
			 
	document.getElementById("empName").innerHTML = XMLDoc.getElementsByTagName('empName')[0].firstChild.nodeValue; // Employee Full Name
	document.getElementById("userNm").innerHTML = XMLDoc.getElementsByTagName('userName')[0].firstChild.nodeValue; // User Name
	document.getElementById("location").innerHTML = XMLDoc.getElementsByTagName('locationName')[0].firstChild.nodeValue; // Office Name
	document.getElementById("designation").innerHTML = XMLDoc.getElementsByTagName('designationName')[0].firstChild.nodeValue; // Designation
	document.getElementById("branch").innerHTML = XMLDoc.getElementsByTagName('branchName')[0].firstChild.nodeValue; // Branch
	document.getElementById("postName").innerHTML= XMLDoc.getElementsByTagName('postName')[0].firstChild.nodeValue; 
	CheckSelectedValues('MODULE');
 }
 function setBlankValue()
 {
 	document.getElementById("userId").value="";
 	
 	document.getElementById("tabModule").style.display='none';
 }
 function setBlankInModuleId()
 {
 	document.getElementById("moduleId").value="";
 }
 function sendAjexRequestForSelectedModule()
 {
	 addElementMapping('MODULE');  
 }
 function CheckSelectedValues(ScreenName,eleCode,obj3)
	{
		//document.form1.roleName_Hidden.value=document.form1.roleName.value;
		if(ScreenName == 'MODULE')
		{
			if(document.form1.userId.value == '')
			{
				alert(aclElementMpgAlerts[3]);
				return false;
			}
			document.getElementById('tabModule').style.display='';
			document.getElementById('txtModuleName').focus();
			document.form1.Module_Save.disabled = true;
			
		}
		else if(ScreenName == 'SCREEN')
		{
			document.getElementById('searchSearchTbl').style.display='';
			if(document.form1.userId.value == '')
			{
				alert(aclElementMpgAlerts[3]);
				return false;
			}
		}
		else if(ScreenName == 'FIELD')
		{
			document.getElementById('FieldTable').style.display='';
			if(document.form1.userId.value == '')
			{
				alert(aclElementMpgAlerts[3]);
				return false;
			}
		}
		
		ajaxfunction(ScreenName,eleCode,obj3);
		
	}
 function addElementMapping(screenName)
	{
		 
		if(screenName == 'MODULE') 
		{
	   		if(document.form1.moduleId.value == ''){
	    		
	    		alert(aclElementMpgAlerts[2]);
	    		return false;
	    	}
	   		document.form1.Field_Save.disabled = false;
			var displayArray = new Array();
			displayArray[0]=document.getElementById('moduleId').value;
			displayArray[1]=document.getElementById('txtModuleName').value;
			displayArray[2]=1;
			displayArray[3]=document.getElementById('userId').value;
			displayArray[4]=screenName;
			displayArray[5]=1;
			
			document.getElementById('txtModuleName').value="";
			document.getElementById('moduleElementId').value="";
			addDataInTableElement("ModuleTable",displayArray,'deleteDBRecordForElement',
					'editDBRecord','N',false);
			 
			document.form1.Module_Save.disabled =false;
			 
	 	}else if(screenName == 'SCREEN')
		{
   			var userScreenSel=document.getElementById("ScreenCombo");
   			for(var i=0;i<userScreenSel.length;i++)
   			{
   				if(userScreenSel.options[i].selected)
   				{
   					var displayArray = new Array();
   					displayArray[0]=userScreenSel.options[i].value;
   					displayArray[1]=userScreenSel.options[i].text;
   					displayArray[2]=document.getElementById('parent_perm').value;
   					displayArray[3]=document.getElementById('userId').value;
   					displayArray[4]=screenName;
   					displayArray[5]=document.getElementById('parent_perm').value;
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
   	   				displayArray[3]=document.getElementById('userId').value;
   	   				displayArray[4]=screenName;
   	   				displayArray[5]=document.getElementById('parent_permForField').value;//Modified By Krunal
   	   				addDataInTableElement("FieldTable",displayArray,'deleteDBRecordForElement',
   	   	 					'editDBRecord','N',false);
   	   			 removeFunction(screenName);
   	   			}
   	   		}
   	   	document.form1.Field_Save.disabled = false;
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
 /*function saveElements(screenName,tableId) 
 {			
	 	document.getElementById('ActionType').value=tableId;
		var temp1=document.form1.userId.value;
		var temp2=document.form1.txtUserName.value;
		var temp3=document.form1.parentCode.value;
		var temp4=document.getElementById('parent_perm').value;
		insertElements();
		alert(aclElementMpgAlerts[1]);
	 
	 	document.form1.userId.value=temp1;
		document.form1.txtUserName.value=temp2;
		document.form1.parentCode.value=temp3;
		document.getElementById('parent_perm').value=temp4;
		
	 
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
 
 function insertElementEntry(tableentries, tableName, screenName, nextScreen,parentPerm)  
 { 	 
	 deleteTableValues(tableName); 

	 for ( var i = 0 ; i < tableentries.length ; i++ )
	 {
	
	 	elementStatus=tableentries[i].childNodes[4].text;
	 	elecode=tableentries[i].childNodes[0].text;   
	 	elename=tableentries[i].childNodes[1].text;
	  	permission=tableentries[i].childNodes[3].text;
	  	elementParentId = tableentries[i].childNodes[5].text;
	 	spaceLevel = tableentries[i].childNodes[6].text;
	  	
	 	var displayFieldArray=new Array();
	 	displayFieldArray[0]=elecode;
	 	displayFieldArray[1]=elename;
	 	displayFieldArray[2]=permission;
	 	displayFieldArray[3]=document.getElementById('userId').value;
	 	displayFieldArray[4]=nextScreen;
	 	displayFieldArray[5] = parentPerm;
	 	displayFieldArray[6] = elementParentId;
	 	displayFieldArray[7] = spaceLevel;
	 	addDataInTableElement(tableName,displayFieldArray,'deleteDBRecordForElement','editDBRecord','M',true);
	 }

 }
 
 var empArray = new Array();
 function empSearch(from)
 {
 	if (from.length == 0)
     {
     	alert('Select atleast one record');
     	return false;
     }
     else if (from.length > 1)
     {
 	    alert('Only one record can be selected');
     	return false;
     }		       
     
     document.getElementById("userId").value = "";
 	
 	for(var i=0; i<from.length; i++)
 	{	
 		empArray[i] = from[i].split("~"); 	
 	}
 	
 	var empRecord = empArray[0]; 
 	
 	document.form1.txtEmployeeName.value = empRecord[0]; // Employee Id
 	document.form1.name_txtEmployeeName.value = empRecord[1]; // Employee Full Name
 	document.form1.userId.value = empRecord[2]; // user id
 	
 	sendAjexRequestForSelectedUserDtls();
 }

 function checkForEmpSearch(searchComboValue)
 {
 	var id = searchComboValue.value;	
 	if(id == 'empName')
 	{
 		document.getElementById("txtSearchTypeTdID").style.display='none';
 		document.getElementById("txtEmployeeNameTdID").style.display='';
 	}
 	else
 	{
 		document.getElementById("txtEmployeeNameTdID").style.display='none';
 		document.getElementById("txtSearchTypeTdID").style.display='';
 	}
 }
 
 function removeComboValueOnEmpReq(empSearchReqFlag)
 {
 	if(empSearchReqFlag!="Y")
 	{
 		var searchTypeCombo =document.getElementById('searchType_select');
 		if(searchTypeCombo!=null)
 		{
 			searchTypeCombo.remove(0);
 		}
 		document.getElementById("txtEmployeeNameTdID").style.display='none';
 		document.getElementById("txtSearchTypeTdID").style.display='';
 	}
 }