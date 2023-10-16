		//=========================================
		
if(arrMulti==null)
{
	//	alert("arrMulti : "+arrMulti);
	var arrMulti= new Array();
}
var rowToUpdate;
var counter = 1;
var updateRow = null;
var flagForUpdatedVO = false;

	function addOrUpdateRecord(methodName, actionFlag, fieldArray, progressBarFlag) 
	{
		showAlert ("METHOSD NAME :"+methodName);
		xmlHttp = null ;
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  showAlert ("Your browser does not support AJAX!");
		  return;
		} 
//		alert ("PRGRESS :"+progressBarFlag);
		if(progressBarFlag != false)
		{
			showProgressbar("Please Wait...");	
		}
				
		var reqBody = getRequestBody(fieldArray);
//		alert ("REQ BODY 33:"+reqBody);
//		var url='ifms.htm?actionFlag=' + actionFlag + '&' + reqBody;
		var url='ifms.htm?actionFlag=' + actionFlag ;
		methodName = methodName + "()";
		
		xmlHttp.onreadystatechange=function() {
			if(xmlHttp.readyState == 4 ) 
			{
				eval(methodName);
				if(progressBarFlag != false)
				{
					hideProgressbar();
				}
			}	
		}
	
		xmlHttp.open("POST",encodeURI(url),true);
		
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//		xmlHttp.setRequestHeader("Content-length", reqBody.length);
//		xmlHttp.setRequestHeader("Connection", "close");

		xmlHttp.send(reqBody);
	}	
	


	
	function getRequestBody(fieldArray)
	{
		//showAlert("field array length : "+fieldArray.length);
		var aParams = new Array();
//		alert ("LENGTH :"+fieldArray.length);
		for(var i = 0; i < fieldArray.length; i++)
		{
//			alert("fieldArray["+i+"] : "+fieldArray[i]);
			var elementArr = document.getElementsByName(fieldArray[i]);
			var sParam  = '';
			var eleType = '';
			if(elementArr.length > 0) 
			{
				sParam  = elementArr[0].name; 
				eleType = elementArr[0].type;
			}
			else
			{
//				alert ("ELEMENT HAVE NAME ID DIFFERENT"+fieldArray[i]);
				var t1 = document.getElementById(fieldArray[i]);
				sParam  = t1.name; 
				eleType = t1.type;
				elementArr = new Array ();
				elementArr.push (t1 );
			}
/*
			alert ("NAME :"+sParam );
			alert ("TYPE :"+eleType) ;
			alert ("LENG :"+elementArr.length);
*/
			//var sParam = document.getElementById(fieldArray[i]).name;
			sParam += "=";

			//if(document.getElementById(fieldArray[i]).type == 'radio') 
			if(eleType == 'radio')
			{
				//showAlert('in radio');
				//var rdo = document.getElementsByName(fieldArray[i]);
				for(var j = 0; j < elementArr.length; j++) 
				{
					if(elementArr[j].checked) 
					{
						sParam += elementArr[j].value;	
//						alert (sParam);
						aParams.push(sParam);		
					}
				}			
			}
			else if(eleType == 'select-multiple')
			{
				//showAlert('in select-multiple');
				var lstbox = document.getElementById(fieldArray[i]);
				for(var j=0; j<lstbox.length; j++)
				{
					if(lstbox[j].selected)
					{
						var a = sParam;
						a += lstbox[j].value;
						aParams.push(a);		
					}
				}
			}
			else if(eleType == 'checkbox')
			{
				//showAlert('in checkbox');
				//var chkbox = document.getElementsByName(fieldArray[i]);
				for(var j=0; j<elementArr.length; j++)
				{
					if(elementArr[j].checked)
					{
						var a = sParam;
						a += elementArr[j].value;
						aParams.push(a);		
					}
				}
			}
			else 
			{	
				//showAlert('in else');
				//var elementArr = document.getElementsByName(fieldArray[i]);
				//showAlert('elementArr.length : ' + elementArr.length);
				for(var j = 0; j < elementArr.length; j++) 
				{					
					var temp = elementArr[j].name+"=";
					temp += elementArr[j].value;
//					alert ('temp : ' + temp);
					aParams.push(temp);
				}			

				//sParam += document.getElementById(fieldArray[i]).value;
				//aParams.push(sParam);		
			}
		}
		var temp =  aParams.join("&");
//		alert ("URL :"+temp);
		return temp;
	}

	function addDataInTable(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName) 
	{
//		showAlert ("addDataInTable called...");
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName == undefined) 
		{
			editMethodName = '';
		}
		if(viewMethodName == undefined) 
		{
			viewMethodName = '';
		}
		
		document.getElementById(tableId).style.display='';
