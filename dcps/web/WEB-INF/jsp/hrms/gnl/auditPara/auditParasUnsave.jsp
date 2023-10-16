
<%

try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>





<script type="text/javascript"
	src="<c:url value="/script/hrms/gnl/AuditPara/AuditaddRecord.js"/>"></script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>


<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/eis/commonUtils.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>"></script>
<script type="text/javascript"  
         src="common/script/commonfunctions.js">
</script>


<fmt:setBundle basename="resources.gnl.AuditPara.AuditParaLables" var="auditLables"
	scope="request" />
	
<fmt:setBundle basename="resources.gnl.AuditPara.AuditAlertMessages" var="alertLables"
	scope="request" />


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DesignationList" value="${resValue.desigList}"></c:set>
<c:set var="ParaTypeList" value="${resValue.TypePara}"></c:set>
<c:set var="ReplyVOList" value="${resValue.ShowDatafrmAudit}"></c:set>
<c:set var="BranchTypeList" value="${resValue.TypeBranch}"></c:set>
<c:set var="FileIdForUnsaved" value="${resValue.FileIdForUnsaved}"></c:set>
<c:set var="auditId" value="${resValue.auditId}"></c:set>
<c:set var="NewCommuDate" value="${resValue.NewCommuDate}"></c:set>
<c:set var="NewAuditFrm" value="${resValue.NewAuditFrm}"></c:set>
<c:set var="NewAuditTo" value="${resValue.NewAuditTo}"></c:set>
<c:set var="NewAuditFor" value="${resValue.NewAuditFor}"></c:set>
<c:set var="NewAuditPTo" value="${resValue.NewAuditPTo}"></c:set>
<c:set var="commudate" value="${resValue.commudate}"></c:set>
<c:set var="ShowDataforOfficer" value="${resValue.ShowDataforOfficer}"></c:set>
<script type="text/javascript">


function ClosePage(frm)
{
frm.action="hdiits.htm?actionFlag=getHomePage";
frm.method="POST";
frm.submit();
}



