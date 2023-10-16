<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/leave/leavecommon.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="allLeaveTypes" value="${resValue.allLeaveTypes}"></c:set>
<c:set var="leaveTypeCustomList" value="${resValue.leaveTypeCustomList}"></c:set>
<c:set var="xmlFilePathNameForMulAdd" value="${resValue.xmlFilePathNameForMulAdd}"></c:set>

<fmt:setBundle basename="resources.ess.leave.AlertMessages" var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.leave.LeaveCaption" var="LeaveCaption" scope="request" />

<script language="javascript">


var recordCount=new Number();
var editDBFlag=new Number();
var changeFlag=new Number();

function validation(action)
{
var typeShortNameEn=document.getElementById("typeShortNameEn").value;
var typeShortNameGu=document.getElementById("typeShortNameGu").value;
var maintainBal=document.getElementById("maintainBal_yes").checked;

	if(document.getElementById("typeNameEn").value=='')
		{
			alert('<fmt:message key="HRMS.leaveTypeEn" bundle="${alertLables}"/>');
			setFocusSelection('typeNameEn');
			return;
		}
	else if(document.getElementById("typeNameGu").value=='')
		{
			alert('<fmt:message key="HRMS.leaveTypeGu" bundle="${alertLables}"/>');
			setFocusSelection('typeNameGu');
			return;
		}
	else
		{
			if(maintainBal==true)
				isBalMaintain='Yes';
			if(maintainBal==false)
				isBalMaintain='No';

			document.getElementById('isBalMaintain').value=isBalMaintain;
			addOrUpdateRecord(action, 'getLeaveTypeAddAjax', new Array('typeNameEn', 'typeNameGu','typeShortNameEn','typeShortNameGu','isBalMaintain','recordExist','leaveCodeEn','leaveCodeGu'));
		}
}

function addRecord() {
	  if (xmlHttp.readyState == 4)
	  {
	  
		var displayFieldArray = new Array('typeNameEn','typeShortNameEn','typeNameGu','typeShortNameGu','isBalMaintain');
		
		addDataInTable('leaveTypeTable','encXML',displayFieldArray,'editRecord','deleteRec','');	
		recordCount=recordCount+1;	
   	    resetData();   			
	   }	
}

function deleteRec(rowId)
{
	recordCount=recordCount-1;
	deleteRecord(rowId);
}

function resetData()
{
	document.getElementById("leaveCodeEn").value='';
	document.getElementById("leaveCodeGu").value='';
	document.getElementById("typeNameEn").value='';
	document.getElementById("typeNameGu").value='';
	document.getElementById("typeShortNameEn").value='';
	document.getElementById("typeShortNameGu").value='';
	document.getElementById('maintainBal_no').disabled=false;
	document.getElementById('maintainBal_no').checked=true;
}

function updateRecord() 
{

	if (xmlHttp.readyState == 4) 
	{ 	
		var displayFieldArray = new Array('typeNameEn','typeShortNameEn','typeNameGu','typeShortNameGu','isBalMaintain');
		if(editDBFlag==-1)
			updateDataInTable('encXML', displayFieldArray);		
		else if(editDBFlag==1)
			updateDataInTable('xmlTemp', displayFieldArray);		
			
			resetData();
			editDBFlag=0;
			document.getElementById('addLeaveType').style.display='';
			document.getElementById('updateLeaveType').style.display='none'; 
    }
}
	

function editRecord(rowId) 
{	
	editDBFlag=-1;
	sendAjaxRequestForEdit(rowId,'populateForm');    	
}	


function populateForm() 
{
	if (xmlHttp.readyState == 4) 
	{ 
		if (xmlHttp.status == 200) 
		{	
		  	var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML,'encXML');

			if(getXPathValueFromDOM(xmlDOM,'leaveTypeNameEn')!=null)
				document.getElementById('typeNameEn').value = getXPathValueFromDOM(xmlDOM,'leaveTypeNameEn'); 
			
			if(getXPathValueFromDOM(xmlDOM,'leaveTypeShortNameEn')!=null)
				document.getElementById('typeShortNameEn').value = getXPathValueFromDOM(xmlDOM,'leaveTypeShortNameEn'); 					
			
			if(getXPathValueFromDOM(xmlDOM,'leaveTypeNameGu')!=null)
				document.getElementById('typeNameGu').value = getXPathValueFromDOM(xmlDOM,'leaveTypeNameGu'); 
			
			if(getXPathValueFromDOM(xmlDOM,'leaveTypeShortNameGu')!=null)
				document.getElementById('typeShortNameGu').value = getXPathValueFromDOM(xmlDOM,'leaveTypeShortNameGu'); 							    
			
			if(getXPathValueFromDOM(xmlDOM,'isBalMaintain')=='Yes'){
				document.getElementById('maintainBal_yes').checked=true;
				}
			if(getXPathValueFromDOM(xmlDOM,'isBalMaintain')=='No'){
					document.getElementById('maintainBal_no').checked=true;
				}
				
			document.getElementById('addLeaveType').style.display='none'; 	    
			document.getElementById('updateLeaveType').style.display='';   	    		   		    
		}
	}
}


