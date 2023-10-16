<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hr.pension.Pension"
	var="pensiontotalServDtlsLabel" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<script type="text/javascript"
	src="<c:url value="script/hrms/hr/pension/PensionTotalSrvc.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"
	src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="script/common/tagLibValidation.js"/>"></script>
<script>
var ApprovedReqOpen=true;
var nextId=1,globalNextId=0;
var xmlFileNameData,globalTableId;
var mappingArrRowIdAndTableIdCount=0;
var mappingArrRowIdAndTableId = new Array();
var deletArrList = new Array();
var deletArrListCount = 0;
var deletArrList_SUS = new Array();
var deletArrListCount_SUS = 0;
var dbDataRow = new Array();
var dbDataRowCount = 0; 
var minusValue_Year=0,minusValue_Month=0,minusValue_Day=0;
var glbAfterDateSelect = '%^#$';

function SaveServiceWindow()
{	
	opener.document.frmPenBranchReq.totalServiceScreenOnFlag.value = 'false';	
	opener.document.frmPenBranchReq.pen_total_Srvc_Year.value=document.getElementById('final_Year').value;
	opener.document.frmPenBranchReq.pen_total_Srvc_Month.value=document.getElementById('final_Month').value;
	opener.document.frmPenBranchReq.pen_total_Srvc_Day.value=document.getElementById('final_Day').value;
	document.frmPensionServDtls.action="hrms.htm?actionFlag=TotalServDataSubmit&mappingArr="+mappingArrRowIdAndTableId+"&delete="+deletArrList+"&delete_sus="+deletArrList_SUS;
	document.frmPensionServDtls.submit();	
//	startProcess();
//	window.setTimeout('onSubmitClick()',5000);
	
}
function onSubmitClick()
{
//	endProcess();
//	window.close();	
}
function closeServiceWindow()
{
	opener.document.frmPenBranchReq.totalServiceScreenOnFlag.value = 'false';	
	window.close();
}
function validateToAndFromDate()
{		
	var bldatediff = false ;
	var strDate1 = document.getElementById('fromDate').value;
	var strDate2 = document.getElementById('toDate').value;				
	if(strDate1!='' && strDate2!='')
	{				
		//Start date split to UK date format and add 31 days for maximum datediff 
		strDate1 = strDate1.split("/"); 
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		starttime = new Date(starttime.valueOf()); 
		
		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		endtime = new Date(endtime.valueOf()); 								
		if(endtime >= starttime) 
		{ 		
			bldatediff = true;
		} 
		else
		{
			document.getElementById('fromDate').value='';	
			document.getElementById('toDate').value='';	
			alert("<fmt:message  bundle="${pensiontotalServDtlsLabel}" key="Pension.ProperToDate"/>");
		}
		return bldatediff;
	}	
}
function hideAttachment(cmbValue)
{
	if("SUS"==cmbValue.value)
	{
		document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='none';
	}
	else
	{
		document.getElementById('formTable1attachmentBiometric').firstChild.firstChild.style.display='';
	}
}
</script>
</head>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="totalServList" value="${resValue.totalServList}"></c:set>
<c:set var="breakServList" value="${resValue.breakServList}"></c:set>
<c:set var="suspensionServList" value="${resValue.suspensionServList}"></c:set>
<c:set var="eolServList" value="${resValue.eolServList}"></c:set>
<c:set var="xmlEOL" value="${resValue.xmlEOLData}"></c:set>
<c:set var="xmlBreak" value="${resValue.xmlBreakData}"></c:set>
<c:set var="xmlSusData" value="${resValue.xmlSUSData}"></c:set>
<fmt:formatDate value="${resValue.toDate}" pattern="dd/MM/yyyy"
	var="toDate" />
<fmt:formatDate value="${resValue.fromDate}" pattern="dd/MM/yyyy"
	var="fromDate" />
