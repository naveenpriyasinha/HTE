
<%
try {
%>

<%@ include file="/WEB-INF/jsp/core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.rta.RtaCaption" var="commonLables"
	scope="request" />

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="amtDue" value="${resValue.amtDue}"></c:set>

<c:set var="advDtlParentObj" value="${resValue.hrRtaReqDtl1}"></c:set>
<c:set var="advDtlObj" value="${resValue.hrRtaReqDtl}"></c:set>

<c:set var="lArrObjTravel" value="${resValue.HrTravelDtls}"></c:set>
<c:set var="XmlFileTravel" value="${resValue.XmlFileTravel}"></c:set>

<c:set var="lArrObjTransport" value="${resValue.HrTransportDtls}"></c:set>
<c:set var="XmlFileTransport" value="${resValue.XmlFileTransport}"></c:set>



<script>




function showmeTravelDtl()
{				
	document.getElementById('rtaTravelTable').style.display='none';
	document.getElementById('travelDtl').style.display='none';
	document.getElementById('travelRequest').style.display='none';

	
}

function showmeTransportDtl()
{				
	document.getElementById('rtaTransportTable').style.display='none';
	document.getElementById('transportRequest').style.display='none';
}



function addDBDataInTableAttachmentRta(tableId, hiddenField, displayFieldArray, xmlFilePath, attachmentIds,formName,attachmentName,
			editMethodName, deleteMethodName, viewMethodName)
	{
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

		trow.id = 'row' + hiddenField + counter;
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';		
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+1).innerHTML = displayFieldArray[i];	
		}	
		
		if(document.getElementById("Incremented_rowNumber")==null)
		{
			
			var table = document.getElementById(tableId);
			
			table.rows[0].insertCell(eval(len+1)).innerHTML = "<INPUT type='hidden' name='Incremented_rowNumber' id='Incremented_rowNumber' value='1'/>";
			table.rows[0].cells[eval(len+1)].style.display = 'none';
		}
		var rowNumberForRow = document.getElementById("Incremented_rowNumber").value;
		
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
												 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id  + "','" + rowNumberForRow + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "','" + rowNumberForRow + "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow + 
												 "')>View</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "')>"+delCap+"</a>";
			
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+editCap+"</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + formName +  "','" + attachmentName + "','" + attachmentIds + "')>View</a>";
	
		trow.insertCell(len + 2).innerHTML = "<INPUT type='hidden' name='attachment" + hiddenField + "' id='attachment" + hiddenField + counter + "' value='" + attachmentIds + "' />";		
		trow.cells[len + 2].style.display = 'none';					
	
		trow.insertCell(len + 3).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "_rowNumber' value='" + rowNumberForRow + "'/>";
		trow.cells[len + 3].style.display = 'none';
		rowNumberForRow = parseInt(rowNumberForRow) + 1;
		document.getElementById("Incremented_rowNumber").value = rowNumberForRow;
	
		counter++;
	return rowNumberForRow - 1;
	}


function viewRecord(frmNameForAttachment,attachmentName,attachmentIds)
{
	
	removeRowFromTableattachmentBiometric(1,frmNameForAttachment);
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	}
	var attachmentString='&attachmentName='+attachmentName;
	var attachmentIdString='&attachmentId='+attachmentIds;

	var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + ''+ attachmentString + attachmentIdString + '&rowNumber='+1;
	xmlHttp.onreadystatechange=rtagetAttachment;
xmlHttp.open("POST",encodeURI(url),true);
xmlHttp.send(null);

}





function viewRecordT(frmNameForAttachment,attachmentName,attachmentIds)
{
	
	removeRowFromTableattachmentBiometricT(1,attachmentName);
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	}
	var attachmentString='&attachmentName='+attachmentName;
	var attachmentIdString='&attachmentId='+attachmentIds;

	var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + ''+ attachmentString + attachmentIdString + '&rowNumber='+1;
	xmlHttp.onreadystatechange=rtagetAttachment;
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(null);

}
function rtagetAttachment()
{
	if (xmlHttp.readyState == 4) 
  	{ 	
  		if (xmlHttp.status == 200) 
		{
			var decXML = xmlHttp.responseText;			
			populateAttachment(decXML,'viewRtaReq');
		}
	}
}

function getDateInProperFormat(v)
{
	if(v.indexOf("-")!=-1)
	{
		v=v.substring(0,10);				
		var splitDate=v.split("-");				
		var byr=splitDate[0];
		var bmo=splitDate[1];
		var bday=splitDate[2];				
		v= bday+'/'+bmo+'/'+byr;	
	}
	return v;
}

</script>