function getwindowForReply(ParaId)
{	
	var ParaID=ParaId;
	var href='${rootUrl}'+'?actionFlag=popupParaDetails&paraID='+ParaID;
window.open(href,'chield', 'width=830,height=400,toolbar=yes,minimize=yes,status=yes,menubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}

function getwindow(PARAID)
{
var ParaID=PARAID;


var href=' ${rootUrl}'+'?actionFlag=popupParaDetails&paraID='+ParaID;
window.open(href,'chield', 'width=830,height=400,toolbar=yes,minimize=yes,status=yes,menubar=yes,location=no,scrollbars=yes,top=50,left=200');
}


var counters=0;
function addData()
{
addOrUpdateRecord('addRecord','addTransaction',new Array('AuditParasCncernd','description'));
counters++;

}

function addRecord()
{
if (xmlHttp.readyState == 4)
	  { 		
	  			var encXML=xmlHttp.responseText;
				var displayFieldArray = new Array("AuditParasCncernd","description");
				addDataInTable('ParaUnassigned', 'encXML', displayFieldArray, 'editRecord', 'deleteRecord1','','showchecked');	
				resetData();
				document.getElementById('assign').style.display="";
		}			
}
function UpdateRecord()
{	
	document.getElementById('Add').style.display='';
	document.getElementById('update').style.display="none";
	addOrUpdateRecord('updatedRecord','addTransaction',new Array('AuditParasCncernd','description','PID'));
}

function updatedRecord ()
{
	  if (xmlHttp.readyState == 4)
	  {
	 
	  
	  var displayFieldArray = new Array("AuditParasCncernd","description");
	 
	  updateDataInTable('encXML2',displayFieldArray);
	
	  resetData();
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
			
			delRow.parentNode.deleteRow(delRow.rowIndex);		
		}
	
	conter--;
	counters--;	
	if(counters==0)
	{
		document.getElementById('ParaUnassigned').style.display='none';	
	}
	return answer;
	}

function editRecord(rowId)
{
	sendAjaxRequestForEdit(rowId, 'populateform');
	document.getElementById('update').style.display="";
	document.getElementById('Add').style.display='none';
}

function populateform()
{
	if (xmlHttp.readyState == 4)
	   { 	
	  	var decXML = xmlHttp.responseText;
	  	
		var xmlDOM = getDOMFromXML(decXML);
		
		document.getElementById('AuditParasCncernd').value=getXPathValueFromDOM(xmlDOM, 'paraNo');
		document.getElementById('description').value=getXPathValueFromDOM(xmlDOM, 'paraDesc');
		document.getElementById('PID').value=getXPathValueFromDOM(xmlDOM, 'paraId');
		}
}
function resetData()
{
	var temp=document.getElementById('AuditParasCncernd');
	temp.value="";
	var temp=document.getElementById('description');
	temp.value="";
}

var	getrow=new Array();
var secndrow=new Array();
var empcount=0;
function showchecked(rowids,cntr)
{
	
	if(document.getElementById('FirstCheck'+cntr).checked==true)
	{
		getrow[empcount]=rowids;
		empcount++;
		
	}
	else
	{ 
		for(var i=0; i<getrow.length; i++)
		{ 
			
			if(getrow[i]== rowids)
			{
				getrow.splice(i,1);
				empcount--;
			}
		
		}
	}
	
	getXMLfile();

}
var paths;
function getXMLfile()
{

	paths="";
	for(var i=0;i<getrow.length;i++)
	{
		
		var differ=getrow[i].substring(3,getrow[i].length);
		
		var xmlFileName = document.getElementById(differ).value;
		
		paths=paths+","+xmlFileName;	
	}
	
	
}


function addInXml()
{
	var getXmlpaths=paths;


	var splittedPaths=new Array();
	splittedPaths=getXmlpaths.substring(1,getXmlpaths.length).split(",");
	
	for(var i=0;i<splittedPaths.length;i++)
	{	
		
		var xmlFileName2=splittedPaths[i];
	
		xmlHttp1=GetXmlHttpObject();
		if (xmlHttp1==null) 
		{
		  hideProgressbar();
		  alert ("Your browser does not support AJAX!");
		  return;
		} 		
		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName2;
		//methodName = methodName + "()";
		
		xmlHttp1.onreadystatechange = function() {
			if(xmlHttp1.readyState == 4) {
				eval('Addanother()');
				hideProgressbar();
			 }
		 }
		
		xmlHttp1.open("POST",encodeURI(url),false);
		xmlHttp1.send(null);	
	}
	
	var removeRow=getrow;
	
	var removed=new Array();
	removed=removeRow;
	
	for(var j=0;j<removed.length;j++)
	{	
		var rowId=removed[j];
		
		deleteRecords(rowId); 
	}
	paths="";
	getrow=new Array();
	empcount=0;
	
}
function deleteRecords(rowId) 
	{
		var delCap = "";
		
			
			var delRow = document.getElementById(rowId);
			
			delRow.parentNode.deleteRow(delRow.rowIndex);		
		
	}

function Addanother()
{
	if (xmlHttp1.readyState == 4) { 	
	  var decXML = xmlHttp1.responseText;
	
		var xmlDOM = getDOMFromXML(decXML);
	
		var paraNo=getXPathValueFromDOM(xmlDOM, 'paraNo');
		document.getElementById('h2').value=paraNo;
		
		var paraDesc=getXPathValueFromDOM(xmlDOM, 'paraDesc');
		document.getElementById('h3').value=paraDesc;
		
		var branch=document.getElementById('branch').value;
		
	
		document.getElementById('SentStatus').value="<fmt:message bundle="${auditLables}" key="HRMS.SentStatus"/>";
	//document.getElementById('ReplyStatus').value="<fmt:message bundle="${auditLables}" key="HRMS.NotReplied"/>";
		var ParaIDs=getXPathValueFromDOM(xmlDOM, 'paraId');
		document.getElementById('hiddenParaId').value=ParaIDs;
		
		if(ParaIDs!=0)
		{
			
			addOrUpdateRecord('addAnotherXml','addTransactionForScnd',new Array('h2','h3','branch','SentStatus','hiddenParaId'));
		}
		else{
		addOrUpdateRecord('addAnotherXml','addTransactionForScnd',new Array('h2','h3','branch','SentStatus'));
		}
		
		}
}

function addAnotherXml()
{
	if (xmlHttp.readyState == 4)
	  { 		
	  			var encXML1=xmlHttp.responseText;
	  			
	  	
				var displayFieldArray = new Array("h2","h3","branch","SentStatus");
				addDataInTable1('AssignedParas','encXML1', displayFieldArray,'showchecked');	
				
				resetData();
				document.getElementById('assign').style.display="";
				
				counters--;	
				if(counters==0)
				{
					document.getElementById('ParaUnassigned').style.display='none';	
				}
				var num = document.getElementById('ParaUnassigned').rows.length;												
				var rowLen=num;
				if(rowLen==2)
				{
					document.getElementById('ParaUnassigned').style.display='none';																				
				}
		}			
}
var counter = 1;
function addDataInTable1(tableId, hiddenField, displayFieldArray,checkMethod) 
	{
		
		
		if(checkMethod == undefined) 
		{
			checkMethod = '';
		}
		
		document.getElementById(tableId).style.display='';
		var trow=document.getElementById(tableId).insertRow();
		
		trow.id = 'row' + hiddenField + counter;
		
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" + xmlHttp.responseText + "'/>";				
		trow.cells[0].style.display = 'none';
		
		trow.insertCell(1).innerHTML ="<INPUT type='checkbox' name='FirstCheck" + counter + "' id='FirstCheck" + counter + "' value='YesChecked' disabled='disabled' onclick=showchecked('" + trow.id + "','" + counter + "')>";
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++)
		{
			var field = document.getElementById(displayFieldArray[i]);
			
			if(field.type == 'select-one')
			{
				/* Code added by Tarun Trehan to check "Select" value in drop down
				for multiple add case. */
				if(field.options[field.selectedIndex].value == '-1')
				{
					trow.insertCell(i+2).innerHTML = "";
				}
				else
				{
					trow.insertCell(i+2).innerHTML = field.options[field.selectedIndex].text;						
				}
			}		
			else if(field.type == 'radio')
			{
				var radio=document.getElementsByName(displayFieldArray[i]);
				for(var j = 0; j < radio.length; j++)
				{
					if(radio[j].checked)
					{
						trow.insertCell(i+2).innerHTML = radio[j].value;
					}
				}
					
			}		
			else
			{
				trow.insertCell(i+2).innerHTML = field.value;	
			}
		}	
		counter++;	
		
		return trow.id;
	}
function addDBDataInTable3(tableId,hiddenField,dispFieldA,
						xmlFilePath,editMethodName,deleteMethodName,viewMethodName,checkMethod)
	{
			
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
		if(checkMethod 	== undefined) 
		{
			checkMethod 	 = '';
		}
		document.getElementById(tableId).style.display='';
		
		
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
	
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';
		trow.insertCell(1).innerHTML ="<INPUT type='checkbox' name='FirstCheck" + counter + "' id='FirstCheck" + counter + "' disabled='disabled' value='YesChecked' onclick=showchecked1('" + trow.id + "','" + counter + "')>";
		var len = dispFieldA.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+2).innerHTML = dispFieldA[i];	
		}	
		counter++;
		
		return trow.id;
	}