<!-- onBlur="checkForChildWindow();" -->
<BODY onunload="closeServiceWindow();">
<hdiits:form name="frmPensionServDtls" validate="true"
	action="hdiits.htm" encType="multipart/form-data" method="post">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <fmt:message key="Pension.totalSrvc" bundle="${pensiontotalServDtlsLabel}" />
		</b></a></li>
	</ul>
	</div>
	<div id="PensionSalaryReq" name="PensionSalaryReq">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="emp_totalsrvc_field1" titleCaptionId="Pension.totalSrvc" bundle="${pensiontotalServDtlsLabel}">				
	<table class="tabtable" BGCOLOR="WHITE">
		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="Pension.SelType" bundle="${pensiontotalServDtlsLabel}" /></b></td>
			<td class="fieldLabel"><hdiits:select
				captionid="Pension.SelType" bundle="${pensiontotalServDtlsLabel}"
				id="selType" name="selType" mandatory="true"
				validation="sel.isrequired" onchange="hideAttachment(this)">
				<hdiits:option value="Select">--<fmt:message key="Pension.Select" bundle="${pensiontotalServDtlsLabel}" />--</hdiits:option>
				<hdiits:option value="EOL">
					<fmt:message key="Pension.CmbEOL" bundle="${pensiontotalServDtlsLabel}" />
				</hdiits:option>
				<hdiits:option value="SUS">
					<fmt:message key="Pension.CmbSuspension" bundle="${pensiontotalServDtlsLabel}" />					
				</hdiits:option>
				<hdiits:option value="LWP">
					<fmt:message key="Pension.CmbBreak" bundle="${pensiontotalServDtlsLabel}" />					
				</hdiits:option>
			</hdiits:select></td>
		</tr>
		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="Pension.FromDate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
			<td class="fieldLabel"><hdiits:dateTime name="fromDate"
				mandatory="true" validation="txt.isrequired,txt.isdt"
				captionid="Pension.FromDate" bundle="${pensiontotalServDtlsLabel}"
				afterDateSelect="validateToAndFromDate();" /></td>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="Pension.Todate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
			<td class="fieldLabel"><hdiits:dateTime name="todate"
				mandatory="true" validation="txt.isrequired,txt.isdt"
				captionid="Pension.Todate" bundle="${pensiontotalServDtlsLabel}"
				afterDateSelect="validateToAndFromDate();" /></td>
		</tr>
		<tr>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="Pension.Remark" bundle="${pensiontotalServDtlsLabel}" /></b></td>
			<td colspan="4" class="fieldLabel"><hdiits:textarea
				name="remarksData" id="remarksData" validation="txt.isrequired"
				captionid="Pension.Remark" bundle="${pensiontotalServDtlsLabel}"
				cols="100" mandatory="true"></hdiits:textarea></td>
		</tr>
	</table>
	</hdiits:fieldGroup>
	<table align="center" width="100%">
		<tr>
			<td class="fieldLabel"><jsp:include
				page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="attachmentBiometric" />
				<jsp:param name="formName" value="frmPensionServDtls" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Attachment" />
				<jsp:param name="multiple" value="Y" />
				<jsp:param name="rowNumber" value="1" />
				<jsp:param name="removeAttachmentFromDB" value="N" />
			</jsp:include></td>
		</tr>
	</table>	
	<BR>
	<center><hdiits:button name="btnAdd" id="btnAdd" type="button" captionid="Button.Add" bundle="${pensiontotalServDtlsLabel}" onclick="AddOrUpdateME('btnAdd')" title="Add" /> 
		<hdiits:button	name="btnSave" id="btnSave" type="button" captionid="Button.Save" bundle="${pensiontotalServDtlsLabel}" onclick="AddOrUpdateME('btnSave')"  title="Save" /> 
		<hdiits:button name="btnCloseView" id="btnCloseView" type="button" style="display:none" value="Close View" onclick="closeViewData()"  /></center>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="emp_totalsrvc_field2" titleCaptionId="Pension.totalPensionSrvc" bundle="${pensiontotalServDtlsLabel}">						
		<table id="totalSalaryTable" align="center" width="85%" style="border-collapse: collapse;" borderColor="BLACK"  border=1>
			<tr bgcolor="#C9DFFF">
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.FromDate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Todate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.YEAR" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.MONTH" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.DAY" bundle="${pensiontotalServDtlsLabel}" /></b></td>
			</tr>
			<tr>
				<td class="fieldLabel"><c:out value="${fromDate}"></c:out></td>
				<td class="fieldLabel"><c:out value="${toDate}"></c:out></td>
				<td class="fieldLabel"><c:out value="${resValue.Total_ServYear}"></c:out></td>
				<td class="fieldLabel"><c:out value="${resValue.Total_ServMon}"></c:out></td>
				<td class="fieldLabel"><c:out value="${resValue.Total_ServDay}"></c:out></td>
			</tr>
		</table>
	</hdiits:fieldGroup>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="emp_totalsrvc_field3" titleCaptionId="Pension.extraOrdinary" bundle="${pensiontotalServDtlsLabel}">
		<table id="extraOrdinaryLeaveTable" name="extraOrdinaryLeaveTable" style="border-collapse: collapse;" 
			borderColor="BLACK"  border=1 align="center" width="85%">
			<tr bgcolor="#C9DFFF">
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.FromDate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Todate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.YEAR" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.MONTH" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.DAY" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Remark" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Links" bundle="${pensiontotalServDtlsLabel}" /></b></td>
			</tr>
		</table>
	</hdiits:fieldGroup>	
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="emp_totalsrvc_field4" titleCaptionId="Pension.suspensionPeriod" bundle="${pensiontotalServDtlsLabel}">		
		<table id="suspensionPeriodTable" style="border-collapse: collapse;" borderColor="BLACK"  border=1 align="center" width="85%">
			<tr bgcolor="#C9DFFF">
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.FromDate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Todate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.YEAR" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.MONTH" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.DAY" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Remark" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Links" bundle="${pensiontotalServDtlsLabel}" /></b></td>
			</tr>
		</table>
	</hdiits:fieldGroup>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="emp_totalsrvc_field5" titleCaptionId="Pension.breakInSrvc" bundle="${pensiontotalServDtlsLabel}">		
		<table id="brakInSrvcTable" style="border-collapse: collapse;" borderColor="BLACK"  border=1 align="center" width="85%">
			<tr bgcolor="#C9DFFF">
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.FromDate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Todate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.YEAR" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.MONTH" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.DAY" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Remark" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Links" bundle="${pensiontotalServDtlsLabel}" /></b></td>
			</tr>
		</table>
	</hdiits:fieldGroup>
	<BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="emp_totalsrvc_field5" titleCaptionId="Pension.pensionSrvcSummry" bundle="${pensiontotalServDtlsLabel}">				
		<table id="otherNonPensionableTable" style="border-collapse: collapse;" borderColor="BLACK"  border=1 align="center" width="85%">
			<tr bgcolor="#C9DFFF">
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.Details" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.YEAR" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.MONTH" bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><b><hdiits:caption
					captionid="Pension.DAY" bundle="${pensiontotalServDtlsLabel}" /></b></td>
			</tr>
			<tr>
				<td class="fieldLabel" bgcolor="#C9DFFF" align="center"><b><hdiits:caption
					captionid="Pension.totalPensionSrvc"
					bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><hdiits:text size="4"
					readonly="true" maxlength="4" name="total_Year" id="total_Year"
					default="0" /></td>
				<td class="fieldLabel" align="center"><hdiits:text size="2"
					readonly="true" maxlength="2" name="total_Month" id="total_Month"
					default="0" /></td>
				<td class="fieldLabel" align="center"><hdiits:text size="2"
					readonly="true" maxlength="2" name="total_Day" id="total_Day"
					default="0" /></td>
			</tr>
			<tr>
				<td class="fieldLabel"  bgcolor="#C9DFFF" align="center"><b><hdiits:caption
					captionid="Pension.totalNonPensionSrvc"
					bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><hdiits:text size="4"
					maxlength="4" readonly="true" name="min_Year" id="min_Year" /></td>
				<td class="fieldLabel" align="center"><hdiits:text size="2"
					maxlength="2" readonly="true" name="min_Month" id="min_Month" /></td>
				<td class="fieldLabel" align="center"><hdiits:text size="2"
					maxlength="2" readonly="true" name="min_Day" id="min_Day" /></td>
			</tr>
			<tr>
				<td class="fieldLabel"  bgcolor="#C9DFFF" align="center"><b><hdiits:caption
					captionid="Pension.PensionableSrvc(1-2)"
					bundle="${pensiontotalServDtlsLabel}" /></b></td>
				<td class="fieldLabel" align="center"><hdiits:text size="4"
					maxlength="4" readonly="true" name="final_Year" id="final_Year"
					default="" /></td>
				<td class="fieldLabel" align="center"><hdiits:text size="2"
					maxlength="2" readonly="true" name="final_Month" id="final_Month"
					default="" /></td>
				<td class="fieldLabel" align="center"><hdiits:text size="2"
					maxlength="2" readonly="true" name="final_Day" id="final_Day"
					default="" /></td>
			</tr>
		</table>
	</hdiits:fieldGroup>
	<BR>
	<center style="display:none" id="msgCenter"><BR>
	<b><hdiits:caption captionid="Pension.ReqAppMsg"
		bundle="${pensiontotalServDtlsLabel}" /></b> <BR>
	<BR>
	<hdiits:button name="btnCloseMsg" onclick="closeServiceWindow();"
		value="Close" type="button" /></center>
	<center id="btnCenter"><hdiits:button name="btnSubmit"
		onclick="SaveServiceWindow();" value="Submit" type="button" /> <hdiits:button
		name="btnClose" onclick="closeServiceWindow();" value="Close"
		type="button" /></center>
	<BR>
	<hdiits:hidden name="pensionChildOpenFlag" id="pensionChildOpenFlag"
		default="false" /> <hdiits:hidden name="editFlag" id="editFlag"
		default="false" /> <hdiits:hidden name="reqId" id="reqId" /> <hdiits:hidden
		name="srNo" id="srNo" default="0" /> <hdiits:hidden name="Year_No"
		id="Year_No" default="0" /> <hdiits:hidden name="Mon_No" id="Mon_No"
		default="0" /> <hdiits:hidden name="Day_No" id="Day_No" default="0" />
	</div>
	</div>
	<script type="text/javascript">	
	document.getElementById('btnSave').disabled=true;
	document.getElementById('btnCloseView').disabled=true;
	initializetabcontent("maintab")
	opener.document.frmPenBranchReq.totalServiceScreenOnFlag.value = 'true';	
	document.getElementById('fromDate').readOnly=true;
	document.getElementById('toDate').readOnly=true;	
	document.getElementById('reqId').value=opener.document.frmPenBranchReq.reqId.value;
	if(opener.document.frmPenBranchReq.actionType.value!='P')
	{
		disable();
		ApprovedReqOpen=false;
		if(opener.document.frmPenBranchReq.actionType.value!='C')
		{				
			document.getElementById('msgCenter').style.display='';
		}
		document.getElementById('btnCloseView').style.display='none';
		document.getElementById('btnCenter').style.display='none';		
		document.getElementById('btnCloseView').disabled=true;				
		document.getElementById('fromDate').disabled=false;
		document.getElementById('toDate').disabled=false;
	}
