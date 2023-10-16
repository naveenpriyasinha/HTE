<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>


<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#FFFFFF"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="fileId" value="${resultValue.fileId}"></c:set>
<c:set var="todaysDate" value="${resultValue.todaysDate}"></c:set>
<c:set var="isSubmitted" value="${resultValue.isSubmitted}"></c:set>
<c:set var="langId" value="${resultValue.langId}"></c:set>
<fmt:formatDate var="formattedDate" pattern="dd/MM/yyyy" value="${todaysDate}" type="date" />

<c:set var="probationEndList" value="${resultValue.probationEndList}"></c:set>


<fmt:setBundle basename="resources.hr.probation.Probation" var="CNFMLables" scope="request" />
<fmt:setBundle basename="resources.hr.probation.AlertMessages" var="CNFMAlerts" scope="request" />


<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/probation/probation.js"/>"></script>

<script>
	var probationAlertArr = new Array();
	probationAlertArr[0]="<fmt:message bundle='${CNFMLables}' key='CNFM.Confirmed'/>";
	probationAlertArr[1]="<fmt:message bundle='${CNFMLables}' key='CNFM.Extended'/>";
	probationAlertArr[2]="<fmt:message bundle='${CNFMLables}' key='CNFM.CnfmDate'/>";
	probationAlertArr[3]="<fmt:message bundle='${CNFMLables}' key='CNFM.ExtndDate'/>";
	probationAlertArr[4]="<fmt:message bundle='${CNFMLables}' key='CNFM.Delete'/>";
	probationAlertArr[5]="<fmt:message bundle='${CNFMAlerts}' key='CNFM.SelAtleastOne'/>";
	probationAlertArr[6]="<fmt:message bundle='${CNFMAlerts}' key='CNFM.DateValuesExtend'/>";
	probationAlertArr[7]="<fmt:message bundle='${CNFMAlerts}' key='CNFM.DateValuesConfirm'/>";
	
	var empDetails;
	var isSubmitted="${isSubmitted}";

	function getProbationRecord(probationRecord, todayDate)
	{
		 for(var iElement=0; iElement<probationRecord.length; iElement++)
		 {
		 	var empRow = probationRecord[iElement] ;
			empDetails = empRow.split("°");
			
			var dispFieldA = new Array(empDetails[0],empDetails[1],empDetails[2],empDetails[3],empDetails[4],empDetails[5],empDetails[6],empDetails[7],empDetails[8],empDetails[9],empDetails[10]);
			addEmplDataInTable("emplProbation",dispFieldA,false,todayDate,"deleteEmpl",'N');		
	 	}
	 	document.getElementById("totalCounter").value = recordCounter;
	}
</script>
</head>
<body>