var getsecndrow=new Array();	
var empcount1=0;
function showchecked1(rowids,cntr)
{
	
	if(document.getElementById('FirstCheck'+cntr).checked==true)
	{
		getsecndrow[empcount1]=rowids;
		empcount1++;
		
	}
	else
	{ 
		for(var i=0; i<getsecndrow.length; i++)
		{ 
			
			if(getsecndrow[i]== rowids)
			{
				getsecndrow.splice(i,1);
				//alert("splice function="+getsecndrow.splice(i,1));
				empcount1--;
				
			}
		
		}
	}
	
	getXMLfileforDrop();

}

var paths1;
function getXMLfileforDrop()
{

	paths1="";
	for(var i=0;i<getsecndrow.length;i++)
	{
		
		var differ=getsecndrow[i].substring(3,getsecndrow[i].length);
		
		var xmlFileName = document.getElementById(differ).value;
		
		paths1=paths1+","+xmlFileName;	
		
	}
	
}

function addInXmlForDrop()
{	
	
	var getXmlpaths=paths1;
	if(getXmlpaths!=undefined && getXmlpaths!="")
	{
		var splittedPaths=new Array();
		splittedPaths=getXmlpaths.substring(1,getXmlpaths.length).split(",");
	
		for(var i=0;i<splittedPaths.length;i++)
		{	
		
			var xmlFileName2=splittedPaths[i];
			
			xmlHttp10=GetXmlHttpObject();
			if (xmlHttp10==null) 
			{
		  		hideProgressbar();
		  		alert ("Your browser does not support AJAX!");
		  		return;
			} 		
			var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName2;
			//methodName = methodName + "()";
			xmlHttp10.onreadystatechange = function()
			{
				if(xmlHttp10.readyState == 4)
				{
					eval('AddanotherForDrop()');
					hideProgressbar();
				}
			}
			xmlHttp10.open("POST",encodeURI(url),false);
			xmlHttp10.send(null);	
		 }
		
		
		document.AuditForm.action = "hrms.htm?actionFlag=DropRepliedDtls&ParaId="+AllParaId;
		document.AuditForm.submit();	
	}
	else
	{
		alert("<fmt:message bundle="${alertLables}" key="HRMS.SelectParaAlert"/>");
	}
	
	empcount1=0;
	paths1="";
	AllParaId="";
}

var StatusFlag=false;
var flagForAllreplied=true;
var AllParaId=0;
function AddanotherForDrop()
{
	if (xmlHttp10.readyState == 4) { 	
	  var decXML = xmlHttp10.responseText;
	
		var xmlDOM = getDOMFromXML(decXML);
		
		
		var Status=getXPathValueFromDOM(xmlDOM, 'status');
		if(Status=='Replied')
		{
			var ParaId=getXPathValueFromDOM(xmlDOM, 'paraId');
			
			AllParaId=ParaId+","+AllParaId;
			
			ForDropping(ParaId);
		}
		else
		{	
			
			StatusFlag=false;
			flagForAllreplied=false;
		}
		
		}
		
		
}

