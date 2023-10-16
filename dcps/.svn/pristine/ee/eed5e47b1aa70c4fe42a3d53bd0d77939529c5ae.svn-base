<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@page import="com.tcs.sgv.common.utils.DateUtility"%>

<fmt:setBundle basename="resources.trng.TrngAttendedLables" var="trngAttendedLables" scope="request" />
<fmt:setBundle basename="resources.trng.trschLables" var="trschLables"	scope="request" />

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/></script>
<script type="text/javascript" src="script/common/addRecord.js" /></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="trainingMstList" value="${resValue.trainingMstList}" > </c:set>

<script type="text/javascript">





function editRecord(rowId)
{

	sendAjaxRequestForEdit(rowId, 'populateRecord');
}


function populateRecord() 
	{
	  if (xmlHttp.readyState == 4) 
	  { 	
	  		var decXML = xmlHttp.responseText;
			//alert(xmlHttp.responseText)
	  		var xmlDOM = getDOMFromXML(decXML);			
	  		document.forms[0].srNo.value = getXPathValueFromDOM(xmlDOM, 'srNo');
			

		if(getXPathValueFromDOM(xmlDOM, 'trngResult')!=null)
		{
			var trngrlt = getXPathValueFromDOM(xmlDOM,'trngResult');
			
			if(trngrlt == 'P')
			{
				document.forms[0].istrngpassed[0].checked = true;
			}
			else
			{
				document.forms[0].istrngpassed[1].checked = true;
			}	 
		}
		if(getXPathValueFromDOM(xmlDOM, 'trngRemarks')!=null)
		{
			document.forms[0].txtremarks.value = getXPathValueFromDOM(xmlDOM,'trngRemarks');
		}

		
		if(getXPathValueFromDOM(xmlDOM, 'hrTrScheduleDtl/orderNo')!=null)
		{
			document.forms[0].txtOrderNo.value = getXPathValueFromDOM(xmlDOM,'hrTrScheduleDtl/orderNo');
		}
		if(getXPathValueFromDOM(xmlDOM, 'hrTrScheduleDtl/batchNo')!=null)
		{
			document.forms[0].txtBatchNo.value = getXPathValueFromDOM(xmlDOM,'hrTrScheduleDtl/batchNo');
		}
		if(getXPathValueFromDOM(xmlDOM, 'hrTrScheduleDtl/hrTrTrainingMst/trainingName')!=null)
		{
			document.forms[0].txtTrainingName.value = getXPathValueFromDOM(xmlDOM,'hrTrScheduleDtl/hrTrTrainingMst/trainingName');
		}
		if(getXPathValueFromDOM(xmlDOM, 'hrTrScheduleDtl/hrTrTrainingMst/cmnLookupMstTrainingTypeLookupId/lookupName')!=null)
		{
			document.forms[0].txtTrainingType.value = getXPathValueFromDOM(xmlDOM,'hrTrScheduleDtl/hrTrTrainingMst/cmnLookupMstTrainingTypeLookupId/lookupName');
		}
		if(getXPathValueFromDOM(xmlDOM, 'hrTrScheduleDtl/startDt')!=null)
		{
			var startDate = getXPathValueFromDOM(xmlDOM,'hrTrScheduleDtl/startDt');
			var startDateyear = startDate.substring(0,4);
			var startDatemonth = startDate.substring(5,7);
			var startDateday = startDate.substring(8,10);
			var startDateDisplay = startDateday+"/"+startDatemonth+"/"+startDateyear;
			document.forms[0].dtStartDt.value = startDateDisplay;
		}
		if(getXPathValueFromDOM(xmlDOM, 'hrTrScheduleDtl/endDt')!=null)
		{
			var endDate = getXPathValueFromDOM(xmlDOM,'hrTrScheduleDtl/endDt');
			var endDateyear = endDate.substring(0,4);
			var endDatemonth = endDate.substring(5,7);
			var endDateday = endDate.substring(8,10);
			var endDateDisplay = endDateday+"/"+endDatemonth+"/"+endDateyear;
			document.forms[0].dtEndDt.value = endDateDisplay;
		}
			
		

		document.getElementById('btnAddtrngdtl').style.display = 'none';   	    
		document.getElementById('btnUpdtrngdtl').style.display = '';  
			   	    
       }
	}
