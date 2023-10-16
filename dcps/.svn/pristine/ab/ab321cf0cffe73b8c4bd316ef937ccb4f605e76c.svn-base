var counter = 1;
var updateRow = null;;
//var flagForUpdatedVO = false;

	function addDataInTableAttachment(tableId, hiddenField, displayFieldArray, 
			editMethodName, deleteMethodName, viewMethodName)
	{

		if(deleteMethodName == undefined) {
			deleteMethodName = '';
		}
		if(editMethodName == undefined) {
			editMethodName = '';
		}
		if(viewMethodName == undefined) {
			viewMethodName = '';
		}
		
		document.getElementById(tableId).style.display='';
		var trow=document.getElementById(tableId).insertRow();
		//trow.id = 'row' + counter;
		trow.id = 'row' + hiddenField + counter;
		
		//trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + counter + "' value='" + xmlHttp.responseText + "'/>";				
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" + xmlHttp.responseText + "'/>";			
						
		trow.cells[0].style.display = 'none';
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++) {
			var field = document.getElementById(displayFieldArray[i]);
			//alert(field.type);
			if(field.type == 'select-one') 
			{
				/* Code added by Tarun Trehan to check "Select" value in drop down
				for multiple add case. */
				if(field.options[field.selectedIndex].value == '-1')
				{
					trow.insertCell(i+1).innerHTML = "";
				}
				else
				{
					trow.insertCell(i+1).innerHTML = field.options[field.selectedIndex].text;						
				}
			}		
			else if(field.type == 'radio')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				for(var j = 0; j < radio.length; j++)
				{
					if(radio[j].checked)
					{
						trow.insertCell(i+1).innerHTML = radio[j].value;
					}
				}
			}		
			else {
				trow.insertCell(i+1).innerHTML = field.value;	
			}
		}	
		
		if(document.getElementById("Incremented_rowNumber")==null)
		{
			var table = document.getElementById(tableId);
			table.rows[0].insertCell(len+1).innerHTML = "<INPUT type='hidden' name='Incremented_rowNumber' id='Incremented_rowNumber' value='1'/>";
			table.rows[0].cells[len+1].style.display = 'none';
		}

		var rowNumberForRow = document.getElementById("Incremented_rowNumber").value;

//		alert('rowNumberForRow: ' + rowNumberForRow);
		var editCap = "";
		var delCap  = "";
		var viewCap = "";
		try 
		{
			editCap = cmnLblArray[0];
			delCap  = cmnLblArray[1];
			viewCap = cmnLblArray[4];
		}
		catch (e)
		{
//			alert ("EXCEPTION THROWN ");
			editCap = "Edit";
			delCap  = "Delete";
			viewCap = "View";
		}
		
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id  + "','" + rowNumberForRow + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "','" + rowNumberForRow + "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+editCap+"</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "','" + rowNumberForRow + "')>"+viewCap+"</a>";
	
		counter++;
	
		trow.insertCell(len + 2).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "_rowNumber' value='" + rowNumberForRow + "'/>";			
		trow.cells[len + 2].style.display = 'none';
		rowNumberForRow = parseInt(rowNumberForRow) + 1;
		document.getElementById("Incremented_rowNumber").value = rowNumberForRow;
	
	return rowNumberForRow - 1;
	}

	//	202414	:	CHIRAGKUMAR SHAH
	
	function addDBDataInTableAttachment(tableId, hiddenField, displayFieldArray, xmlFilePath, attachmentIds,
			editMethodName, deleteMethodName, viewMethodName)
	{

		if(deleteMethodName == undefined) {
			deleteMethodName = '';
		}
		if(editMethodName == undefined) {
			editMethodName = '';
		}
		if(viewMethodName == undefined) {
			viewMethodName = '';
		}
		
//		alert('attachmentIds : ' + attachmentIds);
		document.getElementById(tableId).style.display='';
		var trow=document.getElementById(tableId).insertRow();

		trow.id = 'row' + hiddenField + counter;
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';		
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+1).innerHTML = displayFieldArray[i];	
		}	
		if(document.getElementById("Incremented_rowNumber")==null)
		{
			var table = document.getElementById(tableId);
			table.rows[0].insertCell(len+1).innerHTML = "<INPUT type='hidden' name='Incremented_rowNumber' id='Incremented_rowNumber' value='1'/>";
			table.rows[0].cells[len+1].style.display = 'none';
		}
		var rowNumberForRow = document.getElementById("Incremented_rowNumber").value;
		var editCap = "";
		var delCap  = "";
		var viewCap = "";
		try 
		{
			editCap = cmnLblArray[0];
			delCap  = cmnLblArray[1];
			viewCap = cmnLblArray[4];
		}
		catch (e)
		{
//			alert ("EXCEPTION THROWN ");
			editCap = "Edit";
			delCap  = "Delete";
			viewCap = "View";
		}
		
		
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id  + "','" + rowNumberForRow + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "','" + rowNumberForRow + "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow + 
												 "')>View</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+editCap+"</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "','" + rowNumberForRow + "')>View</a>";
	
		trow.insertCell(len + 2).innerHTML = "<INPUT type='hidden' name='attachment" + hiddenField + "' id='attachment" + hiddenField + counter + "' value='" + attachmentIds + "' />";		
		trow.cells[len + 2].style.display = 'none';					
	
		trow.insertCell(len + 3).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "_rowNumber' value='" + rowNumberForRow + "'/>";
		trow.cells[len + 3].style.display = 'none';
		rowNumberForRow = parseInt(rowNumberForRow) + 1;
		document.getElementById("Incremented_rowNumber").value = rowNumberForRow;
	
		counter++;
	return rowNumberForRow - 1;
	}