function editDBRecord(rowId) 
{	
	setFocusSelection('typeNameEn');
	editDBFlag=1;
	changeFlag=1;
	sendAjaxRequestForEdit(rowId,'populateFormWithDBData');    	
}	


function populateFormWithDBData() 
{
	if (xmlHttp.readyState == 4) 
	{ 
		if (xmlHttp.status == 200) 
		{	
		  	var decXML = xmlHttp.responseText;
			var xmlDOM = getDOMFromXML(decXML,'xmlTemp');
			
			if(getXPathValueFromDOM(xmlDOM,'leaveCodeEn')!=null)
				document.getElementById('leaveCodeEn').value = getXPathValueFromDOM(xmlDOM,'leaveCodeEn'); 
			
			if(getXPathValueFromDOM(xmlDOM,'leaveCodeGu')!=null)
				document.getElementById('leaveCodeGu').value = getXPathValueFromDOM(xmlDOM,'leaveCodeGu'); 
			
			if(getXPathValueFromDOM(xmlDOM,'leaveTypeNameEn')!=null)
				document.getElementById('typeNameEn').value = getXPathValueFromDOM(xmlDOM,'leaveTypeNameEn'); 
			
			if(getXPathValueFromDOM(xmlDOM,'leaveTypeShortNameEn')!=null)
				document.getElementById('typeShortNameEn').value = getXPathValueFromDOM(xmlDOM,'leaveTypeShortNameEn'); 					
			
			if(getXPathValueFromDOM(xmlDOM,'leaveTypeNameGu')!=null)
				document.getElementById('typeNameGu').value = getXPathValueFromDOM(xmlDOM,'leaveTypeNameGu'); 
			
			if(getXPathValueFromDOM(xmlDOM,'leaveTypeShortNameGu')!=null)
				document.getElementById('typeShortNameGu').value = getXPathValueFromDOM(xmlDOM,'leaveTypeShortNameGu'); 							    
			
			if(getXPathValueFromDOM(xmlDOM,'isBalMaintain')=='Yes')
				document.getElementById('maintainBal_yes').checked=true;
			
			if(getXPathValueFromDOM(xmlDOM,'isBalMaintain')=='No'){
					document.getElementById('maintainBal_no').checked=true;
				}

			if(getXPathValueFromDOM(xmlDOM,'recordExist')=='true'){
					document.getElementById('recordExist').value=getXPathValueFromDOM(xmlDOM,'recordExist');
						if(getXPathValueFromDOM(xmlDOM,'isBalMaintain')!='No')
							document.getElementById('maintainBal_no').disabled=true;
					}

			document.getElementById('addLeaveType').style.display='none'; 	    
			document.getElementById('updateLeaveType').style.display='';   
						
		}
	}
}


function deleteDBRec(rowId)
{
		changeFlag=1;
		deleteDBRecord(rowId);
}

function Submit()
{
	if(confirm('<fmt:message  bundle="${alertLables}" key="HRMS.SUBMITCONFIRMATION"/>'))
		document.leaveTypeAdminScreen.submit();
}
function goToHomePage()
{
	document.forms[0].action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";
	document.forms[0].submit();
}
</script>