function ForDropping(ParaId)
{	
	var PARAid=new Array();
}


function submitDtls()
{
	
	document.AuditForm.action = "hrms.htm?actionFlag=SubmitDtls";
	
	document.AuditForm.submit();
	
}

function getAuditId(auditId)
{
	
	getUnsaved(auditId);
	
}

function getUnsaved(auditId)
{
	var audiId=auditId;
	
	try{   
    			xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new 
                        ActiveXObject("Msxml2.XMLHTTP");      
      				}
		    		catch (e){
		          		try{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  		}
				      	catch (e){
			           	   	  alert("Your browser does not support AJAX!");        
			            	  return false;        
			      		}
			 		}			 
        	}	
			var url = "hrms.htm?actionFlag=getUnsaved&auditId="+audiId;    
			xmlHttp.open("POST", encodeURI(url) , false);			
			xmlHttp.onreadystatechange = processResponse1;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
}
var globalNOtot;
var globalTotal;
function processResponse1()
{

	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
		
				var xmlStr = xmlHttp.responseText;
				
				
			    var XMLDoc=getDOMFromXML(xmlStr);
			  
			    
			    var paraNo=XMLDoc.getElementsByTagName('paraNo');
			   
			    var paraDesc=XMLDoc.getElementsByTagName('paraDesc');
			   
			    var XmlFilePath=XMLDoc.getElementsByTagName('XMLFilePath');
			    
			    var ParaId=XMLDoc.getElementsByTagName('ParaId');
			   
			    globalNOtot=paraNo.length;
			    if(paraNo.length>0)
			    {
			    	
			    	for(var i=0;i<paraNo.length;i++)
			    	{
			    		
			    		var	ParaNO=paraNo[i].childNodes[0].text;
			    		
			    		var	ParaDESc=paraDesc[i].childNodes[0].text;
			    		
			    		var XMLPath=XmlFilePath[i].childNodes[0].text;
			    		
			    		var PId=ParaId[i].childNodes[0].text;
			    		
			    		addDBDataInTable('ParaUnassigned','encXML2',new Array(ParaNO,ParaDESc),XMLPath,'editRecord','deleteParaRecord','','showchecked');
			    		
			    	}
			    }
			    var paraNo1=XMLDoc.getElementsByTagName('paraNo1');
			    
			    var paraDesc1=XMLDoc.getElementsByTagName('paraDesc1');
			  
			    var XmlFilePath1=XMLDoc.getElementsByTagName('XMLFilePath1');
			    
			    var AssignedTo=XMLDoc.getElementsByTagName('AssignedTOName');
			  
			    var Status=XMLDoc.getElementsByTagName('Status');
			    
			    var Reply=XMLDoc.getElementsByTagName('Reply');
			    
			    var ParaID=XMLDoc.getElementsByTagName('ParaId1');
			    globalTotal=paraNo1.length;
			    if(paraNo1.length>0)
			    {
			    	
			    	
			    	for(var j=0;j<paraNo1.length;j++)
			    	{
			    		var	ParaNO2=paraNo1[j].childNodes[0].text;
			    		
			    		
			    		var	ParaDESc2=paraDesc1[j].childNodes[0].text;
			    		
			    		var XMLPath2=XmlFilePath1[j].childNodes[0].text;
			    		
			    		var Assigned=AssignedTo[j].childNodes[0].text;
			    		
			    		var StatusIs=Status[j].childNodes[0].text;
			    		
			    		
			    		var ReplyBy=Reply[j].childNodes[0].text;
			    		
			    		var paraID=ParaID[j].childNodes[0].text;
			    		
			    		
			    		
			    		if(StatusIs=='Replied' || StatusIs=='Dropped')
			    		{
			    				addDBDataInTable2('AssignedParas','encXML3',new Array(ParaDESc2,Assigned),XMLPath2,'editRecord','deleteRecord','','showchecked',ParaNO2,paraID,StatusIs);
			    		}
			    		else
			    		{
			    			var StatusIs1="<fmt:message bundle="${auditLables}" key="HRMS.SentStatus"/>";
			    			addDBDataInTable3('AssignedParas','encXML3',new Array(ParaNO2,ParaDESc2,Assigned,StatusIs1),XMLPath2,'editRecord','deleteRecord','','showchecked');
			    		}
			    	}
			    	document.getElementById('assign').style.display="";
			    }
		}
	}
}