//		var trow=document.getElementById(tableId).insertRow();
		//=======
		var table = document.getElementById(tableId);
		var lastRow = table.rows.length;
		var trow = table.insertRow(lastRow);
		//=======
		trow.id = 'row' + hiddenField + counter;
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" + xmlHttp.responseText + "'/>";				
		trow.cells[0].style.display = 'none';
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			//showAlert(field.type);
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
			else if(field.type == 'select-multiple')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				//showAlert(field.type);
				//showAlert(field.options.length);
				var displaystring = "";
				for(var j = 0; j < field.options.length; j++)
				{
					if(field.options[j].selected && field.options[j].value !='-1' )
					{
						//showAlert(j);
						if(displaystring == "")
						{
						displaystring=field.options[j].text;
						}
						else
						{
						displaystring+= " / " + field.options[j].text;
						}
					}
				}
				trow.insertCell(i+1).innerHTML = displaystring;		
			}		
			else
			{
				trow.insertCell(i+1).innerHTML = field.value;	


			}
		}	
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
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap +"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>Edit</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>"+viewCap+"</a>";
			
		counter++;	
		
		return trow.id;
	}
	/**
	 *	Function used to populate "Multiple Add Table"
	 *	with database Data.
	 * 
	 * @author 202414 CHIRAGKUMAR SHAH 
	 */
	
	function addDBDataInTable(tableId,hiddenField,dispFieldA,
						xmlFilePath,editMethodName,deleteMethodName,viewMethodName)
	{
		//	alert ('addDBDataInTable called....');
		if(deleteMethodName == undefined) 
		{
			deleteMethodName = '';
		}
		if(editMethodName 	== undefined) 
		{
			editMethodName 	 = '';
		}
		if(viewMethodName 	== undefined) 
		{
			viewMethodName 	 = '';
		}
		document.getElementById(tableId).style.display='';
		
		//=====
//		var trow=document.getElementById(tableId).insertRow();
		var table = document.getElementById(tableId);
		var lastRow = table.rows.length;
		var trow = table.insertRow(lastRow);
		//=====
		trow.id = 'row' + hiddenField + counter;
	
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';
		var len = dispFieldA.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+1).innerHTML = dispFieldA[i];	


		}
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
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
											 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
											 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
												 "')>"+viewCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>"+editCap+"</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>"+viewCap+"</a>";
		counter++;
		
		return trow.id;
	}

	function updateDataInTable(hiddenField, displayFieldArray) 
	{
		//	alert ("updateDataInTable called...");
		var trow = document.getElementById(updateRow);
		//	var hFieldId = updateRow.substring(updateRow.length-1, updateRow.length);
		var hFieldId = updateRow.substring(3, updateRow.length);
		//	MAKING IT NULL BECAUSE IT WILL USED TO IDENTIFY IF
		//	ANY OTHER MULTIPLE EDIT IS OPEN IN EDIT MODE.
		//  CHANGE BY 202414
		updateRow = null;
		
		//	alert ("xmlHttp.responseText "+xmlHttp.responseText);
		if(flagForUpdatedVO)
		{
			//	alert ("_U append to XML response..");
			//	HERE ADDED "_U" TO INDICATE THIS RECORD IS UPDATED BY USER. 
			document.getElementById(hFieldId).value = xmlHttp.responseText +"_U";
			flagForUpdatedVO = false;
		}
		else
		{
			document.getElementById(hFieldId).value = xmlHttp.responseText;
		}
		
		//trow.cells[0].innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hFieldId + "' value='" + xmlHttp.responseText + "'/>";					
		trow.cells[0].style.display = 'none';
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			//	alert(field.type);
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
			else if(field.type == 'radio')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				for(var j = 0; j < radio.length; j++)
				{
					if(radio[j].checked)
					{
						trow.cells[i+1].innerHTML = radio[j].value;
					}
				}
			}
			else if(field.type == 'select-multiple')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				//alert(field.type);
				//alert(field.options.length);
				var displaystring = "";
				for(var j = 0; j < field.options.length; j++)
				{
					if(field.options[j].selected && field.options[j].value != '-1' )
					{
						if(displaystring == "")
						{
							displaystring=field.options[j].text;
						}
						else
						{
							displaystring+= " / " + field.options[j].text;
						}
					}
				}
				trow.cells[i+1].innerHTML = displaystring;		
			}				
			
			else
			{
				trow.cells[i+1].innerHTML = field.value;	
			}
		}		
	}

	function deleteRecord(rowId) 
	{
		var delCap = "";
		var delNtAlllowed = "";
		try
		{
			delCap = cmnLblArray[2];
			delNtAlllowed = cmnLblArray[3];
		}
		catch (e)
		{
			delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
			delCap = "Are you sure you want to delete this record ?";
		}
		
		if(updateRow != null)
		{
			alert (delNtAlllowed);
			return false;
		}
		var answer = confirm(delCap);
		if(answer)
		{	
			var delRow = document.getElementById(rowId);
			//alert(delRow.rowIndex);
			var lenVar=delRow.parentNode.parentNode;
			delRow.parentNode.deleteRow(delRow.rowIndex);
			var tldLenVar=lenVar.rows.length;
			if(tldLenVar==1)
			{
				lenVar.style.display='none';
			}
		}
//		alert("ANSWER :"+answer);
	return answer;
	}

	function getDOMFromRowXML(rowId)
	{
		updateRow = rowId;
		//var hField = rowId.substring(rowId.length-1, rowId.length);	
		var hField = rowId.substring(3, rowId.length);
		var encXML = document.getElementById(hField).value;	
		var decXML = decodeBase64(encXML);
	return getDOMFromXML(decXML);
	}

	function getDOMFromXML(xmlString)
	{	
		// code for IE
		var doc;
		if (window.ActiveXObject)
		{
			
			doc=new ActiveXObject("Microsoft.XMLDOM");
			doc.async="false";
			doc.loadXML(xmlString);
		}
		// code for Mozilla, Firefox, Opera, etc.
		else
		{
			var parser=new DOMParser();
			doc=parser.parseFromString(xmlString,"text/xml");
		}
//		alert ("ERROR 	:"+doc.parseError.errorCode);
//		alert ("REASON 	:"+doc.parseError.reason);
//		alert ("ERROR LINE :"+doc.parseError.line);
		
	return doc.documentElement;		
	}
	/**
	 * Function used to check record is added in insert mode
	 * OR 
	 * update by user.In both condition return true otherwise return false.
	 *
	 * @author 202414 CHIRAGKUMAR SHAH.
	 */
	function isAllreadyAddedVOFileName(xmlFileName)
	{
		//	alert ("isAllreadyAddedVOFileName(xmlFileName) called....");
		//	alert (xmlFileName.indexOf(".xml_N"));
		if(xmlFileName.indexOf(".xml_N")>0)
		{
			return true;
		}
		if(xmlFileName.indexOf(".xml_U")>0)
		{
			return true;
		}
	return false;		
	}

	function sendAjaxRequestForEdit(rowId, methodName, progressBarFlag) 
	{
///		alert('sendAjaxRequestForEdit called....');
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
		if(progressBarFlag != false)
		{
			showProgressbar("Please Wait...");
		}
		
		updateRow = rowId;
		var hField = rowId.substring(3, rowId.length);
		//	alert(hField);
		var xmlFileName = document.getElementById(hField).value;
		//	alert('xmlFileName'+xmlFileName);
		if(isAllreadyAddedVOFileName(xmlFileName))
		{
			flagForUpdatedVO = true;		
		}
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) 
		{
			if(progressBarFlag != false)
			{
			  hideProgressbar();
			}
		  alert ("Your browser does not support AJAX!");
		  return;
		} 		
		
		var url='ifms.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
		methodName = methodName + "()";
		
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4) {
				eval(methodName);
				if(progressBarFlag != false)
				{
					hideProgressbar();
				}
			 }
		 }
		
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(null);	
	}

	function longToDate(d)
	{
		//alert("long value is" + d);
		var dt = new Date();
		dt.setTime(d);
		var date = dt.getDate();
		var strDate;
		if(date < 10)
			strDate = "0"+date;
		else
			strDate = date;
		var mnth = dt.getMonth()+1;
		var strMnth;
		if(mnth<10)
			strMnth = "0"+mnth;
		else
			strMnth = mnth;
		var yr = dt.getFullYear();
		
		var str = strDate+"/"+strMnth+"/"+yr;
		return str;
	}

	/* this is the function to convert long value to time in string format */
	function longToTime(d)
	{
		//alert("long value is" + d);
		var dt = new Date();
		dt.setTime(d);
		var hours = dt.getHours();
		var strHrs;
		if(hours<10)
			strHrs = "0"+hours;
		else
			strHrs = hours;
		
		var minutes = dt.getMinutes();
		var strMins;
		if(minutes<10)
			strMins = "0"+minutes;
		else
			strMins = minutes;
		
		var seconds = dt.getSeconds();
		var str = strHrs + ":" + strMins;
		return str;
	}
