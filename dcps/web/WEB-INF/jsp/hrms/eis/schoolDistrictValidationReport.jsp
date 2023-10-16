<html>
<head>
<%
try{
//added by vaibhav tyagi
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />
<fmt:setBundle basename="resources.Payroll" var="constantVariables"
	scope="request" />
<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="DistrictOfficeLables" scope="request" />
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript">
	
function generateReports(flag)
{
	var flags = flag.toString();
	var url = "./hrms.htm?actionFlag=generateDDOExcelforSchoolValidation&flag="+flags;
	document.schoolDistrictValidationView.action=url;
	document.schoolDistrictValidationView.submit();
	
	
}


</script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="totalSchoolsConfig" value="${resultValue.totalSchoolsConfig}">
</c:set>
<c:set var="approvedSchools" value="${resultValue.approvedSchools}">
</c:set>
<c:set var="pendingSchools" value="${resultValue.pendingSchools}">
</c:set>
<c:set var="rejectedSchools" value="${resultValue.rejectedSchools}">
</c:set>
<c:set var="dataEntryInitiated" value="${resultValue.dataEntryInitiated}">
</c:set>
<c:set var="flagApproved" value="0"></c:set>
<c:set var="flagPending" value="0"></c:set>
<c:set var="flagRejected" value="0"></c:set>
<c:set var="flagDataEntryInitiated" value="0"></c:set>

</head>
<body>
<form method="POST" name="schoolDistrictValidationView">
<div id="tcontent1"
	style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">

<br />
<br />
<fieldset class="tabstyle"><legend>School
Configuration Report </legend>
<table id="vo" style="align: center; Border: 1px" cellpadding="4"
	class="datatable">
	<thead>
		<tr>
			<th class="datatableheader">District Name</th>
			<th class="datatableheader">Type of School</th>
			<th class="datatableheader">Total School Configured</th>
			<th class="datatableheader">No. of Schools Approved</th>
			<th class="datatableheader">No. of Schools Pending</th>
			<th class="datatableheader">No. of Schools Rejected</th>
			<th class="datatableheader">Schools with Data Entry Initiated</th>

		</tr>
	</thead>

	<tbody>

		<c:forEach items="${resultValue.totalSchoolsConfig}" var="totalSchoolsConf">
			<tr style="border: 1px">
				<td align="center" style="border: 1px solid rgb(255, 218, 178);">
				<c:if test="${totalSchoolsConf[0] ne '-1'}">
				<label><c:out value="${totalSchoolsConf[1]}"></c:out></label>
				</c:if>
				<c:if test="${totalSchoolsConf[0] eq '-1'}">
				<label><c:out value="No District Available"></c:out></label>
				</c:if>
				</td>

				<td align="center" style="border: 1px solid rgb(255, 218, 178);"><label><c:out
					value="${totalSchoolsConf[3]}"></c:out></label></td>

				<td align="center" style="border: 1px solid rgb(255, 218, 178);"><label><c:out
					value="${totalSchoolsConf[4]}"></c:out></label></td>

				<td align="center" style="border: 1px solid rgb(255, 218, 178);"><c:set
					var="flagApproved" value="0"></c:set> <c:forEach
					items="${resultValue.approvedSchools}" var="appvedSch">
					<c:if
						test="${(appvedSch[0]==totalSchoolsConf[0])&&(appvedSch[1]==totalSchoolsConf[2])}">
						<c:set var="flagApproved" value="${appvedSch[2]}"></c:set>

					</c:if>
				</c:forEach> <label><c:out value="${flagApproved}"></c:out></label></td>

				<td align="center" style="border: 1px solid rgb(255, 218, 178);"><c:set
					var="flagPending" value="0"></c:set> <c:forEach
					items="${resultValue.pendingSchools}" var="pendingSch">
					<c:if
						test="${(pendingSch[0]==totalSchoolsConf[0])&&(pendingSch[1]==totalSchoolsConf[2])}">
						<c:set var="flagPending" value="${pendingSch[2]}"></c:set>

					</c:if>
				</c:forEach> <label><c:out value="${flagPending}"></c:out></label></td>

				<td align="center" style="border: 1px solid rgb(255, 218, 178);"><c:set
					var="flagRejected" value="0"></c:set> <c:forEach
					items="${resultValue.rejectedSchools}" var="rejectSch">
					<c:if
						test="${(rejectSch[0]==totalSchoolsConf[0])&&(rejectSch[1]==totalSchoolsConf[2])}">
						<c:set var="flagRejected" value="${rejectSch[2]}"></c:set>

					</c:if>
				</c:forEach> <label><c:out value="${flagRejected}"></c:out></label></td>

				<td align="center" style="border: 1px solid rgb(255, 218, 178);"><c:set
					var="flagDataEntryInitiated" value="0"></c:set> <c:forEach
					items="${resultValue.dataEntryInitiated}" var="dataEntryInit">
					<c:if
						test="${(dataEntryInit[0]==totalSchoolsConf[0])&&(dataEntryInit[1]==totalSchoolsConf[2])}">
						<c:set var="flagDataEntryInitiated" value="${dataEntryInit[2]}"></c:set>

					</c:if>
				</c:forEach> <label><c:out value="${flagDataEntryInitiated}"></c:out></label></td>

			</tr>
		</c:forEach>

	</tbody>
</table>

<br>
&nbsp;
<div align="center"><hdiits:button style="width:120px"
	name="ExcelReports" type="button" captionid="EIS.EXPORTOEXCEL"
	bundle="${commonLables}" onclick="generateReports('${flag}')"></hdiits:button></div>
<br />
<br />

<script type="text/javascript">
	
		initializetabcontent("maintab")
	</script>
</div>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>

</body>
</html>