function deleteParaRecord(rowId)
{		
		
		var answer = deleteDBRecord(rowId);															
		var num = document.getElementById('ParaUnassigned').rows.length;
		if(num==1)
		{
			document.getElementById('ParaUnassigned').style.display='none';																				
		}				
				
}

function addDBDataInTable2(tableId,hiddenField,dispFieldA,
						xmlFilePath,editMethodName,deleteMethodName,viewMethodName,checkMethod,ParaNo,ParaID,Status)
	{
			
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
		if(checkMethod 	== undefined) 
		{
			checkMethod 	 = '';
		}
		document.getElementById(tableId).style.display='';
		
		var ParaNO=ParaNo;
	
		var PARAID=ParaID;
		
		var StatusIs=Status;
		
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
	
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';
		if(StatusIs=='Dropped')
		{	
			StatusIs="<fmt:message bundle="${auditLables}" key="HRMS.Dropped"/>";
			trow.insertCell(1).innerHTML ="<INPUT type='checkbox' name='FirstCheck" + counter + "' id='FirstCheck" + counter + "' value='YesChecked' disabled='disabled' onclick=showchecked1('" + trow.id + "','" + counter + "')>";
		}
		else
		{
			StatusIs="<fmt:message bundle="${auditLables}" key="HRMS.Replied"/>";
			trow.insertCell(1).innerHTML ="<INPUT type='checkbox' name='FirstCheck" + counter + "' id='FirstCheck" + counter + "' value='YesChecked' onclick=showchecked1('" + trow.id + "','" + counter + "')>";
		}
		trow.insertCell(2).innerHTML ="<a href=javascript:void('ParaNO')  onclick=javascript:getwindow('"+PARAID +"')>"+ParaNO+"</a>";
		var len = dispFieldA.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+3).innerHTML = dispFieldA[i];	
		}	
		trow.insertCell(len+2).innerHTML ="<a href=javascript:void('Status')  onclick=javascript:getwindowForReply('"+PARAID +"')>"+StatusIs+"</a>";
		counter++;
		
		return trow.id;
	}

function validateAdd(){
	
 	var officeTypeArray = new Array('designation','lastName','firstName','OfficerDtls','parasCommnctd','AuditParasCncernd','description');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	
	if(statusOfficeTypeValidation)
	{
	ParaNoCheck();
	
	}
	}
function validateUpdate()
{
	var officeTypeArray = new Array('designation','lastName','firstName','OfficerDtls','parasCommnctd','AuditParasCncernd','description');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	
	if(statusOfficeTypeValidation)
	{
		UpdateRecord();
	}
}		
function validateAssign(){
	
 	var officeTypeArray = new Array('branch');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	
	if(statusOfficeTypeValidation)
	{	
		if(paths==undefined || paths=="")
		{
			alert("<fmt:message bundle="${alertLables}" key="HRMS.SelectParaAlert"/>");
		}
		else
		{
			addInXml();
		}	
	}
	}
	
var conter=0;
function ParaNoCheck()
{	var getPress=conter++;
	var Totalval=globalNOtot+globalTotal+getPress;
	
	var Nos=document.getElementById('parasCommnctd').value;
	
	if(Totalval<Nos)
	{
	addData();
	}
	else
	{
		alert("<fmt:message bundle="${alertLables}" key="HRMS.ParaNoAlert"/>");
		conter--;
	}
}

function callfunction()
{	
	var officeTypeArray = new Array('designation','lastName','firstName','OfficerDtls','parasCommnctd');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	
	if(statusOfficeTypeValidation)
	{
	StartSubmit("AuditForm");
	
	}
	
}



function StartSubmit(auditform)
{

window.document.forms[auditform].submit();
}

var abc=new Array(5);
function gettingDates(first,second,third,fourth,fifth)
{	
	var getArray=new Array(first,second,third,fourth,fifth);
	
	
	
	
	
	if(getArray[0]!=undefined)
	{
	document.getElementById('Communication').value=getArray[0];
	}
	if(getArray[1]!=undefined)
	{
	document.getElementById('AuditStart').value=getArray[1];
	}
	if(getArray[2]!=undefined)
	{
	document.getElementById('AuditEnd').value=getArray[2];
	}
	if(getArray[3]!=undefined)
	{
	document.getElementById('AuditCndctdFrm').value=getArray[3];
	}
	if(getArray[4]!=undefined)
	{
	document.getElementById('AuditCndctdTo').value=getArray[4];
	}
}


function chkSpChars(control)
			{
				var iChars = "#^+=\\\;|\<>";
				for (var i = 0; i < control.value.length; i++)
  					{
  						if (iChars.indexOf(control.value.charAt(i)) != -1) 
  						{
  							alert("<fmt:message bundle="${alertLables}" key="HRMS.SpclChars"/>");
  							control.focus();
  							return false;
  						}
  					}
			}
			