// for getting value from xml by passing parent and child name
// parent name--propertyType for e.g cmnlookupByGender
// child name---propertyName for e.g lookupName
	function getValueFromDOMByName(domObj,propertyType ,propertyName) 
	{
	    var entries = domObj.getElementsByTagName('void');		 
		var propName;
		var propType;
		var j=0;
		var value="";
		for (var i = 0; i < entries.length; i++)
		{    			
			propType = entries[i].getAttribute('property');
			if(propType == propertyType)
			{
				for(j=0; j< entries[i].childNodes.length; j++)
				{
					   for(k=0;k<entries[i].childNodes[j].childNodes.length;k++)
					   {
				           propName=entries[i].childNodes[j].childNodes[k].getAttribute('property');
				           if(propName == propertyName)
				           {
				           	value=entries[i].childNodes[j].childNodes[k].text;
				           }
						}
				}
			}
		}
	return value;
	}
	function getAllValueFromDOM(domObj, propertyName)
 	{
		var retArr = new Array();
		var entries = domObj.getElementsByTagName('void');		 
		var propName;
		var j=0;    
		for (var i = 0; i < entries.length; i++)
		{    			
			propName = entries[i].getAttribute('property');
			if(propName == propertyName) 
			{
				retArr[j]=entries[i].childNodes[0].text;
				j++;
			}
		}
	return retArr;
	}
 	
 	function getXMLFromParentProperty(xmlDoc,propertyName)
 	{
		var propName;
		var retXML;
		var x=xmlDoc.getElementsByTagName("void");
		for(var i=0;i<x.length;i++)
		{
			propName = x[i].getAttribute('property');
			if(propName == propertyName)
			{
				var fchild = x[i].firstChild;
				var parent=fchild.parentNode;
				retXML =parent.xml
			}
		}
	return retXML ;
 	}
	//	202414 CHIRAGKUMAR SHAH
	//	START : FUNCTION RELATED TO MULTIPLE ADD WITH EDIT MODE.

	function getReferencePathOfNode(xmlDOM,nodeStr)
	{
		//	alert("function getReferencePathOfNode(xmlDOM,nodeStr) called.");
		var indexOfOpnSqrBr = nodeStr.indexOf ("[");
		////alert ("indexOfOpnSqrBr :"+indexOfOpnSqrBr);
		if(indexOfOpnSqrBr > 0)
		{
			var nodeStrForRef =  nodeStr.substring (0,indexOfOpnSqrBr);
			
			var node = xmlDOM.selectNodes(nodeStrForRef);
			var nodeObjPro ;
			try
			{
				nodeObjPro = node[0].getAttribute('reference');
			}
			catch(e)
			{
//				//alert("EXCEPTION CODE 1 :NODE HAS NOT REFERENCE ATTRIBUTE.");
				return null;
			}
			//	alert("nodeObjPro :"+nodeObjPro);
			if(nodeObjPro == null)
			{
				return null;
			}
			else
			{
				nodeStrForRef = nodeStr.substring(0,indexOfOpnSqrBr)+"/" + nodeObjPro + nodeStr.substring(indexOfOpnSqrBr);
				return nodeStrForRef;
			}
		}
		var nodeObjPro ;
		try
		{
			var nodeObj = xmlDOM.selectNodes(nodeStr);
			nodeObjPro = nodeObj[0].getAttribute('reference');
		}
		catch (e)
		{
//			alert("EXCEPTION CODE 2 :NODE HAS NOT REFERENCE ATTRIBUTE."); 
			return null ;
		}
	nodeObjPro = nodeStr.substring(0,indexOfOpnSqrBr) +"/"+ nodeObjPro + nodeStr.substring(indexOfOpnSqrBr);
	return 	nodeObjPro ;
	}
	
	
	function getValueFromDOM(domObj, propertyName) 
	{
	    var entries = domObj.getElementsByTagName('void');		 
		var propName;
		for (var i = 0; i < entries.length; i++)
		{
			propName = entries[i].getAttribute('property');
			if(propName == propertyName)
			{
				//alert('propName:' + propName);
				//alert('value:' + entries[i].childNodes[0].text);
				return entries[i].childNodes[0].text;
			}
		}
	return null;
	}	
	/**
	 * 	Function used to get value from XMLDom at given XPath.
	 *	@author	202414	CHIRAGKUMAR SHAH.	
	 */
	function getXPathValueFromDOM(xmlDOM, propertyXPath)
	{
//		alert ("getValueFromDOM(xmlDOM, propertyName) called...");
//		alert ("xmlDOM.nodeName :"+xmlDOM.nodeName);
		showAlert ("propertyXPath :"+propertyXPath);
		if (! window.ActiveXObject)
		{
			propertyXPath = convertXpathForFFIndexBase (propertyXPath) ;
		}
		showAlert ("propertyXPath :"+propertyXPath);
		var nodeStr = "/"+xmlDOM.nodeName+"/"+propertyXPath;
//		alert("nodeStr  :"+nodeStr);
		nodeStr = getFullPath(xmlDOM,nodeStr);
		showAlert("FULL PATH RETURN FROM getFullPath 1"+nodeStr);
		
		
		if(nodeStr == null)
		{
//			alert("RETURN NULL FROM  getValueFromDOM");
			return null;			
		}
		
		var finalNodeValue ;
		try 
		{
			if (window.ActiveXObject)
			{
				var nodeTemp = xmlDOM.selectNodes(nodeStr);
				finalNodeValue = nodeTemp[0].childNodes[0].nodeValue;
			}
			else
			{
				//nodeStr = convertXpathForFFIndexBase (nodeStr) ;
				finalNodeValue = getValueForFF (xmlDOM,nodeStr) ;
			}
		}
		catch(e)
		{
//			alert ("ERROR CODE 2 : NODE HAVE NOT A VALUE ");
			finalNodeValue = null;
		}
		showAlert("finalNodeValue :"+finalNodeValue);
	return finalNodeValue;		
	}
	/**
	 * 	Function return true if nodeStr has "set" child tag 
	 * 						else return false.	 
	 *	@author	202414	CHIRAGKUMAR SHAH.	
	 */
	
	function isSetTagPresent(xmlDOM, nodeStr)
	{
		showAlert ("isSetTagPresent(xmlDOM, nodeStr) called.");
		showAlert (" nodeStr :"+nodeStr );
		
		var nodeObjName = null;
		try
		{
			if (window.ActiveXObject)
			{
				var node = xmlDOM.selectNodes(nodeStr);
				nodeObjName = node[0].childNodes[0].nodeName;
			}
			else
			{
				var xpe = new XPathEvaluator();
				//alert("xpe : "+xpe);
				var nsResolver = xpe.createNSResolver(xmlDOM.ownerDocument == null ? xmlDOM.documentElement : xmlDOM.ownerDocument.documentElement);
				var nodeTemp = xpe.evaluate(nodeStr, xmlDOM, nsResolver, XPathResult.ANY_TYPE,null);
				var result=nodeTemp.iterateNext();
				//alert (result.childNodes[1].nodeName);
				nodeObjName = result.childNodes[1].nodeName;
			}
			
		}
		catch(e)
		{
			showAlert ("EXCEPTION THROWN 1318");
			showAlert (e);
			throw e;			
		}
		if(nodeObjName == "set")
		{
//			showAlert ("return true ");
			return true;
		}
//	alert ("return false");
	return false;		
	}
	
	function deleteDBRecord(rowId)
	{
		var delCap = "";
		var delNtAlllowed = "";
		try
		{
			delCap = cmnLblArray[2];
			delNtAlllowed = cmnLblArray[3];
		}
		catch (e)
		{
			delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
			delCap = "Are you sure you want to delete this record ?";
		}
		if(updateRow != null)
		{
			alert (delNtAlllowed);
			return false;
		}
		var answer = confirm(delCap);
		if(answer)
		{
			var delRow 	= document.getElementById(rowId);
			var content = delRow.cells[0].innerHTML;
			showAlert(content);
			content = content.replace(".xml_N",".xml_D");
			content = content.replace(".xml_U",".xml_D");
			delRow.cells[0].innerHTML = content;					
			delRow.style.display='none';
		}
	return answer;	
	}

	
	
	function showAlert (value)
	{
//		alert (value);
	}
	/**
	 * Return full xpath of given argpath.
	 * NOTE : MUST CALL WITH ROOT NODE NAME.
	 * 
	 * @author 202414 - CHIRAGKUMAR SHAH
	 */
	function getFullPath(xmlDOM,argPath)
	{
		showAlert ("getFullPath () called.");
		var returnPath ;
		var pathElArray = argPath.split ("/");
//		alert ("pathElArray.length :"+pathElArray.length );
		var flagForNullValue = false; 
		for (var i = 0;i < pathElArray.length;i++)
		{
			showAlert ("IN FOR LOOP i"+i+" : "+ pathElArray[i]);
			if(i == 0)
			{
				// because it it is root node.
				continue;
			}
			if(i == 1)
			{
				returnPath = "/" + pathElArray[i];
			}
			else
			{
				returnPath = returnPath +"/"+ pathElArray[i];
			}				
			if(isNodeIsSet(pathElArray[i]))
			{
				showAlert ('NODE IS TYPE OF SET 1400'+pathElArray[i])
				var temp = getReferencePathForSetTypeNode(xmlDOM,returnPath);
				showAlert ("TEMP :"+temp);
				if(temp == null)
				{
					flagForNullValue = true;
					showAlert ("flagForNullValue SET TO true");
					break;
				}
				else
				{
					returnPath = temp;
				}
			}
			else
			{
//				alert ("current node normal type"); 
				var temp = getReferencePathForNormalTypeNode(xmlDOM,returnPath);
				if(temp != null)
				{
					returnPath = temp;
				}
			}
		}// END FOR LOOP
		if(flagForNullValue)
		{
			return null;
		}
	return returnPath;	
	}
	function isNodeIsSet(currNode)
	{
		var indexOfOpnSqr = currNode.indexOf("[");
		if(indexOfOpnSqr > 0)
		{
			return true;
		}
	return false;	
	}

	function getReferencePathForSetTypeNode(xmlDOM,argNodeStr)
	{
		var indexOfClsSqr = argNodeStr.lastIndexOf("]") ;
		var tempStr1 = argNodeStr.substring(0,indexOfClsSqr	);
		var indexOfOpnSqr = tempStr1.lastIndexOf("[") ;
		var nodeStr1 = argNodeStr.substring(0,indexOfOpnSqr);
		showAlert ("NODE STR 1446:"+nodeStr1);
		

		var nodeObjPro ;
		try
		{
			if (window.ActiveXObject)
			{
				var node = xmlDOM.selectNodes(nodeStr1);
				nodeObjPro = node[0].getAttribute('reference');
				// alert("nodeObjPro IE : "+nodeObjPro);
			}
			else
			{
				var xpe = new XPathEvaluator();
				var nsResolver = xpe.createNSResolver(xmlDOM.ownerDocument == null ? xmlDOM.documentElement : xmlDOM.ownerDocument.documentElement);
				var nodeT1 = xpe.evaluate(nodeStr1, xmlDOM, nsResolver, XPathResult.ANY_TYPE,null);
				var res = nodeT1.iterateNext();
				nodeObjPro = res.getAttribute('reference');
				showAlert("nodeObjPro FOR FF : "+nodeObjPro);
			}
		}
		catch(e)
		{
			showAlert("EXCEPTION CODE 1 :NODE HAS NOT REFERENCE ATTRIBUTE.");
			showAlert (e);
			nodeObjPro = null;
		}
		
		if(nodeObjPro != null)
		{
			if (window.ActiveXObject)
			{
				nodeObjPro = getRefXPathAccoIE(nodeObjPro);	
			}
			nodeStr1 = nodeStr1 + "/" + nodeObjPro;	
		}

		var flagForSetTypeTag = false;
		try 
		{
			flagForSetTypeTag = isSetTagPresent(xmlDOM,nodeStr1);	
		}
		catch (e)
		{

			return null;
		}
		
		if(flagForSetTypeTag )
		{
			nodeStr1 = nodeStr1 +"/set";
		}
		var setObjName = null;
		if (window.ActiveXObject)
		{
			var tempNode1 = xmlDOM.selectNodes(nodeStr1);

			var childNodeName = tempNode1[0].childNodes;
		// 	there is no object in set like "<set/>"
			if(childNodeName.length == 0)
			{
				showAlert("ERROR CODE 2: THERE IS NO OBJECT IN SET ");
				return null;
			}
			setObjName = tempNode1[0].childNodes[0].nodeName;
		}
		else
		{
			var xpe = new XPathEvaluator();
			var nsResolver = xpe.createNSResolver(xmlDOM.ownerDocument == null ? xmlDOM.documentElement : xmlDOM.ownerDocument.documentElement);
			tempNode2 = xpe.evaluate(nodeStr1, xmlDOM, nsResolver, XPathResult.ANY_TYPE,null);
			res2 = tempNode2.iterateNext();
			childNodeName = res2.childNodes;
			showAlert("childNodeName FOR  FF : "+childNodeName);
			setObjName = res2.childNodes [1].nodeName ;
		}
		showAlert ("SET OBJ NAME 1520: "+setObjName);
		nodeStr1 = nodeStr1 +"/"+setObjName ;

		
		showAlert ("nodeStr1 1523 "+nodeStr1) ;
		
		nodeStr1 = nodeStr1 + argNodeStr.substring (indexOfOpnSqr);
		showAlert ("nodeStr1 1526 "+nodeStr1) ;

		var nodeStrTemp = getReferencePathForNormalTypeNode(xmlDOM,nodeStr1)
		showAlert ("nodeStrTemp  1529:"+nodeStrTemp );
		if(nodeStr1 != nodeStrTemp)




		{
			showAlert ("nodeStr1 != nodeStrTemp");
			nodeStr1 = nodeStrTemp;
		}
		showAlert("nodeStr1:	1535"+nodeStr1);
	return nodeStr1;
	}

	function getReferencePathForNormalTypeNode(xmlDOM,argNodeStr)
	{
//		alert ("getReferencePathForNormalTypeNode called..");
//		alert ("argNodeStr :"+argNodeStr);
		
//		alert("tempNode1");
//		alert(tempNode1);
		var nodeObjPro ;
		try
		{
			if (window.ActiveXObject)
			{
				var tempNode1 = xmlDOM.selectNodes(argNodeStr);
				nodeObjPro = tempNode1[0].getAttribute('reference');
			}
			else
			{
				var xpe = new XPathEvaluator();
				var nsResolver = xpe.createNSResolver(xmlDOM.ownerDocument == null ? xmlDOM.documentElement : xmlDOM.ownerDocument.documentElement);
				tempNode1 = xpe.evaluate(argNodeStr, xmlDOM, nsResolver, XPathResult.ANY_TYPE,null);
				res = tempNode1.iterateNext();
				nodeObjPro = res.getAttribute('reference');
			}
		}
		catch(e)
		{
			showAlert("EXCEPTION CODE 1 :NODE HAS NOT REFERENCE ATTRIBUTE.\nIn function getReferencePathForNormalTypeNode");
			nodeObjPro = null;
		}
		
		if(nodeObjPro == null)
		{
//			alert("AS IT IS ");
			return argNodeStr ;
		}
//		alert ("RETURN NODE STRING + REFERENCE ATTRIBUTE");
		if (window.ActiveXObject)
		{
			nodeObjPro = getRefXPathAccoIE(nodeObjPro);	
		}
		var returnTemp =  argNodeStr + "/" + nodeObjPro;
//		alert("returnTemp :"+returnTemp);
	return returnTemp;	
	}
	
	function getRefXPathAccoIE(refXpath)
	{
		//alert ("getRefXPathAccoIE called...");
		var returnXPath = "";
		var indexOfOpnSqrBr = refXpath.indexOf('[');
		//alert("indexOfOpnSqrBr :"+indexOfOpnSqrBr);
		
		if(indexOfOpnSqrBr < 0)
		{
			//alert ('return from 0');
			return refXpath;			
		}
		var indexOfClsSqrBr = refXpath.indexOf(']',indexOfOpnSqrBr);
		//alert("indexOfClsSqrBr :"+indexOfClsSqrBr);
		var number = refXpath.substring (indexOfOpnSqrBr+1,indexOfClsSqrBr);
		//alert ("number "+number)
		number = number - 1;
		var front = refXpath.substring (0,indexOfOpnSqrBr + 1);
		//alert("front :"+front);
		var end = refXpath.substring (indexOfClsSqrBr );
		//alert("end :"+end);
		returnXPath = front + number + end;
		//alert ("returnXPath  :"+returnXPath );
		
		var indexOfOpnSqrBr1 = returnXPath.indexOf('[',indexOfClsSqrBr);
		//alert ("indexOfOpnSqrBr1  :"+indexOfOpnSqrBr1 );
		if(indexOfOpnSqrBr1 < 0)
		{
			//alert ('return from 1');
			return returnXPath;
		}
		var indexOfClsSqrBr1 = returnXPath.indexOf(']',indexOfOpnSqrBr1);
		//alert ("indexOfClsSqrBr1 :"+ indexOfClsSqrBr1);
		var number1 = returnXPath.substring (indexOfOpnSqrBr1+1,indexOfClsSqrBr1);
		//alert("number1 :"+number1);
		number1 = number1 - 1;
		var front1 = returnXPath.substring (0,indexOfOpnSqrBr1 + 1);
		//alert ("front1 :"+front1);
		var end1 = returnXPath.substring (indexOfClsSqrBr1 );
		//alert ("end1 "+end1);
		
		returnXPath = front1 + number1 + end1;
		//alert("returnXPath :"+returnXPath);
	return returnXPath;			
	}
	
	/**
	 *	Function return length of child node 
	 *	of given set path.
	 *  @author Chiragkumar Shah - 202414
	 */
	function getChildNodeLengnthOfGivenSet(xmlDOM,upToSetPath)
	{
//		alert ("function getLengnthOfGivenSet called..");
		var setNode = xmlDOM.selectNodes(upToSetPath);
		var nodeObjPro ;
		try
		{
			nodeObjPro = setNode[0].getAttribute('reference');
		}
		catch(e)
		{
//			alert("EXCEPTION CODE 1 :NODE HAS NOT REFERENCE ATTRIBUTE.\nIn function getReferencePathForNormalTypeNode");
			nodeObjPro = null;
		}
		if(nodeObjPro != null)
		{
//			alert("AS IT IS ");
			upToSetPath = upToSetPath +nodeObjPro;
		}
		var flagForSetPresent  = false;
		try 
		{
			flagForSetPresent = isSetTagPresent(xmlDOM,upToSetPath)	
		}
		catch(e)
		{
			//return -1;
		}
		if(flagForSetPresent)
		{
//			alert('APPENDING SET TAG');
			upToSetPath = upToSetPath +"/set";
		}
//		alert ("upToSetPath :"+upToSetPath);
		var tempNode1 = xmlDOM.selectNodes(upToSetPath);
//		alert(tempNode1);
//		alert(tempNode1.length);

		
		var childNodeName = tempNode1[0].childNodes;
//		alert(childNodeName);
		//	there is no object in set like "<set/>"
		
//		alert ("LENGT OF CHILD NODE GIVEN SET :"+childNodeName.length)
		return childNodeName.length ;
	}
	function getChildNodeLengnthOfGivenSetNew(xmlDOM,upToSetPath)
	{
//		alert ("function getLengnthOfGivenSet called..");
//		alert ("BEFORE upToSetPath :\n"+upToSetPath);
		upToSetPath = "/"+xmlDOM.nodeName+"/"+upToSetPath;
		upToSetPath = getFullPath(xmlDOM,upToSetPath);
//		alert ("AFTER  upToSetPath :\n"+upToSetPath);

		var tempNode1 = xmlDOM.selectNodes(upToSetPath);
//		alert (tempNode1.length);
//		alert (tempNode1[0].length);
		
		var childNodeName = tempNode1[0].childNodes;
//		alert ("LENGT OF CHILD NODE GIVEN SET :"+childNodeName.length)
		return childNodeName.length ;
	}
	
	function getDateAndTimeFromDateObj (dateObj)
	{
	    var returnArray = new Array();
	    if(dateObj == null || dateObj == "")
	    {
	     returnArray[0] = ""; 
	     returnArray[1] = ""; 
	    }
	    else
	    {
			var array = dateObj.split (" ");
			
			var date = array[0];
		
			var dateArray =  date.split ("-");
			var dateStr = dateArray[2]+"/"+dateArray[1] +"/"+dateArray[0]; 
			returnArray[0] = dateStr; 
			
		   if(array[1] != null && array[1] != "")
		   {	
				var time = array[1];
			
				var timeArray = time.split(":"); 		
				var timeStr = timeArray[0]+":"+timeArray[1];
				returnArray[1] = timeStr;
			}
			else
			{
			 	returnArray[1] = "";
			}
		}
		return returnArray;
	}
	
	function deleteFromDuplicateArray(tableName,rowId)
	{
		var value="";
		// alert('value  '+arrMulti[tableName][rowId]);
		arrMulti[tableName][rowId]=null;
	}
	
	function deleteFromDuplicateArrayByValue(tableName,value)
	{
		for(x in arrMulti[tableName])
		{
			if(value==arrMulti[tableName][x])
			{
				arrMulti[tableName][x] = null;
			//	alert('deleted');
				break;
			}
		}
	}

	function verifyDuplicate(tableName,elementNameArray,hiddenField,duplicateAlert,cnt)
	{
		//alert('rowToUpdate'+rowToUpdate);
		
		if(cnt)
			incr = cnt; 
		else
			incr = counter;
			
		var value="";
		var	rowId ;
		for(var y=0;y<elementNameArray.length;y++)
		{
			value=value+trim(document.getElementsByName(elementNameArray[y])[0].value);
			if(y<elementNameArray.length-1)
			{
				value=value+",";
			}
		}
		// alert("value:::"+value);
		if(arrMulti[tableName])
		{
			// alert('1st array');
			var isPresent = false;
			for(x in arrMulti[tableName])
			{
				// alert(x);
				if(value==arrMulti[tableName][x])
				{
					//alert('value present');
					if(duplicateAlert!=null)
					{
						alert(duplicateAlert);
					}
					else
					{
						var duplicateRecord=cmnLblArray[6];
						if(duplicateRecord)
						{
							alert(duplicateRecord);
						}
						else
						{
							alert('Similar records already exist.Please verify your data');
						}
					}
					isPresent=true;
					return false;
				}
			}
			if(isPresent==false)
			{
				if(rowToUpdate!=null)
				{	//alert('rowToUpdate111   '+rowToUpdate);
					//alert("Value ::::is"+value);
					arrMulti[tableName][rowToUpdate]=value;
					rowToUpdate = null;
					return true;
				}
				else
				{
					rowId = 'row' + hiddenField + incr;
				//	alert('row id 2: '+rowId);
					arrMulti[tableName][rowId]=value;
					return true;
				}
			}
		}
		else
		{
			//rowId = 'row' + hiddenField + 1;
			rowId = 'row' + hiddenField + incr;
			// alert('row id 1: '+rowId);
			arrMulti[tableName]=new Array();
			arrMulti[tableName][rowId]=value;
			return true;
		}
	}
	
