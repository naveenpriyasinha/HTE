
var listCounter=0;
var addedList=new Array();
function addDataInTableElement(tableId, displayFieldArray,deleteMethodName,editMethodName, hdnFieldValue, isDisable) 
{
	 
	if(deleteMethodName == undefined) 
	{
		deleteMethodName = '';
	}
	
	if(editMethodName == undefined) 
	{
		editMethodName = '';
	}
	if(isDisable)
	{
		addedList=new Array();
		listCounter=0;
	}
	
	addedList[listCounter]=displayFieldArray[0];
	
	document.getElementById('addedList').value="";
	var elementIdStr="";
	for(var i=0;i<addedList.length;i++)
	{
		elementIdStr = elementIdStr+","+addedList[i];
	}
	document.getElementById('addedList').value=elementIdStr.substring(1,elementIdStr.length);
	
	document.getElementById(tableId).style.display='';
	var trow=document.getElementById(tableId).insertRow();
	trow.id = "rowId"+displayFieldArray[0];
	
	trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='hdnFlag"+trow.id+"' id='hdnFlag"+trow.id+"' value='"+hdnFieldValue+"'/>" +
								   "<INPUT type='hidden' name='hdnPerm"+displayFieldArray[0]+"' id='hdnPerm"+displayFieldArray[0]+"' value='"+displayFieldArray[2]+"'/>";
	trow.cells[0].style.display = 'none';
	
	trow.insertCell(1).innerHTML = "<INPUT type='hidden' name='"+tableId+"hdnElementCode' id='"+tableId+"hdnElementCode' value='"+displayFieldArray[0]+"'/>";				
	trow.cells[1].style.display = 'none';
	
	
	trow.insertCell(2).innerHTML =displayFieldArray[0];
	
	trow.cells[2].align = 'center';				
	trow.cells[2].style.display = '';
	
	var treeSpace="";
	for(var j=0; j<displayFieldArray[7]; j++)
	{
		treeSpace = treeSpace + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	}
	
	//treeSpace = treeSpace +"1.&nbsp;";
	
	if(displayFieldArray[4]!="" && isDisable && displayFieldArray[4] == 'SCREEN')
	{
		trow.insertCell(3).innerHTML = treeSpace + '<a href=javascript:void("moveToNextTab") onclick="moveToNextTab(\''+displayFieldArray[4]+'\' , ' + displayFieldArray[0] + ' , ' +displayFieldArray[2] + ')">'+displayFieldArray[1]+'</a>';
	}
	else
	{
		trow.insertCell(3).innerHTML = treeSpace + displayFieldArray[1];
	}
	
	//trow.cells[3].align = 'center';				
	trow.cells[3].style.display = '';
	
	var permissionString = "";
	if(displayFieldArray[5]=='1')
	{
		permissionString="<INPUT type='radio' name='permission"+trow.id+"' value='1' "+ (displayFieldArray[2]==1?"checked":"") +" "+ (isDisable?"DISABLED":"") +" class='radiobuttontag' />"+aclElementMpgLables[0]+
		 				 "<INPUT type='radio' name='permission"+trow.id+"' value='5' "+ (displayFieldArray[2]==5?"checked":"") +" "+ (isDisable?"DISABLED":"") +" class='radiobuttontag' />"+aclElementMpgLables[2]+
						 "<INPUT type='radio' name='permission"+trow.id+"' value='2' "+ (displayFieldArray[2]==2?"checked":"") +" "+ (isDisable?"DISABLED":"") +" class='radiobuttontag' />"+aclElementMpgLables[1];
						 
	}
	else if(displayFieldArray[5]=='5')
	{
		permissionString="<INPUT type='radio' name='permission"+trow.id+"' value='5' "+ (displayFieldArray[2]==5?"checked":"") +" "+ (isDisable?"DISABLED":"") +" class='radiobuttontag' />"+aclElementMpgLables[2]+
						"<INPUT type='radio' name='permission"+trow.id+"' value='2' "+ (displayFieldArray[2]==2?"checked":"") +" "+ (isDisable?"DISABLED":"") +" class='radiobuttontag' />"+aclElementMpgLables[1];
						 
	}
	else if(displayFieldArray[5]=='2')
	{
		permissionString="<INPUT type='radio' name='permission"+trow.id+"' value='2' "+ (displayFieldArray[2]==2?"checked":"") +" "+ (isDisable?"DISABLED":"") +" class='radiobuttontag' />"+aclElementMpgLables[1];
	}
	
	trow.insertCell(4).innerHTML = permissionString;
	trow.cells[4].align = 'center';								   
	trow.cells[4].style.display = '';
	
	trow.insertCell(5).innerHTML = "<a id='editLink"+ trow.id +"' href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','"+tableId+"')>"+aclElementMpgLables[3]+"&nbsp;/</a> " + 
								   "<a id='resetLink"+ trow.id +"' href=javascript:void('reset') onclick=javascript:resetRecord('" + trow.id + "')>"+aclElementMpgLables[5]+"&nbsp;/</a> " +
								   "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "','"+tableId+"')>"+aclElementMpgLables[4]+"</a>";																				
	trow.cells[5].align = 'center';								   
	trow.cells[5].style.display = '';
	
	if(hdnFieldValue=='N')
	{
		document.getElementById("editLink"+ trow.id +"").style.display = 'none';
	}
	else
	{
		document.getElementById("resetLink"+ trow.id +"").style.display = 'none';
	}
	
	if(displayFieldArray[6]=='-1')
	{
		trow.style.backgroundColor = "#DCDCDC";
	}
	
	
	listCounter++;
	return trow.id;
}
function editDBRecord(rowId,tableName)
{	
	document.getElementById("editLink"+ rowId +"").style.display = 'none';
	document.getElementById("resetLink"+ rowId +"").style.display = '';
	if(tableName=='ModuleTable')
	{
		document.form1.Module_Save.disabled = false;
	}
	else if(tableName=='ScreenTable')
	{
		document.form1.Screen_Save.disabled = false;
	}
	else if(tableName=='FieldTable')
	{
		document.form1.Field_Save.disabled = false;
	}
	eval("document.getElementById(\"hdnFlag"+rowId+"\").value='U'");
	for(var i=0;i<eval(document.forms[0].name +".permission"+rowId+".length");i++)
	{
		eval("document."+ document.forms[0].name +".permission"+rowId+"[i].disabled=false");
	}
}