function chkForNosAndSpChars(control)
			{
				var iChars = "0123456789";
				
				for (var i = 0; i < control.value.length; i++)
  					{
  						if (iChars.indexOf(control.value.charAt(i)) == -1) 
  						{
  							alert("<fmt:message bundle="${alertLables}" key="HRMS.NOs"/>");
  							control.focus();
  							return false;
  						}
  						
  					}
			}



function checkdate(q)
{
	var AuditStartDate=document.AuditForm.AuditStart.value;
	var AuditEndDate=q.value;
	if(AuditEndDate!="")
	{
		if(compareDate(AuditStartDate,AuditEndDate)<=0 )
		{
			alert("<fmt:message bundle="${alertLables}" key="HRMS.CheckAuditdate"/>");
			document.AuditForm.AuditEnd.value="";
			document.AuditForm.AuditEnd.focus();	   	
		}
	}
}
function checkOtherdate(q)
{
	var AuditStartDate=document.AuditForm.AuditCndctdFrm.value;
	var AuditEndDate=q.value;
	if(AuditEndDate!="")
	{
		if(compareDate(AuditStartDate,AuditEndDate)<=0 )
		{
			alert("<fmt:message bundle="${alertLables}" key="HRMS.CheckOtherAuditdate"/>");
			document.AuditForm.AuditCndctdTo.value="";
			document.AuditForm.AuditCndctdTo.focus();
		}
	}
}
</script>
<hdiits:form name="AuditForm" validate="true" method="POST" action="hrms.htm?actionFlag=SubmitNewDtls">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"
			bgColor="#386CB7"><b><fmt:message key="HRMS.AuditPara"
			bundle="${auditLables}" /></b></a></li>

	</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0"><br>
<hdiits:fieldGroup bundle="${auditLables}" expandable="false" id="AuditParaDetailsGrp" titleCaptionId="HRMS.ReportDtls" mandatory="true">
	<table width="100%" align="center">
		<tr>
			<td><b><hdiits:caption captionid="HRMS.Designation1" bundle="${auditLables}" /></b></td>
			<td><hdiits:select captionid="HRMS.Designation1" bundle="${auditLables}" mandatory="true" name="designation" size="1" sort="false" validation="sel.isrequired" >
				<hdiits:option>------------Select------------</hdiits:option>
				<c:forEach var="DesigType" items="${DesignationList}">
				<hdiits:option value="${DesigType.lookupId}">
				${DesigType.lookupDesc}</hdiits:option>
				</c:forEach>
				
			</hdiits:select></td>
		</tr>
		<tr>
				<td width="25%"></td>
				<td  width="25%"><b><hdiits:caption captionid="HRMS.LAST_NAME" bundle="${auditLables}"></hdiits:caption></b></td>				
				<td  width="25%"><b><hdiits:caption captionid="HRMS.FIRST_NAME" bundle="${auditLables}"></hdiits:caption></b></td>
				<td  width="25%"><b><hdiits:caption captionid="HRMS.MIDDLE_NAME" bundle="${auditLables}"></hdiits:caption></b></td>
				
			</tr>
			<tr>
				<td  width="25%"><b><hdiits:caption captionid="HRMS.NAME" bundle="${auditLables}"></hdiits:caption></b></td>				
				<td width="25%">
					<hdiits:text name="lastName" id="lastName" captionid="HRMS.LAST_NAME" bundle="${auditLables}" maxlength="25"  mandatory="true" validation="txt.isrequired"/>					
				</td>
				<td  width="25%">
					<hdiits:text name="firstName" id="firstName" captionid="HRMS.FIRST_NAME" bundle="${auditLables}"  maxlength="25"  mandatory="true" validation="txt.isrequired" />
				</td>				
				<td width="25%">
					<hdiits:text name="middleName" id="middleName" captionid="HRMS.MIDDLE_NAME" bundle="${auditLables}" maxlength="25" />
				</td>
				
			</tr>
				 <tr>
				<td><b><hdiits:caption captionid="HRMS.OfficerDetails" bundle="${auditLables}" /></b></td>
				 <td><hdiits:text name="OfficerDtls" id="OfficerDtls"   /></td>
			</tr>
		<tr>
			<td><b><hdiits:caption captionid="HRMS.CommunicationDate" bundle="${auditLables}" /></b></td>
			<td><hdiits:dateTime captionid="HRMS.CommunicationDate" bundle="${auditLables}" name="Communication" /></td>
			
			<td><b><hdiits:caption captionid="HRMS.ParasCommunicated" bundle="${auditLables}" /></b></td>
			<td><hdiits:number name="parasCommnctd" id="parasCommnctd" caption="No of Paras Raised" validation="txt.isrequired" mandatory="true" /></td>
		</tr>
		<tr>
			<td>
				<b><hdiits:caption captionid="HRMS.AuditPeriodFrm" bundle="${auditLables}"/></b>
			</td>
			<td>
				<hdiits:dateTime captionid="HRMS.AuditPeriodFrm" bundle="${auditLables}" name="AuditStart" />
			</td>
			<td>
				<b><hdiits:caption captionid="HRMS.AuditPeriodTo" bundle="${auditLables}"/></b>
			</td>
			<td>
				<hdiits:dateTime captionid="HRMS.AuditPeriodTo" bundle="${auditLables}" name="AuditEnd" maxvalue="31/12/9999" onblur="checkdate(this)"/>
			</td>
		</tr>
		<tr>
			<td>
				<b><hdiits:caption captionid="HRMS.AuditCndctdFrom"  bundle="${auditLables}"/></b>
			</td>
			<td>
				<hdiits:dateTime captionid="HRMS.AuditCndctdFrom"  bundle="${auditLables}" name="AuditCndctdFrm" />
			</td>
			<td>
				<b><hdiits:caption captionid="HRMS.AuditCndctdTo" bundle="${auditLables}"/></b>
			</td>
			<td>
				<hdiits:dateTime captionid="HRMS.AuditCndctdTo" bundle="${auditLables}" name="AuditCndctdTo" maxvalue="31/12/9999" onblur="checkOtherdate(this)"/>
			</td>
		</tr>	
		<tr>
			<td>
				<b><hdiits:caption captionid="HRMS.Remarks1"	bundle="${auditLables}"/></b>
			</td>
			<td>
				<hdiits:textarea name="remarks" caption="Remarks" cols="35" rows="3" onblur="chkSpChars(this)"></hdiits:textarea>
			</td>
		</tr>