<hdiits:form name="viewRtaReq" validate="true"
	action="./hrms.htm?actionFlag=updateRtaStatus" method="post"
	encType="multipart/form-data">
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	<br>
	<!--
<c:if test="${advDtlObj.isAdvReimb=='1'}">
<table name="travelReimbDtl" id="travelReimbDtl" class="tabtable" border="0" width="100%">

<tr bgcolor="#386CB7">
		<td class="fieldLabel" colspan="5" align="center">
			<font color="#ffffff">
				<strong><u><fmt:message key="HRMS.rtaAdvDtl"/></u></strong>
			</font>
		</td>
	</tr>
	<tr>
 		<td>
 			<b><hdiits:caption captionid="HRMS.totalpayablerta" bundle="${commonLables}" id="totalPayableAmt"  />:</b>
 		</td>
 		
    	<td>
      		<hdiits:text captionid ="HRMS.totalpayablerta" style="background-color: lightblue;" readonly="true" name="totalPayableAmt" id="totalPayableAmt" default="${advDtlObj.totalPayableAmt}" />
   		</td>  
   	</tr>
   	<tr>
   		<td>
   			<b><fmt:message key="HRMS.remarks"></fmt:message></b>
   		</td>
		
		<td>
			
			<hdiits:hidden name="parentId" id="parentId" default="${advDtlObj.rtaId}" />
			<hdiits:hidden name="status" id="status" default="${advDtlObj.status}"/>
			<hdiits:textarea mandatory="false" rows="2" cols="17" default="${advDtlObj.remarks}" name="remarks1" id="remarks1" tabindex="7" caption="remarks"  />
		</td>
		
		<td>
   			<b><fmt:message key="HRMS.remarksByBranch"></fmt:message></b>
   		</td>
		
		<td>
			
			<hdiits:textarea mandatory="false" rows="2" cols="17" default="${advDtlObj.remarksByBranch}" name="remarksByBranch" id="remarksByBranch" tabindex="7"  caption="remarksByBranch"  />
		</td>
	
	</tr>
</table>
</c:if>	
-->
	<c:if test="${advDtlObj.isAdvReimb=='2'}">


		<hdiits:hidden name="fileId" caption="status"
			default="${advDtlObj.rtaId}" />
		<hdiits:hidden name="isAdvReimb" id="isAdvReimb"
			default="${advDtlObj.isAdvReimb}" />



		<c:if test="${not empty lArrObjTravel }">
			<table width="100%">

				<tr align="center" id="travelDtl">
					<td align="center" bgcolor="#386CB7"><strong><u><font
						color="white"><fmt:message key="HRMS.travelDtl" /></font></u></strong></td>
				</tr>
			</table>

			<table id="rtaTravelTable" name="rtaTravelTable" class="tabtable"
				border="1" align="center">
			<tr>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.DeparturePlace" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.DepartureDate" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.ArrivalPlace" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.ArrivalDate" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.DistanceInKm" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.TransportMode" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.ClassofAcc" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.NoOfFares" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.FaresPaid" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.total" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.Remarks" /></b>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.attachment" /></b></td>
				

				</tr>

			</table>
			<table name="travelRequest" id="travelRequest" class="tabtable"
				border="1" width="100%">
				<tr>
					<td class="fieldLabel" colspan="8"><jsp:include
						page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="attachmentBiometric" />
						<jsp:param name="formName" value="viewRtaReq" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Attachment" />
						<jsp:param name="multiple" value="Y" />
						<jsp:param name="rowNumber" value="1" />
					</jsp:include></td>
				</tr>
			</table>

			<c:forEach items="${lArrObjTravel}" var="HrTravelTuples"
				varStatus="x">
				<c:set var="curXMLTravelFileName" value="${XmlFileTravel[x.index]}"></c:set>
				<c:set var="attachmentId"
					value="${HrTravelTuples.cmnAttachmentMst.attachmentId}"></c:set>
				<c:set var="travelMode10"
					value="${HrTravelTuples.cmnLookupMstByModeOfTrans.lookupDesc}"></c:set>
				<c:set var="class10"
					value="${HrTravelTuples.cmnLookupMstByClassOfAccomod.lookupDesc}"></c:set>
				<c:set var="departurePlace" value="${HrTravelTuples.departurePlace}" />
				<fmt:formatDate value="${HrTravelTuples.departureDate}"
					pattern="dd/MM/yyyy" var="fromDate" />
				<c:set var="departureDate" value="${fromDate}" />
				<c:set var="arrivedPlace" value="${HrTravelTuples.arrivedPlace}" />
				<fmt:formatDate value="${HrTravelTuples.arrivedDate}"
					pattern="dd/MM/yyyy" var="todate" />
				<c:set var="arrivedDate" value="${todate}" />
				<c:set var="distanceInKm" value="${HrTravelTuples.distanceInKm}" />
				<c:set var="noOfPersonTravelling"
					value="${HrTravelTuples.noOfPersonTravelling}" />
				<c:set var="faresPaid" value="${HrTravelTuples.faresPaid}" />
				<c:set var="total" value="${HrTravelTuples.total}" />
				<c:set var="remarks" value="${HrTravelTuples.remarks}" />

				<script type="text/javascript">
		
			var xmlTravelFileName = '${curXMLTravelFileName}';
			
			var displayFieldATravel  = new Array('${departurePlace}','${departureDate}','${arrivedPlace}',
			'${arrivedDate}','${distanceInKm}','${travelMode10}','${class10}','${noOfPersonTravelling}',
			'${faresPaid}','${total}','${remarks}');
			addDBDataInTableAttachmentRta('rtaTravelTable','encXML',displayFieldATravel,xmlTravelFileName,
			'${attachmentId}','viewRtaReq','attachmentBiometric','', '','viewRecord');					
			</script>
			</c:forEach>

			<BR>
			<script>