function resetRecord(rowId)
{
	var radioCount=eval(document.forms[0].name +".permission"+rowId+".length");
	var permission=parseInt(document.getElementById("permission"+rowId+"").value);
	if(permission == 1)
	{
		
		eval("document."+ document.forms[0].name +".permission"+rowId+"[0].checked=true");
	}
	else if(permission == 2)
	{
		if(radioCount==3)
		{
			eval("document."+ document.forms[0].name +".permission"+rowId+"[2].checked=true");
		}
		else if(radioCount==2)
		{
			eval("document."+ document.forms[0].name +".permission"+rowId+"[1].checked=true");
		}
		else
		{
			eval("document."+ document.forms[0].name +".permission"+rowId+"[0].checked=true");
		}
		
	}
	else if(permission == 5)
	{
		if(radioCount==3)
		{
			eval("document."+ document.forms[0].name +".permission"+rowId+"[1].checked=true");
		}
		else
		{
			eval("document."+ document.forms[0].name +".permission"+rowId+"[0].checked=true");
		}
	}
	var hdnVar = document.getElementById("hdnFlag"+rowId).value;
	if(hdnVar!='N')
	{
		for(var i=0;i<eval(document.forms[0].name +".permission"+rowId+".length");i++)
		{
			eval("document."+ document.forms[0].name +".permission"+rowId+"[i].disabled=true");
		}
		document.getElementById("resetLink"+ rowId +"").style.display = 'none';
		document.getElementById("editLink"+ rowId +"").style.display = '';
	
		eval("document.getElementById(\"hdnFlag"+rowId+"\").value='M'");
	}
}


function deleteDBRecordForElement(rowId,tableName)
{
	if(confirm(aclElementMpgAlerts[0]))
	{
		if(tableName=='ModuleTable')
		{
			document.form1.Module_Save.disabled = false;
		}
		else if(tableName=='ScreenTable')
		{
			document.form1.Screen_Save.disabled = false;
		}
		else if(tableName=='FieldTable')
		{
			document.form1.Field_Save.disabled = false;
		}
		
		if(document.getElementById("hdnFlag"+rowId).value=='N')
		{
			var delRow = document.getElementById(rowId);
			var codeArray=document.getElementById('addedList').value.split(',');
			for(var i=0;i<codeArray.length;i++)
			{ 
				if(codeArray[i]==delRow.cells[2].innerHTML)
				{
					codeArray.splice(i, 1);
					listCounter--;
				}
			}
			delRow.parentNode.deleteRow(delRow.rowIndex);
		}
		else
		{	
			document.getElementById(rowId).style.display='none';
			document.getElementById("hdnFlag"+rowId).value='D';
			//eval("document.frmPostRole.hdnFlag"+rowId+".value='D'");
			var delRow = document.getElementById(rowId);
			delRow.id=0;
		}
	}
	if(document.getElementById(tableName).rows.length==1)
	{
		document.getElementById(tableName).style.display='none';
	}
}


function moveToNextTab(screenName, eleCode, parentPerm, obj3) {
	
	if(screenName=='FIELD')
	{
		document.form1.parentCodeForField.value = eleCode;
		document.form1.parent_permForField.value=parentPerm;

	}else
	{
		document.form1.parentCode.value = eleCode;
		document.form1.parent_perm.value=parentPerm;
	}	
	CheckSelectedValues(screenName, eleCode, parentPerm);
	goToNextTab();

}
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

function insertElements()
{ 
	//showProgressbar();	
	
	
	/*xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	  {
	  alert ("Your browser does not support AJAX!");
	  return;
	  }*/ 
	  
	  var url=run(); 
	  var actionf=document.forms[0].actionFlag.value
	  var uri='hdiits.htm?actionFlag='+actionf;
	  url=uri + url;           
	
	
	/*xmlHttp.onreadystatechange=function ()
	{
		if(xmlHttp.readyState == 4)
		{
			if(xmlHttp.status == 200)
			{
				//hideProgressbar();
			}
		}
	};
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(url);*/
	
	new Ajax.Request(encodeURI(url),
	{
		method: 'POST',
		onSuccess: function(resXmlHttp)
		{
			//hideProgressbar();
		},
	    onFailure: alertOnFailureForMstScr,
	    asynchronous: false
	} );
}


function displayText(obj1){
	if(document.getElementById(obj1).value==""){
		document.getElementById(obj1).value=aclElementMpgLables[6];
		document.getElementById(obj1).style.color='#808080';
			}
}

function removeText(obj1){
	if(document.getElementById(obj1).value==aclElementMpgLables[6]){
		document.getElementById(obj1).value="";
		document.getElementById(obj1).style.color='black';
	}
}