function updateDataInTableAttachment(tableId, hiddenField, displayFieldArray) {
	var trow = document.getElementById(updateRow);
	//var hFieldId = updateRow.substring(updateRow.length-1, updateRow.length);				
	var hFieldId = updateRow.substring(3, updateRow.length);				
	//	MAKING IT NULL BECAUSE IT WILL USED TO IDENTIFY IF
	//	ANY OTHER MULTIPLE EDIT IS OPEN IN EDIT MODE.
	updateRow = null;
	if(flagForUpdatedVO)
	{
//		alert ("_U append to XML response..");
		document.getElementById(hFieldId).value = xmlHttp.responseText +"_U";
		flagForUpdatedVO = false;
	}
	else
	{
		document.getElementById(hFieldId).value = xmlHttp.responseText;
	}
	
	
	//	trow.cells[0].innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hFieldId + "' value='" + xmlHttp.responseText + "'/>";					
	trow.cells[0].style.display = 'none';
	
	var len = displayFieldArray.length;
	for(var i = 0; i < len; i++) {
		var field = document.getElementById(displayFieldArray[i]);
		//alert(field.type);
		if(field.type == 'select-one') 
		{
			/* Code added by Tarun Trehan to check "Select" value in drop down
			for multiple add case. */
			if(field.options[field.selectedIndex].value == '-1')
			{
				trow.cells[i+1].innerHTML = "";
			}
			else
			{
				trow.cells[i+1].innerHTML = field.options[field.selectedIndex].text;
			}
		}
		else {
			trow.cells[i+1].innerHTML = field.value;	
		}
	}

	var rowNum = parseInt(document.getElementById("Incremented_rowNumber").value) - 1;
	//var tbl = document.getElementById(tableId);
	//var rowNum = tbl.rows.length - 1;
	//alert('updateRecord:rowNum'+rowNum);	
	
	return rowNum;
//	alert(trow.rowIndex);
//	if(trow.rowIndex == 1)
//	{
//		return trow.rowIndex;
//	}
//	else
//	{
//		return trow.rowIndex - 1;		
//	}
}