document.getElementById('target_uploadattachmentBiometric').style.display='none';
document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='none';
</script>
		</c:if>



		<c:if test="${not empty lArrObjTransport}">

		<table width="100%">

				<tr align="center">
					<td align="center" colspan="9" bgcolor="#386CB7"><strong><u><font
						color="white"><fmt:message key="HRMS.transportdetail" /></font></u></strong></td>
				</tr>
			</table>
				
			<table id="rtaTransportTable" name="rtaTransportTable"
				class="tabtable" border="1" align="center">
				


				<tr>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.DeparturePlace" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.DepartureDate" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.ArrivalPlace" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.ArrivalDate" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.DistanceInKm" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.TransportMode" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.ClassofAcc" /></b></td>


					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.total" /></b></td>
					<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.attachment" /></b></td>
					

				</tr>

			</table>



			<table name="transportRequest" id="transportRequest" class="tabtable"
				border="1">
				<tr>
					<td class="fieldLabel" colspan="8"><jsp:include
						page="/WEB-INF/jsp/common/attachmentPage.jsp">
						<jsp:param name="attachmentName" value="attachmentBiometricT" />
						<jsp:param name="formName" value="viewRtaReq" />
						<jsp:param name="attachmentType" value="Document" />
						<jsp:param name="attachmentTitle" value="Attachment" />
						<jsp:param name="multiple" value="Y" />
						<jsp:param name="rowNumber" value="1" />
					</jsp:include></td>
				</tr>
			</table>

			<c:forEach items="${lArrObjTransport}" var="HrTransportTuples"
				varStatus="x">
				<c:set var="curXMLTransportFileName"
					value="${XmlFileTransport[x.index]}"></c:set>
				<c:set var="attachmentIdT"
					value="${HrTransportTuples.cmnAttachmentMst.attachmentId}"></c:set>
				<c:set var="travelModeT10"
					value="${HrTransportTuples.cmnLookupMstByModeOfTrans.lookupDesc}"></c:set>
				<c:set var="classT10"
					value="${HrTransportTuples.cmnLookupMstByClassOfAccomod.lookupDesc}"></c:set>
				<c:set var="departurePlaceT"
					value="${HrTransportTuples.departurePlace}" />
				<fmt:formatDate value="${HrTransportTuples.departureDate}"
					pattern="dd/MM/yyyy" var="fromDate" />
				<c:set var="departureDateT" value="${fromDate}" />
				<c:set var="arrivalPlaceT" value="${HrTransportTuples.arrivalPlace}" />
				<fmt:formatDate value="${HrTransportTuples.arrivalDate}"
					pattern="dd/MM/yyyy" var="todate" />
				<c:set var="arrivalDateT" value="${todate}" />
				<c:set var="distanceInKmT" value="${HrTransportTuples.distanceInKm}" />
				<c:set var="totalT" value="${HrTransportTuples.total}" />


				<script type="text/javascript">
		
			var xmlTransportFileName = '${curXMLTransportFileName}';
			
			var displayFieldATransport  = new Array('${departurePlaceT}','${departureDateT}','${arrivalPlaceT}',
			'${arrivalDateT}','${distanceInKmT}','${travelModeT10}','${classT10}','${totalT}');
			addDBDataInTableAttachmentRta('rtaTransportTable','encXMLT',displayFieldATransport,xmlTransportFileName,
			'${attachmentIdT}','viewRtaReq','attachmentBiometricT','', '','viewRecordT');					
			</script>
			</c:forEach>

			<script>
