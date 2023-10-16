
<%

try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript"
	src="<c:url value="/script/hrms/gnl/AuditPara/AuditaddRecord.js"/>"></script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
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
<c:set var="BranchTypeList" value="${resValue.TypeBranch}"></c:set>
<c:set var="FileIdForUnsaved" value="${resValue.FileIdForUnsaved}"></c:set>
<script type="text/javascript">


function ClosePage(frm)
{
frm.action="hdiits.htm?actionFlag=getHomePage";
frm.method="POST";
frm.submit();
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
	addOrUpdateRecord('updatedRecord','addTransaction',new Array('AuditParasCncernd','description'));
}

function updatedRecord ()
{
	  if (xmlHttp.readyState == 4)
	  {
	 
	  
	  var displayFieldArray = new Array("AuditParasCncernd","description");
	 
	  updateDataInTable('encXML',displayFieldArray);
	
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

var AddOrUpdate=false;
function editRecord(rowId)
{
	sendAjaxRequestForEdit(rowId, 'populateform');
	document.getElementById('update').style.display="";
	document.getElementById('Add').style.display='none';
	AddOrUpdate=true;
}

function populateform()
{
	if (xmlHttp.readyState == 4)
	   { 	
	  	var decXML = xmlHttp.responseText;
	  	
		var xmlDOM = getDOMFromXML(decXML);
		
		document.getElementById('AuditParasCncernd').value=getXPathValueFromDOM(xmlDOM, 'paraNo');
		document.getElementById('description').value=getXPathValueFromDOM(xmlDOM, 'paraDesc');
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
		document.getElementById('ReplyStatus').value="<fmt:message bundle="${auditLables}" key="HRMS.NotReply"/>";
		
		addOrUpdateRecord('addAnotherXml','addTransactionForScnd',new Array('h2','h3','SentStatus','branch','ReplyStatus'));
		
		
		}
}

function addAnotherXml()
{
	if (xmlHttp.readyState == 4)
	  { 		
	  			var encXML1=xmlHttp.responseText;
	  			
	  	
				var displayFieldArray = new Array("h2","h3","SentStatus","branch","ReplyStatus");
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
		
		trow.insertCell(1).innerHTML ="<INPUT type='checkbox' name='FirstCheck" + counter + "' id='FirstCheck" + counter + "' value='YesChecked' onclick=showchecked1('" + trow.id + "','" + counter + "')>";
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
				empcount1--;
			}
		
		}
	}
	
	

}