function sendAjaxRequestForEditAttachment(rowId, methodName, attachementName, rowNumber) 
{
	var editNtCap = "";
	try
	{
		editNtCap = cmnLblArray[5];	
	}
	catch(e)
	{
		editNtCap = "You can not edit this record, Because you have open one record for update."
	}
	if(updateRow != null)
	{
		alert (editNtCap);
		return ;
	}	

	updateRow = rowId;
	var hField = rowId.substring(3, rowId.length);	
	var xmlFileName = document.getElementById(hField).value;	
	//alert("xmlFileName IN sendAjaxRequestForEditAttachment:" +xmlFileName);
	
	var attachHField = 'attachment' + hField;
	//alert('attachHField : ' + attachHField);
	var attachHFieldObj = document.getElementById(attachHField);
	//alert('attachHFieldObj : ' + attachHFieldObj);	
	
	if(isAllreadyAddedVOFileName(xmlFileName))
	{
		flagForUpdatedVO = true;		
	}
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
	
	showProgressbar("Please Wait...");
		
	var attachmentString = '&attachmentName=';
	var attachementNames = attachementName.split(',');
	//alert(attachementNames.length);
	for (var i = 0; i < attachementNames.length; i++)  
	{
		var currRowNum = rowNumber-1;
		var removeFun = 'removeRowFromTable'+attachementNames[i] + '('+currRowNum+',"EditAttachmentIsTrue")';
	
		eval(removeFun);
	
		attachmentString = attachmentString + attachementNames[i];
		if(i<(attachementNames.length-1))
		{
			attachmentString = attachmentString + '&attachmentName=';
		}
	}

	var attachmentIdString = '';
	if(attachHFieldObj != null) {	
		var attachmentIds = attachHFieldObj.value;
	//	alert('attachmentIds : ' + attachmentIds);	
	
		var attachementIdsArr = attachmentIds.split(',');	
	//	alert('attachementIdsArr.length : ' + attachementIdsArr.length);
		for (var i = 0; i < attachementIdsArr.length; i++)  
		{
			attachmentIdString = attachmentIdString + '&attachmentId=' + attachementIdsArr[i];
		}
	}
	
	
	//var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName+'&attachmentName=' + attachementName +'&rowNumber='+rowNumber;
	var url="";
	//alert("attachmentIdString"+attachmentIdString);
	if(attachmentIdString=='&attachmentId=0')
	{
		url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName+ '&rowNumber='+rowNumber;
		//alert(url);
	}
	else
	{
		url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName+ attachmentString + attachmentIdString + '&rowNumber='+rowNumber;
		//alert(url);
	}
	//alert('sendAjaxRequestForEditAttachment:url '+url);
	methodName = methodName + "()";
	xmlHttp.onreadystatechange=function() { 
		if(xmlHttp.readyState == 4) {
			eval(methodName);
			hideProgressbar();
		 }
	}

	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);	
}

function populateAttachment(decXML,formName)
{
//		alert('populateAttachment called...');
		var appDesXML = decXML.split('$$$ATTACHMENT_XML$$$');
		
//		alert(appDesXML[1]);
		var xmlDOMReturn = getDOMFromXML(appDesXML[0]);
		
		var xmlDOM = getDOMFromXML(appDesXML[1]);
		
		if(xmlDOM!=null)
		{
			var attachmentNames = xmlDOM.getElementsByTagName('element');
			//alert('attachmentNames.length'+attachmentNames.length);
			for (k = 0; k < attachmentNames.length; k++)  
			{
				//var attachmentName = xmlDOM.getElementsByTagName('attachmentName')[0].childNodes[0].text;
				var attachmentName = attachmentNames[k].childNodes[0].text;				

			    //var entries = xmlDOM.getElementsByTagName('void');		 
			    var entries = attachmentNames[k].childNodes;
			    
				var propName;
				var fileName;
				var fileDescription;
				var viewURL;
				//var removeFun = 'removeRowFromTable'+attachmentName + '(0,"'+formName+'")';
				//eval(removeFun);
				
				for (var i = 0; i < entries.length; i++)  
				{   
//					alert("NodeName = " + entries[i].nodeName);
					if(entries[i].nodeName == 'void')
					{
						if(entries[i].childNodes[2].text == 'NA')
						{
//							alert('in if viewUrl: ' + entries[i].childNodes[2].text);
							fileName = entries[i].childNodes[0].text;
							fileDescription = entries[i].childNodes[1].text;
							//var j = i + 1;//Attachment Name should be skipped.
							var j = i;
							var result = fileName.lastIndexOf("\\");
						    fileName = fileName.substring(result+1, fileName.length);
							var insertRow = 'insRowForAdd' + attachmentName + '(" '+  fileName + ' ","'+fileDescription+'","'+j+'",false,"") ';
							eval(insertRow);
						}
						else 
						{
//							alert('in else viewUrl: ' + entries[i].childNodes[2].text);
							fileName = entries[i].childNodes[0].text;
							fileDescription = entries[i].childNodes[1].text;
							viewURL = entries[i].childNodes[2].text;
							viewURL = viewURL.replace("$","&");
							viewURL = viewURL.replace("$","&");
							var j = i + 1;
							var insertRow = 'insRowForAdd' + attachmentName + '(" '+  fileName + ' ","'+fileDescription+'","'+j+'",true,"'+viewURL+'") ';
							eval(insertRow);					
						}
					}
					//insRowForAddattachmentBio(fileName,fileDescription,i+1,false,'');
				}
			}
		}

	return xmlDOMReturn;
}