<hdiits:form name="leaveTypeAdminScreen" validate="true" method="post"
	action="hdiits.htm?actionFlag=leaveTypeAdminScreen" encType="multipart/form-data">
	
	<table width="100%" class="tabtable" id="typeDetail">
		<tr>
			<td align="center">
				<b><hdiits:caption captionid="HRMS.typeName" bundle="${LeaveCaption}" /></b>
			</td>
			<td align="left">
				<hdiits:text name="typeNameEn" captionid="HRMS.typeName" mandatory="true" bundle="${LeaveCaption}" id="typeNameEn" tabindex="1"  size="15"/>
			</td>
			<td align="left">
 				<hdiits:text name="typeNameGu" captionid="HRMS.typeName" mandatory="true" bundle="${LeaveCaption}" id="typeNameGu" tabindex="2" size="15"/>
			</td>
			<td>
			</td>
		</tr>
		<tr>
			<td align="center">
				<b><hdiits:caption captionid="HRMS.typeShortName" bundle="${LeaveCaption}" /></b>
			</td>
			<td align="left">
				<hdiits:text name="typeShortNameEn" captionid="HRMS.typeShortName" bundle="${LeaveCaption}" id="typeShortNameEn" tabindex="3"  size="15"/>
			</td>
			<td align="left">
 				<hdiits:text name="typeShortNameGu" captionid="HRMS.typeShortName" bundle="${LeaveCaption}" id="typeShortNameGu" tabindex="4" size="15"/>
			</td>
			<td>
 							<hdiits:hidden name="recordExist" id="recordExist"/>
							<hdiits:hidden name="isBalMaintain" id="isBalMaintain"/>
							<hdiits:hidden name="leaveCodeEn" id="leaveCodeEn"/>
							<hdiits:hidden name="leaveCodeGu" id="leaveCodeGu"/>
			</td>
		</tr>
		<tr>
			<td align="center">
				<b><hdiits:caption captionid="HRMS.maintainBalance" bundle="${LeaveCaption}" /></b>
			</td>
			
			<td align="center"><hdiits:radio name="maintainBal" id="maintainBal_yes" value="1"
				mandatory="false" captionid="HRMS.Yes" bundle="${LeaveCaption}" tabindex="6"  />
				 <hdiits:radio	name="maintainBal" id="maintainBal_no" value="0" default="0"
				mandatory="false" captionid="HRMS.No" bundle="${LeaveCaption}" tabindex="7" />
			</td>
		</tr>
		<tr>
			<td align="center" colspan="3"><hdiits:button type="button"
					name="addLeaveType" id="addLeaveType"
					onclick="javascript:validation('addRecord');" captionid="HRMS.addLeaveType"
					bundle="${LeaveCaption}" /></td>
		</tr>
		<tr>
			<td align="center" colspan="3"><hdiits:button type="button"
					name="updateLeaveType" style="display:none" id="updateLeaveType"
					onclick="javascript:validation('updateRecord');" captionid="HRMS.updateLeaveType"
					bundle="${LeaveCaption}" /></td>
		</tr>
	</table>
		
	<table width="100%" class="tabtable" border="1" bordercolor="black" rules="rows" frame="border" id="leaveTypeTable">
	 <tr bgcolor="#C9DFFF" id="header">
	 	<td>
	 		<b><fmt:message key="HRMS.typeNameEn" bundle="${LeaveCaption}" /></b>
		</td>
		<td>
	 		<b><fmt:message key="HRMS.typeShortName" bundle="${LeaveCaption}" /></b>
		</td>
		<td>
	 		<b><fmt:message key="HRMS.typeNameGu" bundle="${LeaveCaption}" /></b>
		</td>
		<td>
	 		<b><fmt:message key="HRMS.typeShortName" bundle="${LeaveCaption}" /></b>
		</td>
		<td>
	 		<b>Maintain Balance</b>
		</td>
		<td>
	 		<b><fmt:message key="HRMS.Edit" bundle="${LeaveCaption}" /></b> /
	 		<b><fmt:message key="HRMS.Delete" bundle="${LeaveCaption}" /></b>
		</td>
	 </tr>
	 
	 <tr>
	 <td colspan="4">
	 </td>
	 </tr>
	</table>
	<table id="formButtons" width="100%">
      <tr>
	 	<td align="right">
	 		<hdiits:button type="button" name="formSub" id="formSub" onclick="Submit();" captionid="HRMS.Submit" bundle="${LeaveCaption}"/>
	 	</td>
	 	<td align="left">
	 		<hdiits:button name="back" type="button" captionid="HRMS.Close" onclick="goToHomePage();" bundle="${LeaveCaption}"/>
	 	</td>
	  </tr>
	</table>
	
<c:forEach items="${leaveTypeCustomList}" var="leaveTypeCustomList" varStatus="x">
		<c:set var="curXMLFileName" value="${xmlFilePathNameForMulAdd[x.index]}"></c:set>
		<c:set var="leaveTypeNameEn" value="${leaveTypeCustomList.leaveTypeNameEn}"/>
		<c:set var="leaveTypeNameGu" value="${leaveTypeCustomList.leaveTypeNameGu}"/>
		<c:set var="leaveTypeShortNameEn" value="${leaveTypeCustomList.leaveTypeShortNameEn}"/>
		<c:set var="leaveTypeShortNameGu" value="${leaveTypeCustomList.leaveTypeShortNameGu}"/>
		<c:set var="recordExist" value="${leaveTypeCustomList.recordExist}"/>
		<c:set var="isBalMaintain" value="${leaveTypeCustomList.isBalMaintain}"/>

		<script type="text/javascript">
			var xmlFileName = '${curXMLFileName}';
			var recordExist = '${recordExist}';

			var displayFieldA = new Array('${leaveTypeNameEn}','${leaveTypeShortNameEn}','${leaveTypeNameGu}','${leaveTypeShortNameGu}','${isBalMaintain}');

			if(recordExist=='true') //- check if Entry exists in hr_ess_leave_other_dtl. i.e. If any User has applied for this leave type
				 rowId = addDBDataInTable('leaveTypeTable','xmlTemp',displayFieldA,xmlFileName,'editDBRecord','','');
			if(recordExist=='false')
				 rowId = addDBDataInTable('leaveTypeTable','xmlTemp',displayFieldA,xmlFileName,'editDBRecord','deleteDBRecord','');
		</script>
</c:forEach>
	
</hdiits:form>	
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>