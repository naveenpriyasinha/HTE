<%try { %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<c:set var="rowId" value="${resValue.rowId}"></c:set>
<c:set var="userId" value="${resValue.userId}"></c:set>
<c:set var="breakServList" value="${resValue.breakServList}"></c:set>
<c:set var="xmlBreak" value="${resValue.xmlBreakData}"></c:set>
<fmt:formatDate var="proStartDate" pattern="dd/MM/yyyy" value="${resValue.startDate}" type="date" />
<fmt:formatDate var="proEndDate" pattern="dd/MM/yyyy" value="${resValue.endDate}" type="date" />

<fmt:setBundle basename="resources.hr.probation.Probation" var="pensiontotalServDtlsLabel" scope="request" />
<fmt:setBundle basename="resources.hr.probation.AlertMessages" var="CNFMAlerts" scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<script type="text/javascript" src="<c:url value="script/hrms/hr/pension/PensionTotalSrvc.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/probation/probation.js"/>"></script>

<script>
	var proStartDate="${proStartDate}";
	var proEndDate="${proEndDate}";
	var updateLwpAlertArr = new Array();
	updateLwpAlertArr[0]="<fmt:message  bundle="${CNFMAlerts}" key="CNFM.Between"/>";
	updateLwpAlertArr[1]="<fmt:message  bundle="${CNFMAlerts}" key="CNFM.ProperToDate"/>";
</script>
</head>


<BODY  onunload="closeServiceWindow();">
<hdiits:form name="frmPensionServDtls" validate="true" action="" encType="multipart/form-data" method="POST">
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<fmt:message key="Pension.totalSrvc" bundle="${pensiontotalServDtlsLabel}"></fmt:message>
		</b></a></li>
</ul>
</div>
<div id="PensionSalaryReq" name="PensionSalaryReq">
		<div id="tcontent1" class="tabcontent" tabno="0">
<table class="tabtable" width="100%">
	<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="5" align="center"><font color="#ffffff">
			<strong><u><fmt:message key="Pension.totalSrvc" bundle="${pensiontotalServDtlsLabel}"></fmt:message></u></strong></font></td>
	</tr>
</table>
<BR>
<table class="tabtable" BGCOLOR="WHITE" width="100%">
	<tr>
		<td class="fieldLabel">
			<hdiits:hidden id="selType" name="selType" default="LWP" />
		</td>
	</tr>
	<tr>
		<td class="fieldLabel"><b><hdiits:caption captionid="Pension.FromDate" bundle="${pensiontotalServDtlsLabel}" /></b></td>		
		<td class="fieldLabel"><hdiits:dateTime name="fromDate" mandatory="true" validation="txt.isrequired,txt.isdt"  captionid="Pension.FromDate" bundle="${pensiontotalServDtlsLabel}"/></td>		
		<td class="fieldLabel"><b><hdiits:caption captionid="Pension.Todate" bundle="${pensiontotalServDtlsLabel}" /></b></td>		
		<td class="fieldLabel"><hdiits:dateTime name="todate" mandatory="true" validation="txt.isrequired,txt.isdt" captionid="Pension.Todate" bundle="${pensiontotalServDtlsLabel}"/></td>		
	</tr>
	<tr>
		<td class="fieldLabel"><b><hdiits:caption captionid="Pension.Remark" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td colspan="4" class="fieldLabel"><hdiits:textarea name="remarksData" id="remarksData" validation="txt.isrequired" captionid="Pension.Remark" bundle="${pensiontotalServDtlsLabel}"  cols="100" mandatory="true"></hdiits:textarea></td>
	</tr>	
</table>
<table class="tabtable" align="center" width="90%">
		<tr>
			<td class="fieldLabel">
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="attachmentBiometric" />
				<jsp:param name="formName" value="frmPensionServDtls" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Attachment" />			
				<jsp:param name="multiple" value="Y" />
				<jsp:param name="rowNumber" value="1" />
				<jsp:param name="removeAttachmentFromDB" value="N" />
			</jsp:include>
			</td>
		</tr>
</table>		
<BR>
<center>
	<hdiits:button name="btnAdd" id="btnAdd" type="button" captionid="CNFM.Add" bundle="${pensiontotalServDtlsLabel}"  onclick="setLWP();AddOrUpdateME('btnAdd');"/>	
	<hdiits:button name="btnSave" id="btnSave" type="button" captionid="CNFM.Save" bundle="${pensiontotalServDtlsLabel}"   onclick="AddOrUpdateME('btnSave');" readonly="true"/>	
	<hdiits:button name="btnCloseView" id="btnCloseView" type="button" style="display:none" captionid="CNFM.Close" bundle="${pensiontotalServDtlsLabel}" onclick="closeViewData();" readonly="true"/>		
	<hdiits:button type="button" name="btnReset" captionid="CNFM.Reset" bundle="${pensiontotalServDtlsLabel}" onclick="resetData()"></hdiits:button>
</center>
<BR>
<hr>
<script type="text/javascript">
var rowId=${rowId};
</script>

<BR>
<table id="brakInSrvcTable" border=1 borderColor="black" align="center" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}; display:none" width="85%">
	<tr>
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.FromDate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.Todate" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.YEAR" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.MONTH" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.DAY" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.Remark" bundle="${pensiontotalServDtlsLabel}" /></b></td>			
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.Links" bundle="${pensiontotalServDtlsLabel}" /></b></td>			
	</tr>