function submitDtls()
{
	
	document.AuditForm.action = "hrms.htm?actionFlag=SubmitDtls";
	
	document.AuditForm.submit();
	
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
function callfunction()
{	
	var officeTypeArray = new Array('designation','lastName','firstName','OfficerDtls','parasCommnctd');
	var statusOfficeTypeValidation =  validateSpecificFormFields(officeTypeArray);
	
	
	if(statusOfficeTypeValidation)
	{
		//if(conter>1)
		//{
			StartSubmit("AuditForm");
		//}
		//else
		//{
		//	alert("There is Not Any Paras Raised So Far,Please Enter At Least One");
		//}
	}
	
}
	
function StartSubmit(auditform)
{

window.document.forms[auditform].submit();
}
	
var conter=0;
function ParaNoCheck()
{	var getPress=conter++;
	
	var Nos=document.getElementById('parasCommnctd').value;
	
	if(getPress<Nos)
	{
	addData();
	}
	else
	{
		alert("<fmt:message bundle="${alertLables}" key="HRMS.ParaNoAlert"/>");
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
<hdiits:form name="AuditForm" validate="true" method="POST" action="hrms.htm?actionFlag=SubmitDtls">
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
			<td><hdiits:select captionid="HRMS.Designation1" bundle="${auditLables}" id="designation" name="designation" size="1" sort="false" validation="sel.isrequired" mandatory="true">
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
					<hdiits:text name="lastName" id="lastName" captionid="HRMS.LAST_NAME" bundle="${auditLables}" maxlength="25"  mandatory="true" validation="txt.isrequired" onblur="chkSpChars(this)"/>					
				</td>
				<td  width="25%">
					<hdiits:text name="firstName" id="firstName" captionid="HRMS.FIRST_NAME" bundle="${auditLables}"  maxlength="25"  mandatory="true" validation="txt.isrequired" onblur="chkSpChars(this)"/>
				</td>				
				<td width="25%">
					<hdiits:text name="middleName" id="middleName" captionid="HRMS.MIDDLE_NAME" bundle="${auditLables}" maxlength="25" onblur="chkSpChars(this)"/>
				</td>
				
			</tr>
			<tr>
				<td><b><hdiits:caption captionid="HRMS.OfficerDetails" bundle="${auditLables}" /></b></td>
				<td><hdiits:text name="OfficerDtls" id="OfficerDtls" captionid="HRMS.OfficerDetails" bundle="${auditLables}"  validation="txt.isrequired" mandatory="true" onblur="chkSpChars(this)"/></td>
			</tr>
		<tr>
			<td><b><hdiits:caption captionid="HRMS.CommunicationDate" bundle="${auditLables}" /></b></td>
			<td><hdiits:dateTime  name="Communication" captionid="HRMS.CommunicationDate" bundle="${auditLables}"  />
			</td>
			<td><b><hdiits:caption captionid="HRMS.ParasCommunicated" bundle="${auditLables}" /></b></td>
			<td><hdiits:number name="parasCommnctd" id="parasCommnctd" captionid="HRMS.ParasCommunicated" bundle="${auditLables}" validation="txt.isrequired" mandatory="true" onblur="chkForNosAndSpChars(this)"/></td>
			
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
				<hdiits:dateTime captionid="HRMS.AuditCndctdTo" bundle="${auditLables}" name="AuditCndctdTo" maxvalue="31/12/9999"  onblur="checkOtherdate(this)" />
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
			<hdiits:number name="AuditParasCncernd" id="AuditParasCncernd" mandatory="true" validation="txt.isrequired" captionid="HRMS.AuditParasCncrnd" bundle="${auditLables}" onblur="chkForNosAndSpChars(this)"/>
		</td>
	</tr>
	<tr>
		<td>
			<b><hdiits:caption captionid="HRMS.Description"  bundle="${auditLables}"/>
		</b></td>
		<td>
			<hdiits:textarea name="description" id="description" cols="30" rows="3" caption="ParaDescription" mandatory="true" validation="txt.isrequired" onblur="chkSpChars(this)"></hdiits:textarea>
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
		<!--  <td class="fieldLabel" bgcolor="lightblue" width="10%"><b><hdiits:caption captionid="HRMS.Status" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" bgcolor="lightblue" width="10%"><b><hdiits:caption captionid="HRMS.AssignedTo" bundle="${auditLables}" /></b></td>-->
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
<table id="show" width="150%">
	<tr>
		<td><b><hdiits:caption captionid="HRMS.Branch" bundle="${auditLables}"/></b></td>
		<td><hdiits:select  caption="Branch"name="branch" id="branch" size="1" sort="false" validation="sel.isrequired" mandatory="true">
		<hdiits:option value="0">------------Select------------</hdiits:option>
		<c:forEach var="BrnachType" items="${BranchTypeList}">
		<hdiits:option value="${BrnachType.branchCode}">
		${BrnachType.branchName}
		</hdiits:option>
		</c:forEach>
		</hdiits:select>
		<hdiits:button type="button" name="assign" id="assign" value="assign" captionid="HRMS.Assign" bundle="${auditLables}" style="display:none" onclick="validateAssign()"/>
	</td>
	</tr>
	</table>
</hdiits:fieldGroup>

	
<hdiits:fieldGroup bundle="${auditLables}" expandable="true" id="AssgndParasGrp"  titleCaptionId="HRMS.assgndParas">
<table width="100%" border="1" id="AssignedParas"  style="border-collapse: collapse;display:none" borderColor="BLACK">
	<tr>
		<td class="fieldLabel" bgcolor="#C9DFFF" width="1%"></td>
		<!--  <td class="fieldLabel" bgcolor="lightblue" width="10%"><b><hdiits:caption captionid="HRMS.SrNo" bundle="${auditLables}" /></b></td>-->
		<td class="fieldLabel" align="center" bgcolor="#C9DFFF" width="10%"><b><fmt:message key="HRMS.ParaNo" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" align="center" bgcolor="#C9DFFF" width="10%"><b><fmt:message key="HRMS.Descriptions" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" align="center" bgcolor="#C9DFFF" width="10%"><b><fmt:message key="HRMS.Status" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" align="center" bgcolor="#C9DFFF" width="10%"><b><fmt:message key="HRMS.AssignedTo" bundle="${auditLables}" /></b></td>
		<td class="fieldLabel" align="center" bgcolor="#C9DFFF" width="10%"><b><fmt:message key="HRMS.Reply" bundle="${auditLables}" /></b></td>
	</tr>
</table>
</hdiits:fieldGroup>
<center>
<!--<hdiits:button type="button" name="drop" id="drop" value="drop" captionid="HRMS.Drop" bundle="${auditLables}"/>-->
<hdiits:button name="Submit" id="Submit" type="button" captionid="HRMS.Submit" bundle="${auditLables}" onclick="callfunction()"/>
<!--   <hdiits:button name="Close" type="button" value="Close" captionid="HRMS.Close" bundle="${auditLables}" onclick="ClosePage(document.AuditForm);"/>  -->
</center>

</div>
</div>
<hdiits:hidden name="SentStatus" id="SentStatus"/>
<hdiits:hidden name="h2" id="h2"/>
<hdiits:hidden name="h3" id="h3"/>
<hdiits:hidden name="ReplyStatus" id="ReplyStatus"/>
<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />

<input type="hidden" name="BrnchIdForUnsaved" id="BrnchIdForUnsaved" value="${FileIdForUnsaved}"/>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
		controlNames="designation,lastName,firstName,OfficerDtls,parasCommnctd,AuditParasCncernd,description,branch" />
		

		</hdiits:form>
		<script type="text/javascript">
	initializetabcontent("maintab")
	</script>

		<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
	