function addtrngDetails()
{
	
	
	
	
	
	var trngdtlarray = new Array('hdnSchId','istrngpassed','txtremarks','Employee_ID_searchEmpInTrngAttended');

	addOrUpdateRecord('addRecoredTrng','attendedTrainings',trngdtlarray);

}

function addRecoredTrng()
{

	if (xmlHttp.readyState == 4)
  	{ 	
		
  		document.getElementById('txnAddSch').style.display='';	
  		var displayFieldArray = new Array('txtTrainingType','txtTrainingName','dtStartDt','dtEndDt','istrngpassed','txtremarks');
  		
  		addDataInTable('txnAddSch','encDirectorXML', displayFieldArray,'editRecord','deletetrngRecord');
  		resetDataDirector();
	}
}
function resetDataDirector()
{	
	/*var EmployeeSearchName= "searchEmpInTrngAttended";
	document.getElementById("Employee_Name_"+EmployeeSearchName).value='';
	document.getElementById("GPF_NM_"+EmployeeSearchName).value='';
	document.getElementById("Dsgn_NM_"+EmployeeSearchName).value='';
	document.getElementById("Police_ST_NM_"+EmployeeSearchName).value='';
	document.getElementById("DISTRICT_NM_"+EmployeeSearchName).value='';
	document.getElementById("Employee_srchNameText_"+EmployeeSearchName).value = '';*/

	document.getElementById('txtTrainingType').value='';
	document.getElementById('txtTrainingName').value = '';
	document.getElementById('dtStartDt').value = '';
	document.getElementById('dtEndDt').value = '';
	document.getElementById('txtOrderNo').value = '';
	document.getElementById('txtBatchNo').value = '';

	document.frmAttended.istrngpassed[0].checked = false;
	document.frmAttended.istrngpassed[1].checked = false;
	document.getElementById('txtremarks').value = '';
	
	
	
	
	
				
}
function updatetrng()
{
	/*if(document.frmPostmortem.injurywhen.selectedIndex==0){
	alert('Please Select a type of injury before updating.');
	return false;
}*/
var UpdatedArray = new Array('hdnSchId','istrngpassed','txtremarks','Employee_ID_searchEmpInTrngAttended','srNo');
addOrUpdateRecord('updateTrngRecord','attendedTrainings',UpdatedArray);

			
}
function updateTrngRecord() 
{

  if (xmlHttp.readyState == 4) 
		{ 	

	  		var displayFieldArray = new Array('txtTrainingType','txtTrainingName','dtStartDt','dtEndDt','istrngpassed','txtremarks');
		
		updateDataInTable('encDirectorXML',displayFieldArray);
		resetDataDirector();
		document.getElementById('btnAddtrngdtl').style.display='';
		document.getElementById('btnUpdtrngdtl').style.display='none';
		 }
}	

function deletetrngRecord(rowId)	
{
var result = deleteRecord(rowId);
}

</script>




<hdiits:form name="frmAttended" validate="true" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="attendedTrainings1"/>
<hdiits:hidden name="editFlag" default="Y"/>

<hdiits:hidden name="emp_id" default="0"/>
<hdiits:hidden name="srNo" default=""/>
<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.TrngAttended" bundle="${trngAttendedLables}"></hdiits:caption> </a></li>
		</ul>
</div>