</table>

<BR>

<table id="otherNonPensionableTable" style="display:none" border=1 borderColor="black" align="center" cellpadding="1" cellspacing="1" style="border-collapse: collapse;background-color: ${tableBGColor}" width="85%">
	<tr>
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.Details" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.YEAR" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.MONTH" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" bgcolor="${tdBGColor}" align="center"><b><hdiits:caption captionid="Pension.DAY" bundle="${pensiontotalServDtlsLabel}" /></b></td>		
	</tr>
	<tr>
		<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="Pension.totalPensionSrvc" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" align="center"><hdiits:text size="4" readonly="true" maxlength="4" name="total_Year" id="total_Year" default="0"/></td>
		<td class="fieldLabel" align="center"><hdiits:text size="2" readonly="true" maxlength="2" name="total_Month" id="total_Month" default="0"/></td>
		<td class="fieldLabel" align="center"><hdiits:text size="2" readonly="true" maxlength="2" name="total_Day" id="total_Day" default="0"/></td>
	</tr>
	<tr>
		<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="Pension.totalNonPensionSrvc" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" align="center"><hdiits:text size="4" maxlength="4" readonly="true" name="min_Year" id="min_Year" /></td>
		<td class="fieldLabel" align="center"><hdiits:text size="2" maxlength="2" readonly="true" name="min_Month" id="min_Month" /></td>
		<td class="fieldLabel" align="center"><hdiits:text size="2" maxlength="2" readonly="true" name="min_Day" id="min_Day" /></td>
	</tr>
	<tr>
		<td class="fieldLabel" bgcolor="lightblue" align="center"><b><hdiits:caption captionid="Pension.PensionableSrvc(1-2)" bundle="${pensiontotalServDtlsLabel}" /></b></td>
		<td class="fieldLabel" align="center"><hdiits:text size="4" maxlength="4" readonly="true" name="final_Year" id="final_Year" default=""/></td>
		<td class="fieldLabel" align="center"><hdiits:text size="2" maxlength="2" readonly="true" name="final_Month" id="final_Month" default=""/></td>
		<td class="fieldLabel" align="center"><hdiits:text size="2" maxlength="2" readonly="true" name="final_Day" id="final_Day" default=""/></td>
	</tr>
</table>

<BR>
<center id="btnCenter">
	<hdiits:button name="btnSubmit" onclick="SaveServiceWindow();" captionid="Pension.btnSubmit" bundle="${pensiontotalServDtlsLabel}" type="button"/>
	<hdiits:button name="btnClose" onclick="closeServiceWindow();" captionid="CNFM.Close" bundle="${pensiontotalServDtlsLabel}" type="button"/>	
