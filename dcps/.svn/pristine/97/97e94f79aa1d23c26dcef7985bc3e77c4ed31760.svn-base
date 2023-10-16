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
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert ("Your browser does not support AJAX!");
		  return;
		} 
		if(progressBarFlag != false)
		{
			showProgressbar("Please Wait...");	
		}
				
		var reqBody = getRequestBody(fieldArray);	
		var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		
		xmlHttp.onreadystatechange=function() {
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


	function getRequestBody(fieldArray)
	{
		var aParams = new Array();
		for(var i = 0; i < fieldArray.length; i++)
		{
			var sParam = document.getElementById(fieldArray[i]).name;
			sParam += "=";

			if(document.getElementById(fieldArray[i]).type == 'radio') 
			{
				var rdo = document.getElementsByName(fieldArray[i]);
				for(var j = 0; j < rdo.length; j++) 
				{
					if(rdo[j].checked) 
					{
						sParam += rdo[j].value;	
						aParams.push(sParam);		
					}
				}			
			}
			else if(document.getElementById(fieldArray[i]).type == 'select-multiple')
			{
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
			else 
			{				
				var elementArr = document.getElementsByName(fieldArray[i]);				
				for(var j = 0; j < elementArr.length; j++) 
				{					
					var temp = elementArr[j].name+"=";
					temp += elementArr[j].value;
					aParams.push(temp);
				}			

				//sParam += document.getElementById(fieldArray[i]).value;
				//aParams.push(sParam);		
			}
		}
		return aParams.join("&");
	}

	function addDataInTable(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName) 
	{
//		alert ("addDataInTable called...");
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
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" + xmlHttp.responseText + "'/>";				
		trow.cells[0].style.display = 'none';
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
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
			else if(field.type == 'select-multiple')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				//alert(field.type);
				//alert(field.options.length);
				var displaystring = "";
				for(var j = 0; j < field.options.length; j++)
				{
					if(field.options[j].selected && field.options[j].value !='-1' )
					{
						//alert(j);
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
		
		
		var trow=document.getElementById(tableId).insertRow();
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

	function deleteRecord1(rowId) 
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
			delRow.parentNode.deleteRow(delRow.rowIndex);		
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
		//	alert('sendAjaxRequestForEdit called....');
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
		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
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
		var finalNodeValue ;
		try 
		{
			finalNodeValue = nodeTemp[0].childNodes[0].nodeValue;
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
//		alert ("isSetTagPresent(xmlDOM, nodeStr) called.");
//		alert (" nodeStr :"+nodeStr );
		var node = xmlDOM.selectNodes(nodeStr);
		var nodeObjName = null;
		try
		{
			nodeObjName = node[0].childNodes[0].nodeName;
		}
		catch(e)
		{
//			alert ("EXCEPTION THROWN");
			throw e;			
		}
		if(nodeObjName == "set")
		{
//			alert ("return true ");
			return true;
		}
//	alert ("return false");
	return false;		
	}
	
	function deleteDBRecord1(rowId)
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
			document.getElementById('save').style.display='';
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
//		alert ("getFullPath () called.");
		var returnPath ;
		var pathElArray = argPath.split ("/");
//		alert ("pathElArray.length :"+pathElArray.length );
		var flagForNullValue = false; 
		for (var i = 0;i < pathElArray.length;i++)
		{
//			alert ("IN FOR LOOP i"+i+" : "+ pathElArray[i]);
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
//				alert ("current node is set type");
				var temp = getReferencePathForSetTypeNode(xmlDOM,returnPath);
				if(temp == null)
				{
					flagForNullValue = true;
//					alert ("flagForNullValue SET TO true");
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

		var node = xmlDOM.selectNodes(nodeStr1);
		var nodeObjPro ;
		try
		{
			nodeObjPro = node[0].getAttribute('reference');
		}
		catch(e)
		{
//			alert("EXCEPTION CODE 1 :NODE HAS NOT REFERENCE ATTRIBUTE.");
			nodeObjPro = null;
		}
		
		if(nodeObjPro != null)
		{
			nodeObjPro = getRefXPathAccoIE(nodeObjPro);
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
		var tempNode1 = xmlDOM.selectNodes(nodeStr1);
		
		var childNodeName = tempNode1[0].childNodes;
		// there is no object in set like "<set/>"
		if(childNodeName.length == 0)
		{
			showAlert("ERROR CODE 2: THERE IS NO OBJECT IN SET ");
			return null;
		}
		var setObjName = tempNode1[0].childNodes[0].nodeName;
		showAlert("SET OBJECT NAME :"+setObjName);
		nodeStr1 = nodeStr1 +"/"+setObjName ;
		var flagForIndexAppend = false;
		
		nodeStr1 = nodeStr1 + argNodeStr.substring (indexOfOpnSqr);
//		alert ("go 4 ref \n"+nodeStr1);
		var nodeStrTemp = getReferencePathForNormalTypeNode(xmlDOM,nodeStr1)
//		alert ("nodeStrTemp  :"+nodeStrTemp );
		if(nodeStr1 == nodeStrTemp)
		{
//			alert ("nodeStr1 == nodeStrTemp ");
		}
		else
		{
//			alert ("nodeStr1 != nodeStrTemp");
			nodeStr1 = nodeStrTemp;
		}
		showAlert("nodeStr1:	"+nodeStr1);
	return nodeStr1;	
	}

	function getReferencePathForNormalTypeNode(xmlDOM,argNodeStr)
	{
//		alert ("getReferencePathForNormalTypeNode called..");
//		alert ("argNodeStr :"+argNodeStr);
		var tempNode1 = xmlDOM.selectNodes(argNodeStr);
//		alert("tempNode1");
//		alert(tempNode1);
		var nodeObjPro ;
		try
		{
			nodeObjPro = tempNode1[0].getAttribute('reference');
		}
		catch(e)
		{
//			alert("EXCEPTION CODE 1 :NODE HAS NOT REFERENCE ATTRIBUTE.\nIn function getReferencePathForNormalTypeNode");
			nodeObjPro = null;
		}
		
		if(nodeObjPro == null)
		{
//			alert("AS IT IS ");
			return argNodeStr ;
		}
//		alert ("RETURN NODE STRING + REFERENCE ATTRIBUTE");
		nodeObjPro = getRefXPathAccoIE(nodeObjPro);
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
		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
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
