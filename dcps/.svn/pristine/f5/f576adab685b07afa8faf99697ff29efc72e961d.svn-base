<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>

<script>	
var counter=new Number();
var recordCount=new Number();
recordCount=0;
var editDBFlag=new Number();
var changeFlag=new Number();
var updateRow = null,deleteRowId=null;

function selectedIndexOfCombo(component,value)
{
	for (i=0;i<component.options.length;i++)
	{
		if(component.options[i].value==value)
		{
		 	component.options[i].selected=true;
			return true;
		}
	}
} 





	function addOrUpdateRecordAcr(methodName, actionFlag, fieldArray, progressBarFlag) 
	{	
		
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) 
		{
			alert ("Your browser does not support AJAX!");
		  	return;
		} 
		if(progressBarFlag != false)
		{
			showProgressbar("Please Wait...");	
		}
		
		var reqBody = getRequestBodyAcr(fieldArray);	
		
		var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		xmlHttp.onreadystatechange=function() 
		{
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
	
function getRequestBodyAcr(fieldArray)
	{
		
		var aParams = new Array();
		for(var i = 0; i < fieldArray.length; i++)
		{
			var sParam = parent.document.getElementById(fieldArray[i]).name;
			sParam += "=";
			if(parent.document.getElementById(fieldArray[i]).type == 'radio') 
			{
				var rdo = parent.document.getElementsByName(fieldArray[i]);
				for(var j = 0; j < rdo.length; j++) 
				{
					if(rdo[j].checked) 
					{
						sParam += rdo[j].value;	
						aParams.push(sParam);		
					}
				}			
			}
			else if(parent.document.getElementById(fieldArray[i]).type == 'select-multiple')
			{
				var lstbox = parent.document.getElementById(fieldArray[i]);
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
				var elementArr = parent.document.getElementsByName(fieldArray[i]);				
				for(var j = 0; j < elementArr.length; j++) 
				{					
					var temp = elementArr[j].name+"=";
					temp += elementArr[j].value;
					aParams.push(temp);
				}			
			}
		}
		return aParams.join("&");
	
	}	
	
	
	
	
function addRecord() 
{
	  if (xmlHttp.readyState == 4)
	  { 		
		var displayFieldArray = new Array('selectDesgn','selectAuthority','GoalEn', 'GoalGu','year');
		addDataInTableAcr('txnAdd', 'encXML', displayFieldArray,'editRecordAcr','deleteRec','');	
		recordCount=recordCount+1;			
   	    resetDataAcr();   			
	   }	
}
function recCountInc(){recordCount=recordCount+1;}

function addDataInTableAcr(tableId, hiddenField, displayFieldArray, editMethodName, deleteMethodName, viewMethodName) 
{
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
			var field = window.parent.document.getElementById(displayFieldArray[i]);
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
				var radio=window.parent.document.getElementsByName(displayFieldArray[i]);
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
				var radio=window.parent.document.getElementsByName(displayFieldArray[i]);
				var displaystring = "";
				for(var j = 0; j < field.options.length; j++)
				{
					if(field.options[j].selected && field.options[j].value !='-1' )
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
	
	
function resetDataAcr() 
{	
		
		window.parent.document.getElementById('GoalGu').value = '';
		window.parent.document.getElementById('GoalEn').value = '';
		//window.parent.document.forms[0].selectDesgn.value = '';
		parent.document.getElementById("oldYear").value=0;
		parent.document.getElementById("yearTD").style.display='';
		parent.document.getElementById("oldYearTD").style.display='none';
		window.parent.document.forms[0].selectAuthority.value = '-1';
		window.parent.document.forms[0].year.value = '-1';
}	
function deleteFormAcr()
{
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200 && deleteRowId!=null) 
	{
		var decXML = xmlHttp.responseText;
		var xmlDOM = getDOMFromXML(decXML);
		var updateCodeValue = getXPathValueFromDOM(xmlDOM,'updatedCode');
		updateRow = null;
		var answer = deleteRecord(deleteRowId);
		if( answer )
		{				
			recordCount=recordCount-1;					
			if(updateCodeValue!=0)
			{
				if(window.parent.document.forms[0].deleteData.value=="")
				{
					window.parent.document.forms[0].deleteData.value=updateCodeValue;
				}
				else
				{				
					window.parent.document.forms[0].deleteData.value = window.parent.document.forms[0].deleteData.value+"/"+updateCodeValue;
				}
			}			
			/*if( recordCount==0 )
			{
				window.parent.document.getElementById('submitButton').disabled=true;
				window.parent.document.getElementById('pointTableDiv').style.display = 'none';
			}*/
		}
		deleteRowId=null;
	}
}
function deleteRec(rowId)
{	
	deleteRowId = rowId;
	sendAjaxRequestForEdit(rowId,'deleteFormAcr',true);
}

function updateRecord() 
{
	if (xmlHttp.readyState == 4) 
	{ 	
		var displayFieldArray = new Array('selectDesgn','selectAuthority','GoalEn', 'GoalGu');
		if(parent.document.getElementById("oldYear").value!=0)
		{
			displayFieldArray.push("yearLabel");	
		}	
		else
		{
			displayFieldArray.push("year");	
		}	
		if(editDBFlag==-1)
		{
			updateDataInTableAcr('encXML', displayFieldArray);		
		}
		else if(editDBFlag==1)
		{
			updateDataInTableAcr('addedPunch', displayFieldArray);		
		}
		resetDataAcr();
		editDBFlag=0;
		parent.document.getElementById('btnAdd').disabled=false; 	
		parent.document.getElementById('updateButton').disabled=true;   	
	}
}

function updateDataInTableAcr(hiddenField, displayFieldArray) 
{
		var trow = document.getElementById(updateRow);
		var hFieldId = updateRow.substring(3, updateRow.length);
		//	MAKING IT NULL BECAUSE IT WILL USED TO IDENTIFY IF
		//	ANY OTHER MULTIPLE EDIT IS OPEN IN EDIT MODE.
		//  CHANGE BY 202414
		updateRow = null;
		if(flagForUpdatedVO)
		{
			//	HERE ADDED "_U" TO INDICATE THIS RECORD IS UPDATED BY USER. 
			document.getElementById(hFieldId).value = xmlHttp.responseText +"_U";
			flagForUpdatedVO = false;
		}
		else
		{
			document.getElementById(hFieldId).value = xmlHttp.responseText;
		}
		trow.cells[0].style.display = 'none';
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = parent.document.getElementById(displayFieldArray[i]);
			if(field.type == 'select-one')
			{				
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
				var radio=parent.document.getElementsByName(displayFieldArray[i]);
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
				var radio=parent.document.getElementsByName(displayFieldArray[i]);
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

function editRecordAcr(rowId) 
{	
	window.parent.document.getElementById('submitButton').disabled=true;	
	editDBFlag=-1;
	sendAjaxRequestForEdit(rowId,'populateFormAcr',true);
}

function populateFormAcr() 
{
	if (xmlHttp.readyState == 4) 
	{ 
		if (xmlHttp.status == 200) 
		{	
			var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML,'encXML');
			parent.document.forms[0].selectAuthority.value=getXPathValueFromDOM (xmlDOM, 'authSet');
			//parent.document.forms[0].year.value=getXPathValueFromDOM (xmlDOM, 'year');
			var h1 = getXPathValueFromDOM (xmlDOM, 'year');
			var flag = selectedIndexOfCombo(parent.document.forms[0].year,h1);
			if(flag!=true)
			{
				parent.document.getElementById("oldYear").value = h1;
				parent.document.forms[0].year.value='-1';
				++h1;
				parent.document.getElementById("yearLabel").value=parent.document.getElementById("oldYear").value+"-"+h1;
				parent.document.getElementById("oldYearTD").style.display='';
				parent.document.getElementById("yearTD").style.display='none';
			}
			else 
			{
				parent.document.getElementById("oldYear").value=0;
				parent.document.getElementById("yearTD").style.display='';
				parent.document.getElementById("oldYearTD").style.display='none';
			}
			parent.document.getElementById('GoalGu').value = getXPathValueFromDOM(xmlDOM,'goalGu'); 
			parent.document.getElementById('Goalen').value = getXPathValueFromDOM(xmlDOM,'goalEn');
			parent.document.getElementById('updateCode').value = getXPathValueFromDOM(xmlDOM,'updatedCode');
			var desgSetlen=getChildNodeLengnthOfGivenSet(xmlDOM, 'desgSet');
			for(var j=0;j<desgSetlen;j++)
			{					
				var parentNode='desgSet['+j+']';
				var h1=getXPathValueFromDOM(xmlDOM, parentNode);
				selectedIndexOfCombo(parent.document.forms[0].selectDesgn,h1);
			}
			parent.document.getElementById('btnAdd').disabled=true; 	
			parent.document.getElementById('updateButton').disabled=false;   	    		   		    
		}
	}
}
function submitIFrame()
{
	var	encXMLArray = new  Array();
	var encXMLLength=document.getElementsByName('encXML').length;
	for(i=0;i<encXMLLength;i++)
	{
		parent.document.getElementById('hiddenField').value=document.getElementsByName('encXML')[i].value+"~"+parent.document.getElementById('hiddenField').value;
	}
	return(encXMLArray);
}


</script>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<hdiits:form name="pointTable"  method="POST" validate="false" action="./hrms.htm?actionFlag=submitAcrMasterPoints" encType="multipart/form-data">
<table id="txnAdd" border="1" bgcolor="white" borderColor="BLACK" style="border-collapse: collapse;" width="100%"> 
						<tr bgcolor="#C9DFFF">
							<td class="fieldLabel" width="10%">
	    						<b>
	    							<hdiits:caption captionid="HR.ACR.selectedDesignation" bundle="${commonLables}" />
	    						</b>
							</td>
							<td class="fieldLabel" width="10%">
	    						<b>
	    							<hdiits:caption captionid="HR.ACR.selectedAuthority" bundle="${commonLables}" />
	    						</b>
							</td>
							
	    					<td class="fieldLabel" width="30%">
	    						<b><hdiits:caption captionid="HR.ACR.PointEnglish" bundle="${commonLables}" />
	    						</b>
							</td>
	    					<td class="fieldLabel" width="30%">
	    						<b><hdiits:caption captionid="HR.ACR.PointGujarati" bundle="${commonLables}" />
	    						</b>
							</td>
							
							<td class="fieldLabel" width="10%">
	    						<b>
	    							<hdiits:caption captionid="HR.ACR.selectedYear" bundle="${commonLables}" />
	    						</b>
							</td>
							<td class="fieldLabel" width="10%" >
								<b>
									<hdiits:caption captionid="HR.ACR.EditDelete" bundle="${commonLables}" />
								</b>
							</td>  
	    				</tr>
</table>
</hdiits:form>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>