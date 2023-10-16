var eprofileRowNum=-1,eprofileShowAttachFlag=false;
var eprofileShowId,eprofileHideId;
var frmNameForAttachment='',eprofileAttachmentTableId='';
function eprofileSubmitMeFunction(formNameValue,url)	
{
    window.document.forms[formNameValue].action=url;
    window.document.forms[formNameValue].submit();
    endProcess();
}	
function eprofileCheckForNull(chkArr)
{
	for (var i=0;i<chkArr.length;i++)
	{
		if(chkArr[i]=='' || chkArr[i]=='null' || chkArr[i]==null)
		{
			chkArr[i]='-';
		}
	}
	return chkArr;
}
function eprofileHideAttachment(showId,hideId)
{
	document.getElementById(hideId).style.display='none';
	document.getElementById(showId).style.display='';
	document.getElementById(eprofileAttachmentTableId).style.display='none';
	eprofileShowAttachFlag=false;
}
function eprofileShowAttachment(trnSrno,attachmentName,frmName,trId,hideId,attachmentTableId)
{
	if(eprofileShowAttachFlag==true)
	{
		document.getElementById(eprofileHideId).style.display='none';
		document.getElementById(eprofileShowId).style.display='';
	}
	eprofileShowAttachFlag=true;
	eprofileShowId=hideId;
	eprofileHideId=trId;
	frmNameForAttachment=frmName;
	eprofileAttachmentTableId=attachmentTableId;
	removeRowFromTableattachmentBiometric(1,frmNameForAttachment);
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	}
	var attachmentString='&attachmentName='+attachmentName;
	var attachmentIdString='&attachmentId='+trnSrno;
	
	var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + ''+ attachmentString + attachmentIdString + '&rowNumber='+1;
		
	document.getElementById(hideId).style.display='none';
	document.getElementById(trId).style.display='';
	document.getElementById(eprofileAttachmentTableId).style.display='';
	//trow= document.getElementById(trId);
	//trow.cells[tdId].innerText="<a href=javascript:void('Hide') onclick=javascript:eprofileHideAttachment("+trnSrno+","+attachmentName+","+frmName+","+trId+","+tdId+")>Hide Attachment</a>";
	