</script>
	<script>
	document.getElementById('total_Year').value='${resValue.Total_ServYear}';
	document.getElementById('total_Month').value='${resValue.Total_ServMon}';
	document.getElementById('total_Day').value='${resValue.Total_ServDay}';		
</script>

	<c:if test="${not empty  eolServList}">
		<c:forEach items="${eolServList}" var="lArrObj" varStatus="x">
			<c:set var="curXMLFileName" value="${xmlEOL[x.index]}"></c:set>
			<c:set var="status" value="${lArrObj.status}" />
			<c:set var="remarks" value="${lArrObj.remarks}" />
			<c:forEach items="${lArrObj.hrEssLeaveOtherDtls}" var="OtherDtlsObj">
				<c:set var="attachmentId"
					value="${OtherDtlsObj.cmnAttachmentMst.attachmentId}"></c:set>
				<fmt:formatDate value="${OtherDtlsObj.sanctionFromdate}" pattern="dd/MM/yyyy"
					var="fromDate" />
				<fmt:formatDate value="${OtherDtlsObj.sanctionTodate}" pattern="dd/MM/yyyy"
					var="toDate" />
			</c:forEach>	
			<script>
		var xmlFileName = '${curXMLFileName}';					
		var remarks= '${remarks}';				
		for(var i=0;i<remarks.length;i++)
		{
			remarks = remarks.replace("&amp;#x0D;"," ");
			remarks = remarks.replace("&#x0D;"," ");
		}
		if(remarks==''){remarks='-';}
		var dateDiff = getDateDiffInString('${fromDate}','${toDate}');
		var minValue = dateDiff.split('~');	
		var year = minValue[0];
		var mon = minValue[1];
		var day = minValue[2];
		var displayFieldA  = new Array('${fromDate}','${toDate}',year,mon,day,remarks);		
		if('${status}'==4)	
		{
			addDBDataInTableAttachment('extraOrdinaryLeaveTable','encXML',displayFieldA,xmlFileName,'${attachmentId}','editRecordForDB', 'DeleteRecordFromTable','');
			mappingArrRowIdAndTableId[mappingArrRowIdAndTableIdCount]="rowencXML"+parseInt(counter-1)+"/extraOrdinaryLeaveTable/encXML";
			mappingArrRowIdAndTableIdCount=mappingArrRowIdAndTableIdCount+1;			
		}
		else
		{
			addDBDataInTableAttachment('extraOrdinaryLeaveTable','addedencXML',displayFieldA,xmlFileName,'${attachmentId}','','','viewThisData');
			mappingArrRowIdAndTableId[mappingArrRowIdAndTableIdCount]="rowaddedencXML"+parseInt(counter-1)+"/extraOrdinaryLeaveTable/addedencXML";
			mappingArrRowIdAndTableIdCount=mappingArrRowIdAndTableIdCount+1;			
		}		
	</script>
		</c:forEach>
	</c:if>
	<c:if test="${not empty  suspensionServList}">
		<c:forEach items="${suspensionServList}" var="lArrObj" varStatus="x">
			<c:set var="curXMLFileName" value="${xmlSusData[x.index]}"></c:set>
			<c:set var="status" value="${lArrObj.applicationType}" />
			<c:set var="remarks" value="${lArrObj.remarks}" />
			<c:set var="attachmentId" value=""></c:set>
			<fmt:formatDate value="${lArrObj.suspenFromDate}"
				pattern="dd/MM/yyyy" var="fromDate" />
			<fmt:formatDate value="${lArrObj.suspenToDate}" pattern="dd/MM/yyyy"
				var="toDate" />
			<script>
		var xmlFileName = '${curXMLFileName}';					
		var remarks= '${remarks}';				
		for(var i=0;i<remarks.length;i++)
		{
			remarks = remarks.replace("&amp;#x0D;"," ");
			remarks = remarks.replace("&#x0D;"," ");
		}
		if(remarks==''){remarks='-';}
		var dateDiff = getDateDiffInString('${fromDate}','${toDate}');
		var minValue = dateDiff.split('~');	
		var year = minValue[0];
		var mon = minValue[1];
		var day = minValue[2];
		var displayFieldA  = new Array('${fromDate}','${toDate}',year,mon,day,remarks);		
		if('${status}'==4)	
		{
			addDBDataInTableAttachment('suspensionPeriodTable','encXML_sus',displayFieldA,xmlFileName,'${attachmentId}','editRecordForDB', 'DeleteRecordFromTable','');
			mappingArrRowIdAndTableId[mappingArrRowIdAndTableIdCount]="rowencXML_sus"+parseInt(counter-1)+"/suspensionPeriodTable/encXML_sus";
			mappingArrRowIdAndTableIdCount=mappingArrRowIdAndTableIdCount+1;			
		}
		else
		{
			addDBDataInTableAttachment('suspensionPeriodTable','addedencXML',displayFieldA,xmlFileName,'${attachmentId}','','','viewThisData');
			mappingArrRowIdAndTableId[mappingArrRowIdAndTableIdCount]="rowaddedencXML"+parseInt(counter-1)+"/suspensionPeriodTable/addedencXML";
			mappingArrRowIdAndTableIdCount=mappingArrRowIdAndTableIdCount+1;
		}
	</script>
		</c:forEach>
	</c:if>

	<c:if test="${not empty  breakServList}">
		<c:forEach items="${breakServList}" var="lArrObj" varStatus="x">
			<c:set var="curXMLFileName" value="${xmlBreak[x.index]}"></c:set>
			<c:set var="status" value="${lArrObj.status}" />
			<c:set var="remarks" value="${lArrObj.remarks}" />
			<c:forEach items="${lArrObj.hrEssLeaveOtherDtls}" var="OtherDtlsObj">
				<c:set var="attachmentId"
					value="${OtherDtlsObj.cmnAttachmentMst.attachmentId}"></c:set>			
				<fmt:formatDate value="${OtherDtlsObj.sanctionFromdate}" pattern="dd/MM/yyyy"
					var="fromDate" />
				<fmt:formatDate value="${OtherDtlsObj.sanctionTodate}" pattern="dd/MM/yyyy"
					var="toDate" />
			</c:forEach>		
			<script>
		var xmlFileName = '${curXMLFileName}';					
		var remarks= '${remarks}';				
		for(var i=0;i<remarks.length;i++)
		{
			remarks = remarks.replace("&amp;#x0D;"," ");
			remarks = remarks.replace("&#x0D;"," ");
		}
		if(remarks==''){remarks='-';}
		var dateDiff = getDateDiffInString('${fromDate}','${toDate}');
		var minValue = dateDiff.split('~');	
		var year = minValue[0];
		var mon = minValue[1];
		var day = minValue[2];
		var displayFieldA  = new Array('${fromDate}','${toDate}',year,mon,day,remarks);		
		if('${status}'==4)	
		{
			addDBDataInTableAttachment('brakInSrvcTable','encXML',displayFieldA,xmlFileName,'${attachmentId}','editRecordForDB', 'DeleteRecordFromTable','');
			mappingArrRowIdAndTableId[mappingArrRowIdAndTableIdCount]="rowencXML"+parseInt(counter-1)+"/brakInSrvcTable/encXML";
			mappingArrRowIdAndTableIdCount=mappingArrRowIdAndTableIdCount+1;			
		}
		else
		{
			addDBDataInTableAttachment('brakInSrvcTable','addedencXML',displayFieldA,xmlFileName,'${attachmentId}','','','viewThisData');
			mappingArrRowIdAndTableId[mappingArrRowIdAndTableIdCount]="rowaddedencXML"+parseInt(counter-1)+"/brakInSrvcTable/addedencXML";
			mappingArrRowIdAndTableIdCount=mappingArrRowIdAndTableIdCount+1;			
		}		
	</script>
		</c:forEach>
	</c:if>
</hdiits:form>
<script>
	var dateDiff = LogicForMinusValue();
	var minValue = dateDiff.split('~');
	document.getElementById('min_Year').value=minValue[0];
	document.getElementById('min_Month').value=minValue[1];
	document.getElementById('min_Day').value=minValue[2];
	dateDiff = giveMeDateDiff(minValue[0],minValue[1],minValue[2]);
	minValue = dateDiff.split('~');
	var year = parseInt(document.getElementById('total_Year').value)-minValue[0];
	var mon = parseInt(document.getElementById('total_Month').value)-minValue[1];
	var day = parseInt(document.getElementById('total_Day').value)-minValue[2];				
	dateDiff = giveMeDateDiff(year,mon,day);
	minValue = dateDiff.split('~');	
	document.getElementById('final_Year').value=minValue[0];
	document.getElementById('final_Month').value=minValue[1];
	document.getElementById('final_Day').value=minValue[2];	
</script>
<hdiits:validate locale="${locale}"
	controlNames="selType,remarksData,fromDate,todate" />
</BODY>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
