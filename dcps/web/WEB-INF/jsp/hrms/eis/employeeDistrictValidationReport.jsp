<html>
<head>
<%
	try {
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
	function generateReports(flag) {
		var flags = flag.toString();
		var url = "./hrms.htm?actionFlag=generateDDOExcelforEmployeeValidation&flag="
				+ flags;
		document.EmployeeDistrictValidationView.action = url;
		document.EmployeeDistrictValidationView.submit();

	}
</script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="noOfEmpConfig" value="${resultValue.noOfEmpConfig}">
</c:set>
<c:set var="noOfDraftForms" value="${resultValue.noOfDraftForms}">
</c:set>
<c:set var="noOfFwdForms" value="${resultValue.noOfFwdForms}">
</c:set>
<c:set var="noOfAppvdForms" value="${resultValue.noOfAppvdForms}">
</c:set>
<c:set var="noOfRejectForms" value="${resultValue.noOfRejectForms}">
</c:set>
<c:set var="flagDraft" value="0"></c:set>
<c:set var="flagFwd" value="0"></c:set>
<c:set var="flagApp" value="0"></c:set>
<c:set var="flagRej" value="0"></c:set>

</head>
<body>
	<form method="POST" name="EmployeeDistrictValidationView">
		<div id="tcontent1"
			style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">

			<br /> <br />
			<fieldset class="tabstyle">
				<legend>Employee Configuration Report </legend>
				<table id="vo" style="align: center; Border: 1px" cellpadding="4"
					class="datatable">
					<thead>
						<tr>
							<th class="datatableheader">District Name</th>
							<th class="datatableheader">Type of School</th>
							<th class="datatableheader">Institution Name</th>
							<th class="datatableheader">Total No. Of Employees
								Configured</th>
							<th class="datatableheader">No. of Employees whose data is
								Saved as Draft</th>
							<th class="datatableheader">No. of Forms Forwarded To Level
								2/Level 3</th>
							<th class="datatableheader">No. of Forms Approved By Level
								2/ Level 3</th>
							<th class="datatableheader">No. of Forms Rejected By Level
								2/Level 3</th>

						</tr>
					</thead>

					<tbody>

						<c:forEach items="${resultValue.noOfEmpConfig}" var="totalNoOfEmp">
							<tr style="border: 1px">
								<td style="border: 1px solid rgb(255, 218, 178);"><label><c:out
											value="${totalNoOfEmp[1]}"></c:out></label></td>

								<td style="border: 1px solid rgb(255, 218, 178);"><label><c:out
											value="${totalNoOfEmp[3]}"></c:out></label></td>

								<td style="border: 1px solid rgb(255, 218, 178);"><label><c:out
											value="${totalNoOfEmp[4]}"></c:out></label></td>

								<td style="border: 1px solid rgb(255, 218, 178);"><label><c:out
											value="${totalNoOfEmp[5]}"></c:out></label></td>

								<td style="border: 1px solid rgb(255, 218, 178);"><c:set
										var="flagDraft" value="0"></c:set> <c:forEach
										items="${resultValue.noOfDraftForms}" var="noOfDrafts">
										<c:if
											test="${(noOfDrafts[3]==totalNoOfEmp[6])&&(noOfDrafts[0]==totalNoOfEmp[0])&&(noOfDrafts[1]==totalNoOfEmp[2])}">
											<c:set var="flagDraft" value="${noOfDrafts[2]}"></c:set>

										</c:if>
									</c:forEach> <label><c:out value="${flagDraft}"></c:out></label></td>

								<td style="border: 1px solid rgb(255, 218, 178);"><c:set
										var="flagFwd" value="0"></c:set> <c:forEach
										items="${resultValue.noOfFwdForms}" var="noOfFwrdForms">
										<c:if
											test="${(noOfFwrdForms[3]==totalNoOfEmp[6])&&(noOfFwrdForms[0]==totalNoOfEmp[0])&&(noOfFwrdForms[1]==totalNoOfEmp[2])}">
											<c:set var="flagFwd" value="${noOfFwrdForms[2]}"></c:set>
										</c:if>

									</c:forEach> <label><c:out value="${flagFwd}"></c:out></label></td>

								<td style="border: 1px solid rgb(255, 218, 178);"><c:set
										var="flagApp" value="0"></c:set> <c:forEach
										items="${resultValue.noOfAppvdForms}" var="noOfApprovedForms">
										<c:if
											test="${(noOfApprovedForms[3]==totalNoOfEmp[6])&&(noOfApprovedForms[0]==totalNoOfEmp[0])&&(noOfApprovedForms[1]==totalNoOfEmp[2])}">
											<c:set var="flagApp" value="${noOfApprovedForms[2]}"></c:set>
										</c:if>

									</c:forEach> <label><c:out value="${flagApp}"></c:out></label></td>

								<td style="border: 1px solid rgb(255, 218, 178);"><c:set
										var="flagRej" value="0"></c:set> <c:forEach
										items="${resultValue.noOfRejectForms}" var="noOfRejectedForms">
										<c:if
											test="${(noOfRejectedForms[3]==totalNoOfEmp[6])&&(noOfRejectedForms[0]==totalNoOfEmp[0])&&(noOfRejectedForms[1]==totalNoOfEmp[2])}">

											<c:set var="flagRej" value="${noOfRejectedForms[2]}"></c:set>

										</c:if>

									</c:forEach> <label><c:out value="${flagRej}"></c:out></label></td>

							</tr>
						</c:forEach>

					</tbody>
				</table>

				<br> &nbsp;
				<div align="center">
					<hdiits:button style="width:120px" name="ExcelReports"
						type="button" captionid="EIS.EXPORTOEXCEL"
						bundle="${commonLables}" onclick="generateReports('${flag}')"></hdiits:button>
				</div>
				<br /> <br />

				<script type="text/javascript">
					initializetabcontent("maintab")
				</script>
		</div>
		<%
			} catch (Exception e) {

				e.printStackTrace();
			}
		%>
	
</body>
</html>