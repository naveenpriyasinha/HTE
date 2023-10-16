
<%
	try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />
<fmt:setBundle basename="resources.Payroll" var="constantVariables"
	scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="actionList" value="${resultValue.actionList}">
</c:set>
<c:set var="empAllRec" value="${resultValue.empAllRec}"></c:set>
<c:set var="empId" value="${resultValue.empId}"></c:set>
<c:set var="ViewFlag" value="${resultValue.ViewFlag}"></c:set>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="contract" key="contract" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="fixed" key="fixed" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="permanent" key="permanent"
	bundle="${constantVariables}" scope="request">
</fmt:message>
<fmt:message var="probation" key="probation"
	bundle="${constantVariables}" scope="request">
</fmt:message>
<c:set var="empName" value="${resultValue.empName}">
</c:set>
<c:set var="FromBasicDtlsNew" value="${resultValue.FromBasicDtlsNew}"></c:set>
<c:set var="otherId" value="${resultValue.otherId}"></c:set>
<style type="text/css">
.bluecolor {
	color: blue;
}
</style>

<%
	List actionList = (List) pageContext.getAttribute("actionList");

		int size = actionList.size();

		pageContext.setAttribute("listSize", size);
%>
<script type="text/javascript">
	//alert("in empViewListEmpCompMpg..........");

	function submitForm() {
		var empId = document.getElementById("Employee_ID_EmpInfoSearch").value;

		var url = "./hrms.htm?actionFlag=getEmpDataForMapping";

		document.EmpInfo.action = url;
		document.EmpInfo.submit();
	}
	function chkValue() {

		if (document.getElementById("Employee_ID_EmpInfoSearch").value == document
				.getElementById("Employee_Name_EmpInfoSearch").value) {

			empId = document.getElementById("Employee_ID_EmpInfoSearch").value;
		} else {

			empId = document.getElementById("Employee_Name_EmpInfoSearch").value;
		}

		document.getElementById("Employee_ID_EmpInfoSearch").value = empId;

		var empId = document.getElementById("Employee_ID_EmpInfoSearch").value;
		if (empId == "") {
			alert("Please search the employee first");
			return false;
		} else {
			return true;
		}

	}
	function submitFormAuto() {
		if (chkValue()) {
			document.EmpInfo.submit();
		}
		return true;
	}
	function opennewwindow(valueempid) {
		alert("in getEmpDataForMapping opennewwindow :: " + valueempid);
		//window.parent.opener.location="./hrms.htm?actionFlag=getEmpDataForMapping&empId="+valueempid+"&edit=Y";
		//	window.close();	
		var href = "./hrms.htm?actionFlag=getEmpDataForMapping&empId="
				+ valueempid + "&edit=Y";
		objChildWindow = window
				.open(
						href,
						"Show",
						"toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");
	}
</script>
<%
	int i = 1;
%>
<body>
	<div id="childLock">
		<div id="tabmenu">
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
								captionid="EmpEndDate.WorkList" bundle="${commonLables}"
								style="color:black" /></b></a></li>

			</ul>
		</div>
		<div id="tcontent1"
			style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">

			<br>
			<hdiits:form name="EmpInfo" validate="true" method="POST"
				action="./hrms.htm?actionFlag=getEmpDataForEndDate"
				encType="text/form-data">
				<c:set value="display:none" var="displayStyle" />
				<c:if test="${empAllRec!='true'}">
					<c:set value="display:show" var="displayStyle" />
				</c:if>

				<table width="85%" align="center" style="${displayStyle};" border=1>
					<tr>
						<td><jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
								<jsp:param name="searchEmployeeTitle" value="Search Employee" />
								<jsp:param name="SearchEmployee" value="EmpInfoSearch" />
								<jsp:param name="formName" value="EmpInfo" />
								<jsp:param name="functionName" value="submitFormAuto" />
							</jsp:include></td>

					</tr>

					<c:if test="${listSize ge 2}">
						<c:set value="display:none" var="displayStyle" />
					</c:if>
					<tr style="${displayStyle}">
						<td align="center">
							<fieldset style="background: #eeeeee;">
								<!--<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${commonLables}"  name="details" onclick="return chkValue()"/>	
				-->
								<hdiits:button type="button" captionid="viewAll"
									bundle="${commonLables}" name="viewAll" onclick="submitForm()" />
							</fieldset>
						</td>
					</tr>
				</table>
			</hdiits:form>



			<display:table name="${actionList}" requestURI=""
				pagesize="${pageSize}" id="row" export="false" style="width:100%">

				<c:set value="${row.empId}" var="otherIdForLink" />
				<%--<display:column property="empId" class="tablecelltext" title="Employee Id"  headerClass="datatableheader" style="text-align:center"/>--%>
				<display:column class="tablecelltext" title="Employee Name"
					headerClass="datatableheader"
					style="text-align: center;font-size:12px;">
					<a
						href="./hrms.htm?actionFlag=getEmpDataForEndDate&empId=${row.empId}&edit=Y&elementId=9000224"
						id="otherLink${row.empId}">${row.empName}</a>

				</display:column>

				<display:column class="tablecelltext" title="DCPS/GPF"
					headerClass="datatableheader"
					style="text-align: center;font-size:12px;">
					<c:out value="${row.dcpsorgpf}" />
				</display:column>

				<c:choose>
					<c:when test="${row.empType eq contract }">
						<c:set var="empType" value="Contract" />
					</c:when>
					<c:when test="${row.empType eq fixed }">
						<c:set var="empType" value=" Fixed Pay " />
					</c:when>
					<c:when test="${row.empType eq permanent }">
						<c:set var="empType" value=" Permanent " />
					</c:when>
					<c:when test="${row.empType eq probation }">
						<c:set var="empType" value=" Probation " />
					</c:when>
				</c:choose>
				<display:column class="tablecelltext" title="Employee Type"
					headerClass="datatableheader"
					style="text-align: center;font-size:12px;">
					<c:out value="${empType}" />
				</display:column>


				<display:setProperty name="export.pdf" value="false" />
			</display:table>

			<BR>







			<script type="text/javascript">
				//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
				initializetabcontent("maintab")
			</script>
		</div>
	</div>
</body>
<%
	} catch (Exception e) {

		e.printStackTrace();
	}
%>