<hdiits:form name="frmProbation" validate="true" method="POST"
	action="hrms.htm?actionFlag=submitForwardApproveData">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		<fmt:message key="CNFM.Header" bundle="${CNFMLables}" /></a></li>
	</ul>
	</div>

	<div class="tabcontentstyle" style="height: 100%">

	<div id="tcontent1" class="tabcontent" tabno="0">
	<br>
	<table width="100%" id="displaySearch">
		<tr align="center">
			<td width="30%"></td>			
			<td width="20%" align="left"><hdiits:caption captionid="CNFM.Search" bundle="${CNFMLables}" /></td>
			<td align="left"><hdiits:search name="txtEmplName" id="txtEmplName" url="hrms.htm?actionFlag=getProbationEndEmpl" readonly="true" /></td>
			
		</tr>
	</table>
	
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${fileId}" /> 
	<hdiits:hidden name="isSubmitted" id="isSubmitted" default="${isSubmitted}" /> 
	<hdiits:hidden name="totalCounter" id="totalCounter" default="${recordCounter}" /> 
	<hdiits:jsField jsFunction="submit_frmProbation()" name="submit_frmProbation"/>
	
	<table id="emplProbation" border=1 borderColor="black" align="center"
		width="100%" cellpadding="1" cellspacing="1"
		style="border-collapse: collapse;background-color: ${tableBGColor};display:none">
		<tr>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="CNFM.EmpName" bundle="${CNFMLables}" /></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="CNFM.PStartDate" bundle="${CNFMLables}" /></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="CNFM.PEndDate" bundle="${CNFMLables}" /></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="CNFM.Lwp" bundle="${CNFMLables}" /></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="CNFM.Ext" bundle="${CNFMLables}" /></b></td>
			<td align="center" bgcolor="${tdBGColor}"><b><hdiits:caption captionid="CNFM.ExtDate" bundle="${CNFMLables}" /></b></td>
			<td align="center" bgcolor="${tdBGColor}" width="20%"><b><hdiits:caption captionid="CNFM.Remarks" bundle="${CNFMLables}" /></b></td>
			<td align="center" bgcolor="${tdBGColor}" id="action"><b><hdiits:caption captionid="CNFM.Action" bundle="${CNFMLables}" /></b></td>
		</tr>
	</table>
	<table id="tabNavBtns" align="right" style="display:none">
		<tr>
			<td>
				<jsp:include page="../../../core/tabnavigation.jsp" >
					<jsp:param name="disableReset" value="true"/>	
				</jsp:include>
			</td>
		</tr>
	</table>
	<!-- ravi - starts --> 
	<c:if test="${not empty probationEndList}">
		<script>
			document.getElementById("displaySearch").style.display = 'none';
			
		</script>
		<c:forEach var="hrEisProbationDtl" items="${probationEndList}" varStatus="x">

			<c:forEach var="orgEmpMst" items="${hrEisProbationDtl.orgUserMstByUserId.orgEmpMsts}" varStatus="x">
				<c:set var="empLangId" value="${orgEmpMst.cmnLanguageMst.langId}" />
					<c:if test="${langId == empLangId}"> 
							<c:set var="empFname" value="${orgEmpMst.empFname}" />
							<c:set var="empMname" value="${orgEmpMst.empMname}" />
							<c:set var="empLname" value="${orgEmpMst.empLname}" />
					</c:if>	
			</c:forEach>
			<c:set var="hrEmpProbationDtlPk"
				value="${hrEisProbationDtl.hrEmpProbationDtlPk}" />
			<c:set var="fileId" value="${hrEisProbationDtl.fileId}" />
			<c:set var="userId"
				value="${hrEisProbationDtl.orgUserMstByUserId.userId}" />
			<fmt:formatDate var="probationStartDate" pattern="dd/MM/yyyy"
				value="${hrEisProbationDtl.probationStartDate}" type="date" />
			<fmt:formatDate var="probationEndDate" pattern="dd/MM/yyyy"
				value="${hrEisProbationDtl.probationEndDate}" type="date" />
			<c:set var="remarks" value="${hrEisProbationDtl.remarks}" />
			<fmt:formatDate var="confirmationDate" pattern="dd/MM/yyyy"
				value="${hrEisProbationDtl.confirmationDate}" type="date" />

			<script type="text/javascript">					
				var todayDate = "${formattedDate}";
				// PK,file_id,user_id,emp_name,start_date,end_date,lwp,isconfirmed,date,remarks 
				var displayFieldA = new Array('${hrEmpProbationDtlPk}','${fileId}','${userId}','${empFname} ${empMname} ${empLname}','${probationStartDate}','${probationEndDate}','${hrEisProbationDtl.lwp}','${hrEisProbationDtl.isConfirmed}','${confirmationDate}','${remarks}');
				addEmplDataInTable("emplProbation",displayFieldA,true,todayDate,null,"${hrEisProbationDtl.statusFlag}");	
				document.getElementById("tabNavBtns").style.display = 'none';
			</script>

		</c:forEach>
		<script type="text/javascript">			
			document.getElementById("totalCounter").value=recordCounter;			
		</script>
	</c:if> 
	<!-- ravi - ends --> 
	
	</div>
	</div>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	</hdiits:form>

<script type="text/javascript">		
initializetabcontent("maintab");

if("${isSubmitted}" == 1)
{
	document.frmProbation.formSubmitButton.disabled = true;
	
}
</script>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