</table>
</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${auditLables}" expandable="false" id="EditReprtDtlsGrp" titleCaptionId="HRMS.EditReprtDtls" mandatory="true">
<table width="86%" >
	<!--  	
	<tr>
		<td>
			<b><hdiits:caption captionid="HRMS.TypeParas" bundle="${auditLables}"/></b>
		</td>
		<td>
			<hdiits:select name="AuditParas" id="AuditParas" sort="false" size="1" onchange="ShowDesc()">
			<hdiits:option>------------Select------------</hdiits:option>
			<c:forEach var="Paratype" items="${ParaTypeList}">
			<hdiits:option value="${Paratype.lookupId}">
				${Paratype.lookupDesc}</hdiits:option></c:forEach>
			</hdiits:select>
		</td>
	</tr>
	-->
	<tr>
		<td>
			<b><hdiits:caption captionid="HRMS.AuditParasCncrnd" bundle="${auditLables}"/></b>
		</td>
		<td>
			<hdiits:number name="AuditParasCncernd" id="AuditParasCncernd"  captionid="HRMS.AuditParasCncrnd" bundle="${auditLables}" validation="txt.isrequired" mandatory="true" />
		</td>
	</tr>
	<tr>
		<td>
			<b><hdiits:caption captionid="HRMS.Description"  bundle="${auditLables}"/>
		</b></td>
		<td>
			<hdiits:textarea name="description" id="description" cols="30" rows="3" validation="txt.isrequired" caption=" Para Description"  mandatory="true" onblur="chkSpChars(this)"></hdiits:textarea>
			<hdiits:button type="button" name="Add" id="Add" captionid="HRMS.Add" bundle="${auditLables}" onclick="validateAdd()"/>
			<hdiits:button type="button" name="update" id="update" captionid="HRMS.Update" bundle="${auditLables}" onclick="validateUpdate()" style="display:none"/>
		</td>
	</tr>
</table>

<!-- <TABLE class=tabtable>
		<TBODY>
			<TR bgColor=#386cb7>
				<TD class=fieldLabel align=middle colSpan=5><FONT color=#ffffff><STRONG><U><fmt:message key="HRMS.unassgndParas" bundle="${auditLables}" /></U></STRONG></FONT></TD>
			</TR>
		</TBODY>
	</TABLE>
 -->
	
<table id="ParaUnassigned" width="100%" border="1"  style="border-collapse: collapse;display:none" borderColor="BLACK">
	<tr>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="1%"></td>
		<!--  <td class="fieldLabel" bgcolor="lightblue" width="10%"><b><hdiits:caption captionid="HRMS.SrNo" bundle="${auditLables}" /></b></td>-->
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.ParaNo" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.Descriptions" bundle="${auditLables}" /></b></td>
		<!--  <td class="fieldLabel" bgcolor="lightblue" width="10%" align="center"><b><hdiits:caption captionid="HRMS.Status" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%"><b><hdiits:caption captionid="HRMS.AssignedTo" bundle="${auditLables}" /></b></td>-->
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.Action" bundle="${auditLables}" /></b></td>
	</tr>