document.getElementById('target_uploadattachmentBiometricT').style.display='none';
document.getElementById('formTable1attachmentBiometricT').firstChild.firstChild.style.display='none';


</script>

		</c:if>

		<hdiits:hidden name="status" id="status"
			default="${advDtlObj.status}" />
		<table name="travelReimbDtl" id="travelReimbDtl" class="tabtable"
			border="0" width="100%">
			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="5" align="center"><font
					color="#ffffff"> <strong><u><fmt:message
					key="HRMS.rtaAdvReqDtl" /></u></strong> </font></td>
			</tr>
			<tr>
				<c:if test="${not empty advDtlParentObj}">

					<c:if test="${advDtlParentObj.status==1}">
						<td align="center"><b><fmt:message key="HRMS.advTaken" /></b></td>

						
					</c:if>
					<c:if test="${advDtlParentObj.status!=1}">
						<td align="center"><b><fmt:message key="HRMS.advReq" /></b></td>

					
					</c:if>
					<td><hdiits:number mandatory="true" floatAllowed="true" name="advTaken" tabindex="1"
						id="advTaken" caption="advTaken" size="5"
						default="${advDtlParentObj.totalPayableAmt}" readonly="true" style="background-color: lightblue;" />
					</td>
					<td align="right"><b><fmt:message key="HRMS.remarks"></fmt:message></b></td>
					<td align="center"><hdiits:hidden name="isAdvReimb" id="isAdvReimb"
						default="${advDtlObj.isAdvReimb}" /> <hdiits:hidden
						name="parentId" id="parentId" default="${advDtlObj.rtaId}" /> <hdiits:textarea
						mandatory="false" rows="5" cols="40" tabindex="2" 
						default="${advDtlParentObj.remarks}" name="remarks2"
						id="remarks2" caption="remarks" readonly="true" maxlength="2000" /></td>

				</c:if>

				<c:if test="${empty advDtlParentObj}">
					<td align="center"><b><fmt:message key="HRMS.advTaken" /></b></td>

					<td><hdiits:number mandatory="true" floatAllowed="true" tabindex="3"
						name="advTaken" id="advTaken" caption="advTaken" size="5"
						default="${advDtlObj.totalPayableAmt}" readonly="true" style="background-color: lightblue;" /></td>
				</c:if>



			</tr>

			<tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="5" align="center"><font
					color="#ffffff"> <strong><u><fmt:message
					key="HRMS.reimbAmtDtl" /></u></strong> </font></td>
			</tr>

			<tr>
				<td align="center"><b><fmt:message key="HRMS.grandTotal" /></b></td>
				<td><hdiits:number mandatory="true" floatAllowed="true" tabindex="4"
					name="grandTotal" id="grandTotal" caption="grandTotal" size="5"
					default="${advDtlObj.grantTotalFaresTrans}" readonly="true" style="background-color: lightblue;" /></td>

				<td align="right" style="display:none"><b><fmt:message
					key="HRMS.remarks" /></b></td>

				<td><hdiits:hidden name="remarks" id="remarks"
					caption="remarks" default="${advDtlObj.remarks}" /></td>


			</tr>
			<tr>
				<td align="center"><b><fmt:message key="HRMS.amtDue" /></b></td>
				<td><hdiits:number mandatory="true" floatAllowed="true" tabindex="5"
					name="amtDue" id="amtDue" caption="amtDue" size="5"
					default="${amtDue}" readonly="true" style="background-color: lightblue;" /></td>

			</tr>
			<tr>
				<td align="center"><b><fmt:message key="HRMS.sanctionAmt" /></b></td>
				<td><hdiits:number mandatory="true" floatAllowed="true" tabindex="6"
					name="sanctionAmt" id="sanctionAmt" caption="sanctionAmt" size="5"
					default="${advDtlObj.sanctionedAmt}" maxlength="10"/></td>

			</tr>

		</table>


	</c:if>
	<BR>



	<jsp:include page="../../../core/tabnavigation.jsp" />
	<hdiits:jsField name="testSantAmt" jsFunction="validateSanctionedAmt()"/>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>
</hdiits:form>
<script>
function validateSanctionedAmt()
{	
	if((document.forms[0].sanctionAmt.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmt"/>');
		document.forms[0].sanctionAmt.focus();
		return;
	}
	
	else if(isNaN(document.getElementById('sanctionAmt').value))
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.sanctionedAmtFormat"/>');
		document.forms[0].sanctionAmt.value='';
		document.forms[0].sanctionAmt.focus();
		return;
	}
	else
	{
		return true;
	}
}
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

	


