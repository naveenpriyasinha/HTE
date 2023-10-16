var counter = 1;
var updateRow;

function addOrUpdateRecord(methodName, actionFlag, fieldArray) {	
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	} 

	var reqBody = getRequestBody(fieldArray);
	//alert('reqBody: ' + reqBody);
	var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
	methodName = methodName + "()";
	xmlHttp.onreadystatechange=function() { eval(methodName); }	

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
		
		if(document.getElementById(fieldArray[i]).type == 'radio') {
			var rdo = document.getElementsByName(fieldArray[i]);
			for(var j = 0; j < rdo.length; j++) {
				if(rdo[j].checked) {
					sParam += rdo[j].value;	
				}
			}			
		}
		else {		
			sParam += document.getElementById(fieldArray[i]).value;
		}
		
		aParams.push(sParam);		
	}
	
	return aParams.join("&");
}

function addDataInTable(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName,rowCounter) {
	//alert('displayFieldArray:' + displayFieldArray.length);
	
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
	counter=rowCounter;
	//trow.id = 'row' + counter;
	trow.id = 'row' + hiddenField + counter;
	
	//trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + counter + "' value='" + xmlHttp.responseText + "'/>";				
	trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" + xmlHttp.responseText + "'/>";				
	trow.cells[0].style.display = 'none';
	
	var len = displayFieldArray.length;			
	for(var i = 0; i < len; i++) {
		var field = document.getElementById(displayFieldArray[i]);
		//alert(field.type);
		if(field.type == 'select-one') {
			trow.insertCell(i+1).innerHTML = field.options[field.selectedIndex].text;	
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
		
	if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
											 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
											 "')>Edit</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>Delete</a>";			
	
	else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +
											 "')>Edit</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>Delete</a>";										
											 
	else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
											 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "')>Edit</a>";
											 
	else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + 
											 "')>View</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>Delete</a>";			
	
	else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>Delete</a>";
		
	else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "')>Edit</a>";		
		
	else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
		trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "')>View</a>";
		
	counter++;	
}

function updateDataInTable(hiddenField, displayFieldArray) {
	var trow = document.getElementById(updateRow);				
	//var hFieldId = updateRow.substring(updateRow.length-1, updateRow.length);					
	var hFieldId = updateRow.substring(3, updateRow.length);				
	trow.cells[0].innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hFieldId + "' value='" + xmlHttp.responseText + "'/>";					
	trow.cells[0].style.display = 'none';
	
	var len = displayFieldArray.length;	
	for(var i = 0; i < len; i++) {
		var field = document.getElementById(displayFieldArray[i]);
		//alert(field.type);
		if(field.type == 'select-one') {
			trow.cells[i+1].innerHTML = field.options[field.selectedIndex].text;	
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
		else {
			trow.cells[i+1].innerHTML = field.value;	
		}
	}			
}

function deleteRecord(rowId) {
	var answer = confirm("Are you sure you want to delete this record?");
	if(answer) {	
		var delRow = document.getElementById(rowId);
		//alert(delRow.rowIndex);
		delRow.parentNode.deleteRow(delRow.rowIndex);			
	}
	return answer;
}

function getDOMFromRowXML(rowId) {
	updateRow = rowId;
	//var hField = rowId.substring(rowId.length-1, rowId.length);	
	var hField = rowId.substring(3, rowId.length);
	var encXML = document.getElementById(hField).value;	
	
	var decXML = decodeBase64(encXML);
	
	return getDOMFromXML(decXML);
}

function getDOMFromXML(xmlString) {	
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
	
	return doc.documentElement;		
}

function getValueFromDOM(domObj, propertyName) {
    var entries = domObj.getElementsByTagName('void');		 
	var propName;
	    
	for (var i = 0; i < entries.length; i++)  {    			
		propName = entries[i].getAttribute('property');
		if(propName == propertyName) {
			//alert('propName:' + propName);
			//alert('value:' + entries[i].childNodes[0].text);
			return entries[i].childNodes[0].text;
		}
	}
	return null;
}

function sendAjaxRequestForEdit(rowId, methodName) {
	updateRow = rowId;
	var hField = rowId.substring(3, rowId.length);	
	var xmlFileName = document.getElementById(hField).value;	
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
		
	var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
	methodName = methodName + "()";
	xmlHttp.onreadystatechange=function() { eval(methodName); }

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
function getValueFromDOMByName(domObj,propertyType ,propertyName) {
    var entries = domObj.getElementsByTagName('void');		 
	var propName;
	var propType;
	var j=0;
	var value="";
	for (var i = 0; i < entries.length; i++)  {    			
		propType = entries[i].getAttribute('property');
		if(propType == propertyType) {
			for(j=0; j< entries[i].childNodes.length; j++){
				   for(k=0;k<entries[i].childNodes[j].childNodes.length;k++){
			           propName=entries[i].childNodes[j].childNodes[k].getAttribute('property');
			           if(propName == propertyName)
			           {value=entries[i].childNodes[j].childNodes[k].text;
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
	for (var i = 0; i < entries.length; i++)  {    			
		propName = entries[i].getAttribute('property');
		
		if(propName == propertyName) {
		     
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