</table>

<table width="100%">
	<tr align="center">
	<td>
	
	<!--<hdiits:button type="button" name="convert" id="convert" value="convert" captionid="HRMS.Convert" bundle="${auditLables}"/></td>-->
	</tr>
</table>
<br>
<table width="150%">
	<tr>
		<td><b><hdiits:caption captionid="HRMS.Branch" bundle="${auditLables}"/></b></td>
		<td><hdiits:select name="branch" captionid="HRMS.Branch" bundle="${auditLables}" id="branch" size="1" mandatory="true"  validation="sel.isrequired" sort="false">
		<hdiits:option value="">------------Select------------</hdiits:option>
		<c:forEach var="BrnachType" items="${BranchTypeList}">
		<hdiits:option value="${BrnachType.branchCode}">
		${BrnachType.branchName}
		</hdiits:option>
		</c:forEach>
		</hdiits:select>
		<hdiits:button type="button" name="assign" id="assign" value="assign" captionid="HRMS.Assign" bundle="${auditLables}" onclick="validateAssign()"/>
	</td>
	</tr>
	</table>

</hdiits:fieldGroup>

<hdiits:fieldGroup bundle="${auditLables}" expandable="true" id="AssgndParasGrp"  titleCaptionId="HRMS.assgndParas">
<table width="100%" border="1" id="AssignedParas"  style="border-collapse: collapse;display:none" borderColor="BLACK">
	<tr>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="1%"></td>
		<!--  <td class="fieldLabel" bgcolor="lightblue" width="10%"><b><hdiits:caption captionid="HRMS.SrNo" bundle="${auditLables}" /></b></td>-->
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.ParaNo" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.Descriptions" bundle="${auditLables}" /></b></td>
		
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.AssignedTo" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><fmt:message key="HRMS.Status" bundle="${auditLables}" /></b></td>
		<!--  <td class="fieldLabel" bgcolor="lightblue" width="10%"><b><hdiits:caption captionid="HRMS.Reply" bundle="${auditLables}" /></b></td>-->
	</tr>
</table>
</hdiits:fieldGroup>
<script>
	getAuditId('${auditId}');
	gettingDates("${NewCommuDate }","${NewAuditFrm}","${ NewAuditTo}","${NewAuditFor }","${ NewAuditPTo}");
	
	var remrks="${ReplyVOList.remarks }";
	//alert(remrks);
	document.getElementById('remarks').value=remrks;
	var fname='${ReplyVOList.fnameOfficer}';
	document.getElementById('firstName').value=fname;
	var mname="${ReplyVOList.mnameOfficer}";
	document.getElementById('middleName').value=mname;
	var lname="${ReplyVOList.lnameOfficer}";
	document.getElementById('lastName').value=lname;
	var otherDtl="${ReplyVOList.otherDtls}";
	document.getElementById('OfficerDtls').value=otherDtl;
	var totalPara="${ReplyVOList.totalParas }";
	document.getElementById('parasCommnctd').value=totalPara;
	
	var desig="${ShowDataforOfficer.lookupId }";
	if(desig=="")
	{
		var desig1="${ReplyVOList.cmnLookupMst.lookupId }";
		document.getElementById('designation').value=desig1;
	}
	else
	{
		document.getElementById('designation').value=desig;
	}
</script>
<center>
<hdiits:button type="button" name="drop" id="drop" value="drop" captionid="HRMS.Drop" bundle="${auditLables}" onclick=" addInXmlForDrop()"/>
<hdiits:button name="Submit" id="Submit" type="button" captionid="HRMS.Submit" bundle="${auditLables}" onclick="callfunction()"/>
</center>

</div>
</div>
<hdiits:hidden name="SentStatus" id="SentStatus"/>
<hdiits:hidden name="h2" id="h2"/>
<hdiits:hidden name="h3" id="h3"/>
<input type="text" name="hiddenParaId" id="hiddenParaId" style="display:none"/>
<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />

<hdiits:hidden name="ReplyStatus" id="ReplyStatus"/>
<input type="text" name="BrnchIdForUnsaved" id="BrnchIdForUnsaved" value="${FileIdForUnsaved}" style="display:none"/>
<input type="hidden" name="AuditID" id="AuditID" value="${auditId }"/>
<hdiits:hidden name="PID" id="PID"/>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
		controlNames="designation,lastName,firstName,OfficerDtls,parasCommnctd,AuditParasCncernd,description,branch" />
<script type="text/javascript">
initializetabcontent("maintab")
</script>
<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
		<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	