function sendAjaxRequestForView(rowId, methodName) 
{
		//	alert('sendAjaxRequestForEdit called....');
		showProgressbar("Please Wait...");
		
//		updateRow = rowId;
		var hField = rowId.substring(3, rowId.length);
		//	alert(hField);
		var xmlFileName = document.getElementById(hField).value;
		//	alert('xmlFileName'+xmlFileName);
		if(isAllreadyAddedVOFileName(xmlFileName))
		{
			flagForUpdatedVO = true;		
		}
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) 
		{
		  hideProgressbar();
		  alert ("Your browser does not support AJAX!");
		  return;
		} 		
		
		var url='ifms.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
		methodName = methodName + "()";
		
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4) {
				eval(methodName);
				hideProgressbar();
			 }
		 }
		
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(null);	
}

	/*
	 * Function return values as array for array type child nodes.
	 * 
	 */

	function getXPathValueFromDOMForArray(xmlDOM, propertyXPath)
	{
//		alert ("getValueFromDOM(xmlDOM, propertyName) called...");
//		alert ("xmlDOM.nodeName :"+xmlDOM.nodeName);
		var nodeStr = "/"+xmlDOM.nodeName+"/"+propertyXPath;
//		alert("nodeStr  :"+nodeStr);
		nodeStr = getFullPath(xmlDOM,nodeStr);
//		alert("FULL PATH RETURN FROM getFullPath 1"+nodeStr);
		
		
		if(nodeStr == null)
		{
//			alert("RETURN NULL FROM  getValueFromDOM");
			return null;			
		}
		var nodeTemp = xmlDOM.selectNodes(nodeStr);
		//alert(nodeTemp.nodeName);
		//alert(nodeTemp[0].nodeValue);
		//alert(nodeTemp.length);
		var finalNodeValue ;
		try 
		{
//			alert(nodeTemp[0].childNodes.length);
			finalNodeValue = nodeTemp;
		}
		catch(e)
		{
//			alert ("ERROR CODE 2 : NODE HAVE NOT A VALUE ");
			finalNodeValue = null;
		}
//		showAlert("finalNodeValue :"+finalNodeValue);
	return finalNodeValue;		
	}	

	function getValueForFF (xmlDOM,nodeStr)
	
	{
		var xpe = new XPathEvaluator();
		//alert("xpe : "+xpe);
		var nsResolver = xpe.createNSResolver(xmlDOM.ownerDocument == null ? xmlDOM.documentElement : xmlDOM.ownerDocument.documentElement);
		var nodeTemp = xpe.evaluate(nodeStr, xmlDOM, nsResolver, XPathResult.ANY_TYPE,null);
		var result=nodeTemp.iterateNext();
	
		var finalNodeValue = null ;
		try 
		{
			//finalNodeValue = nodeTemp[0].childNodes[0].nodeValue;
			finalNodeValue = result.childNodes[0].nodeValue;
		}
		catch(e)
		{
			showAlert ("ERROR CODE 2 : NODE HAVE NOT A VALUE ");
			showAlert (e);
			
		}
		showAlert ("FINAL NODE VALUE :"+finalNodeValue);
		return finalNodeValue ;
	}

	function convertXpathForFFIndexBase (nodeStr)
	{
		var arr = nodeStr.split ('[') ; 
		if (arr.length == 1)
		{
			return nodeStr;
		}
		var returnStr = null;
		for (var i = 0;i < arr.length;i++)
		{
//			alert (arr[i]);
			if (i == 0)
			{
				returnStr = arr[i] ;
			}
			else
			{
				var c1 = arr[i].indexOf ("]");
				var index1= arr[i].substring (0,c1);
				//alert ("index1 :"+index1);
				var index1Inc = eval (index1) + 1 ;

				var p2 = arr[i].substring (c1);
				//alert (p2);
				var t1 ="[" +index1Inc + p2 ;
				returnStr = returnStr +  t1;
			}
		}
	//alert (returnStr);
	return returnStr ;
	}