//	alert('Eprofile  sendAjaxRequestForEditAttachment:url '+url);
//	methodName = methodName + "()";
//	xmlHttp.onreadystatechange=function() { eval(methodName); }
	xmlHttp.onreadystatechange=eprofilegetAttachment;
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);
}
function eprofilegetAttachment()
{
	if (xmlHttp.readyState == 4) 
  	{ 	
  		if (xmlHttp.status == 200) 
		{
			var decXML = xmlHttp.responseText;		
			populateAttachment(decXML,frmNameForAttachment);
		}
	}
}
function  eprofileAddDataInTableAttachment(tableId, hiddenField, displayFieldArray, 
			editMethodName, deleteMethodName, viewMethodName,attachmentIds)
{
		if (attachmentIds != 'null' && attachmentIds != null)
			attachmentIds = attachmentIds;
		else
			attachmentIds = "";
		
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
		
		/*if(document.getElementById("Incremented_rowNumber")==null)
		{
			var table = document.getElementById(tableId);
			table.rows[0].insertCell(len+1).innerHTML = "<INPUT type='hidden' name='Incremented_rowNumber' id='Incremented_rowNumber' value='1'/>";
			table.rows[0].cells[len+1].style.display = 'none';
		}

		var rowNumberForRow = document.getElementById("Incremented_rowNumber").value;*/
		var rowNumberForRow = eprofileRowNum;

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
		
		trow.insertCell(len + 2).innerHTML = "<INPUT type='hidden' name='attachment" + hiddenField + "' id='attachment" + hiddenField + counter + "' value='" + attachmentIds + "' />";		
		trow.cells[len + 2].style.display = 'none';	
		
		trow.insertCell(len + 3).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "_rowNumber' value='" + rowNumberForRow + "'/>";			
		trow.cells[len + 3].style.display = 'none';
		
		counter++;		
		
		return eprofileRowNum;
}
function eprofileMyCmbFieldValue(chkArr)
{
	for (var i=0;i<chkArr.length;i++)
	{	     	
		var val = document.getElementById(chkArr[i]).value;
		var field = document.getElementById(chkArr[i]);		
		if(field.type == 'select-one')
		{
			if(val=='Select' || val=='select' || val=='SELECT' || val=='' || val=='0')
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
			if(val=='' || val=='null' || val==null)
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
function eprofileHideObj(chkArr)
{
	document.getElementById(chkArr).style.display='none';
}

function checkNumberForOnlyOneDot(fieldValue)
{
	var num;
	num = window.event.keyCode;
	if ((eval(num)<46||eval(num)==47)||eval(num)>58)
	{
		return false;
	}	
	else
	{
	 	if (eval(num)==46)
	 	{
		 	var dotCount=0;
		 	var length = fieldValue.length;
		 	if (length == 0)
		 		return false;	
		 	for(var i=0;i<length;i++)
			{
				var keyId = fieldValue.charCodeAt(i);		
				if(keyId==46) 
				{
					dotCount=parseInt(dotCount)+1;
				}
			}
			if(dotCount>=1)
			{
				return false;
			}
		}
		return true;
	}
}

function getSubqualificationsLst(cmb)
{
	var id=cmb.value;											
	if(id=='' || id.search(/'Select'/i)!=-1) 
	{
		hideDiciplineTd();
		return;
	}	

	if(id=='null' || id==null || id=='edu_Higher_Secondary_School'  || id== 'edu_Secondary_School' || id=='edu_Primary_School' || id=='' || id=='Select')
	{
		hideDiciplineTd();
	}
	else
		document.getElementById('diciplineId').style.display='';
		
	var z=document.getElementById('SubQualification');
	z.length = 1;
	
	var disciplineCmb=document.getElementById('discipline');
	disciplineCmb.length = 1;
	
	var subQualificationActionflag="EducationDtls&flag=getSubqualification&cmbid="+id;
	sendAjaxRequestHRMS("processSubqualificationsLstResponse", subQualificationActionflag,'', false, "POST");
}			

function processSubqualificationsLstResponse()
{						
	if (hrmsXmlHttp.readyState == 4) 
	{   
		endProcess();  
		if (hrmsXmlHttp.status == 200) 
		{          				
			var textId;												
			var z=document.getElementById('SubQualification');			            		            			            	
			var xmlStr = hrmsXmlHttp.responseText;
			var XMLDoc=getDOMFromXML(xmlStr);   
			var SubStr = XMLDoc.getElementsByTagName('SubQualification');				    	
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

			if (workFlowEnabled) 
			{
				if(setCombo!=-1)
				{
					if(setCombo.search(/Select/i)==-1)
					{		           				
						document.getElementById('SubQualification').value = setCombo;
						getDisciplineLst(document.getElementById('SubQualification'));setCombo=-1;				           		
					}
				}	
			}
			else if(setCombo!=-1)
			{
				document.getElementById('SubQualification').value = setCombo;
				getDisciplineLst(document.getElementById('SubQualification'));setCombo=-1;
			}
		}
		else 
		{  			
			alert("Error");					
		}
	}
}

function getDisciplineLst(cmb)
{
	var id=cmb.value;		
	if(id=='' || id.search(/'Select'/i)!=-1) {return;}
	var z=document.getElementById('discipline');
	z.length = 1;							
	var disciplineActionflag="EducationDtls&flag=getDiscipline&cmbid="+id;
	sendAjaxRequestHRMS("processDisciplineLstResponse", disciplineActionflag,'', false, "POST");
}
function processDisciplineLstResponse()
{
	if (hrmsXmlHttp.readyState == 4) 
	{     
		endProcess();
		if (hrmsXmlHttp.status == 200) 
		{ 
			var textId;												
			var z=document.getElementById('discipline');			            		            			            	
			var xmlStr = hrmsXmlHttp.responseText;
			var XMLDoc=getDOMFromXML(xmlStr);   
			try 
			{
				var SubStr = XMLDoc.getElementsByTagName('SubQualification');				    	
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
				if(setdiscipline!=-1){document.getElementById('discipline').value=setdiscipline;setdiscipline=-1;}	
			}catch(e){}
		}
	}
}

function hideDiciplineTd()
{
		document.getElementById('diciplineId').style.display='none';
		document.getElementById('discipline').value='Select';	
		if(document.getElementById('courseCategory')!=null)
			document.getElementById('courseCategory').value='Select';
}