<div id="tcontent1" class="tabcontent" tabno="0">
<TABLE class="tabtable">
<fmt:message key="TR.Search_Emp" bundle="${trngAttendedLables}" var="title"></fmt:message>
<tr>  
	<td class="fieldLabel" colspan="6">
	<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
		<jsp:param name="SearchEmployee" value="searchEmpInTrngAttended"/> 
		<jsp:param name="searchEmployeeTitle" value="${title}"/> 
		<jsp:param name="mandatory" value="Yes"/> 					
	</jsp:include>
	</td>
</tr>
<fmt:message key="TR.SEARCH_TRAINING_SCHEDULE"  bundle="${trschLables}" var="scheduleTitle"></fmt:message>
<tr>
			<td class="fieldLabel" colspan = "6">
			<jsp:include page="./trngSchSearchForTrngAttended.jsp">
				<jsp:param name="searchScheduleTitle" value="${scheduleTitle}"/>
				<jsp:param name="mandatory" value="Yes" />
			</jsp:include>
			</td>		
</tr>
<tr>
						 
	<td class="fieldLabel" width="5%">
			<hdiits:caption captionid="TR.ispassed" bundle="${trngAttendedLables}" />
	</td>
	<td class="fieldLabel" width="25%">
			<hdiits:radio name="istrngpassed" id="istrngpassed" value='P' bundle="${trngAttendedLables}" captionid="TR.pass"/>
			<hdiits:radio name="istrngpassed" value='F'  bundle="${trngAttendedLables}" captionid="TR.fail"/>
	</td>
</tr>
<tr>
	<td class="fieldLabel" width="5%">
			      <hdiits:caption captionid="TR.remarks" bundle="${trngAttendedLables}"/>
	</td>
					
	<td class="fieldLabel" width="25%">
				 <hdiits:textarea name="txtremarks" captionid="TR.remarks" rows="5" cols="50" />
	</td>
</tr>
</table>

<table id="btnAddtrngdtl" class="tabtable" border=0 style="display">
		<tr>
			<TD class="fieldLabel" align="center" colspan="4"><hdiits:button type="button" name="addtrngdtl" id="multitrngAdd" captionid="TR.ADDDETAIL" bundle="${trngAttendedLables}" onclick="addtrngDetails()"/></TD>
			
		</tr>
</table>
<table id="btnUpdtrngdtl" class="tabtable" border=0 style="display:none">
	<tr>
		<TD class="fieldLabel" align="center" colspan="4"><hdiits:button type="button" name="updtrngdtl" id="multitrngUpd" captionid="TR.UPDATEDETAIL" bundle="${trngAttendedLables}" onclick="updatetrng()" /></TD> 
	</tr>
</table>

<table id="txnAddSch" align="center" border="3" width="100%" class="datatable" style="display:none">
		<tr>
			<td class="datatableheader" width="14%"><hdiits:caption captionid="TR.TRAININGTYPE"  bundle="${trschLables}" ></hdiits:caption></td>
			<td class="datatableheader" width="14%"><hdiits:caption captionid="TR.name"  bundle="${trngAttendedLables}" ></hdiits:caption></td>
			 <td class="datatableheader"><b><hdiits:caption captionid="TR.STARTDATE" bundle="${trschLables}"/></b></td>
          	 <td class="datatableheader"><b><hdiits:caption captionid="TR.ENDDATE" bundle="${trschLables}"/></b></td>
             <td class="datatableheader"><b><hdiits:caption captionid="TR.ispassed" bundle="${trngAttendedLables}"/></b></td>         
             <td class="datatableheader"><b><hdiits:caption captionid="TR.remarks" bundle="${trngAttendedLables}"/></b></td>
 		    <td class="datatableheader" width="14%"><hdiits:caption captionid="TR.EDITDELETE"  bundle="${trngAttendedLables}" ></hdiits:caption></td>  
 		  
 		    	
			
		</tr>
		</table>

	
<jsp:include page="../../core/tabnavigation.jsp" /> 
</div>


<script	type="text/javascript">
		initializetabcontent("maintab")
</script> 

		
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>

</hdiits:form>
 