</center>
<BR>
<hdiits:hidden name="pensionChildOpenFlag" id="pensionChildOpenFlag" default="false"/>
<hdiits:hidden name="editFlag" id="editFlag" default="false"/>
<hdiits:hidden name="userId" id="userId" default="${userId}" />
<hdiits:hidden name="reqId" id="reqId"/>
<hdiits:hidden name="srNo" id="srNo" default="0"/>
<hdiits:hidden name="Year_No" id="Year_No" default="0"/>
<hdiits:hidden name="Mon_No" id="Mon_No" default="0"/>
<hdiits:hidden name="Day_No" id="Day_No" default="0"/>
</div>
</div>
<script type="text/javascript">	
	initializetabcontent("maintab")
	document.getElementById('fromDate').readOnly=true;
	document.getElementById('toDate').readOnly=true;	
</script>

<c:if test="${not empty  breakServList}">
<c:forEach items="${breakServList}" var="lArrObj" varStatus="x">
	<c:set var="curXMLFileName" value="${xmlBreak[x.index]}"></c:set>	
	<c:set var="status" value="${lArrObj.status}" />
	<c:set var="remarks" value="${lArrObj.remarks}" />	
	<c:forEach items="${lArrObj.hrEssLeaveOtherDtls}" var="OtherDtlsObj"> 
		<c:set var="noofsancCheck" value="${OtherDtlsObj.noofsancdays}"></c:set>	
		<c:set var="leaveTypeCheck" value="${OtherDtlsObj.hrEssLeaveMst.leaveTypeid}"></c:set>
		<c:if test="${noofsancCheck > 0 && leaveTypeCheck==13}">	
			<c:set var="attachmentId" value="${OtherDtlsObj.cmnAttachmentMst.attachmentId}"></c:set>	
			<c:set var="noofsancdays" value="${OtherDtlsObj.noofsancdays}"></c:set>	
			<c:set var="leaveTypeid" value="${OtherDtlsObj.hrEssLeaveMst.leaveTypeid}"></c:set>
			<fmt:formatDate value="${OtherDtlsObj.sanctionFromdate}" pattern="dd/MM/yyyy" var="fromDate"/>
			<fmt:formatDate value="${OtherDtlsObj.sanctionTodate}" pattern="dd/MM/yyyy" var="toDate"/>
		</c:if>
	</c:forEach>
	<script>
		var xmlFileName = '${curXMLFileName}';					
		var remarks= '${remarks}';	
		for(var i=0;i<remarks.length;i++)
		{
			remarks = remarks.replace("&amp;#x0D;"," ");
			remarks = remarks.replace("&#x0D;"," ");
		}
		if(remarks=='')
		{
		remarks='-';
		}
		
		if(("${noofsancdays}" >0)&&("${leaveTypeid}"==13))
		{
			var dateDiff = getDateDiffInString('${fromDate}','${toDate}');
			var minValue = dateDiff.split('~');	
			var year = minValue[0];
			var mon = minValue[1];
			var day = minValue[2];
			
			var displayFieldA  = new Array('${fromDate}','${toDate}',year,mon,day,remarks);		
			if('${status}'==4)	
			{
				addDBDataInTableAttachment('brakInSrvcTable','encXML',displayFieldA,xmlFileName,'${attachmentId}','editRecordForDB', 'DeleteRecordFromLWPTable','');
				mappingArrRowIdAndTableId[mappingArrRowIdAndTableIdCount]="rowencXML"+parseInt(counter-1)+"/brakInSrvcTable/encXML";
				mappingArrRowIdAndTableIdCount=mappingArrRowIdAndTableIdCount+1;			
			}
			else
			{
				addDBDataInTableAttachment('brakInSrvcTable','addedencXML',displayFieldA,xmlFileName,'${attachmentId}','','','viewThisData');
				mappingArrRowIdAndTableId[mappingArrRowIdAndTableIdCount]="rowaddedencXML"+parseInt(counter-1)+"/brakInSrvcTable/addedencXML";
				mappingArrRowIdAndTableIdCount=mappingArrRowIdAndTableIdCount+1;			
			}	
		}	
	</script>
</c:forEach>		
</c:if>

</hdiits:form>
<script>
	var dateDiff = LogicForMinusValue();
	var minValue = dateDiff.split('~');
</script>
<hdiits:validate locale="${locale}" controlNames="selType,remarksData,fromDate,todate" />
</BODY>
</html>
<%
}catch(Exception e)
{
	e.printStackTrace();
}
%>