function editAttachment(attachmentId,attachmentName)
{
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
	
	//var removeFun = 'removeRowFromTable'+attachmentName + '(0,0)';//2007-11-01 18:00
	var removeFun = 'removeRowFromTable'+attachmentName + '(0,"EditAttachmentIsTrue")';
	
	eval(removeFun);	
	
	var url='hdiits.htm?actionFlag=getAttachmentXml&attachmentId='+attachmentId+'&attachmentName='+attachmentName;
	
	//methodName = methodName + "()";
	xmlHttp.onreadystatechange=function() { eval('populateAttachmentForEdit()'); }
	
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);			
}
function populateAttachmentForEdit()
{
	if (xmlHttp.readyState == 4) 
	{ 	
	  	var decXML = xmlHttp.responseText;

		var appDesXML = decXML.split('$$$ATTACHMENT_XML$$$');	
		var xmlDOMReturn = getDOMFromXML(appDesXML[0]);
		var xmlDOM = getDOMFromXML(appDesXML[1]);

		if(xmlDOM!=null)
		{		
			var attachmentName = xmlDOM.getElementsByTagName('attachmentName')[0].childNodes[0].text;
		    var entries = xmlDOM.getElementsByTagName('void');		 
		    
			var propName;
			var fileName;
			var fileDescription;
			var viewURL;
			
			//removeRowFromTableattachmentBiometric(0);    
			
			for (var i = 0; i < entries.length; i++)  
			{    			
				fileName = entries[i].childNodes[0].text;
				fileDescription = entries[i].childNodes[1].text;
				viewURL = entries[i].childNodes[2].text;
				viewURL = viewURL.replace("$","&");
				viewURL = viewURL.replace("$","&");
				var j = i + 1;
				var insertRow = 'insRowForAdd' + attachmentName + '(" '+  fileName + ' ","'+fileDescription+'","'+j+'",true,"'+viewURL+'") ';
				eval(insertRow);
				//insRowForAddattachmentBio(fileName,fileDescription,i+1,true,viewURL);
			}
		}
		
   	}
   	return xmlDOMReturn;
}
function sendAjaxRequestForViewAttachment(rowId, methodName, attachementName, rowNumber) 
{
	updateRow = rowId;
	var hField = rowId.substring(3, rowId.length);	
	var xmlFileName = document.getElementById(hField).value;	
//	alert("xmlFileName IN sendAjaxRequestForEditAttachment:" +xmlFileName);
	
	var attachHField = 'attachment' + hField;
//	alert('attachHField : ' + attachHField);
	var attachHFieldObj = document.getElementById(attachHField);
//	alert('attachHFieldObj : ' + attachHFieldObj);	
	
	if(isAllreadyAddedVOFileName(xmlFileName))
	{
		flagForUpdatedVO = true;		
	}
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
	
	showProgressbar("Please Wait...");
		
	var attachmentString = '&attachmentName=';
	var attachementNames = attachementName.split(',');
	for (var i = 0; i < attachementNames.length; i++)  
	{
		var currRowNum = rowNumber-1;
		var removeFun = 'removeRowFromTable'+attachementNames[i] + '('+currRowNum+',"EditAttachmentIsTrue")';
	
		eval(removeFun);
	
		attachmentString = attachmentString + attachementNames[i];
		if(i<(attachementNames.length-1))
		{
			attachmentString = attachmentString + '&attachmentName=';
		}
	}

	var attachmentIdString = '';
	if(attachHFieldObj != null) {	
		var attachmentIds = attachHFieldObj.value;
//		alert('attachmentIds : ' + attachmentIds);	
	
		var attachementIdsArr = attachmentIds.split(',');	
//		alert('attachementIdsArr.length : ' + attachementIdsArr.length);
		for (var i = 0; i < attachementIdsArr.length; i++)  
		{
			attachmentIdString = attachmentIdString + '&attachmentId=' + attachementIdsArr[i];
		}
	}
	
	//var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName+'&attachmentName=' + attachementName +'&rowNumber='+rowNumber;
	var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName+ attachmentString + attachmentIdString + '&rowNumber='+rowNumber;
//	alert(url);
	
	//alert('sendAjaxRequestForEditAttachment:url '+url);
	methodName = methodName + "()";
	xmlHttp.onreadystatechange=function() { 
		if(xmlHttp.readyState == 4) {
			eval(methodName);
			hideProgressbar();
		 }
	}

	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);	
}
	