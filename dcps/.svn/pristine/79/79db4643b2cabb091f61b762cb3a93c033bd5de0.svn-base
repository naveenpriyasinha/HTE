
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<link rel="stylesheet"
	href="<c:url value="/themes/hdiits/tabcontent.css"/>" type="text/css" />

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript">
 
	function submitDtls()
	{
		var urlstyle="hdiits.htm?actionFlag=submitSuspensionDtls";
		document.inquiryCaseTracking.action=urlstyle;
		document.inquiryCaseTracking.submit();
	}
	function closewindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getHomePage";
		document.inquiryCaseTracking.action=urlstyle;
		document.inquiryCaseTracking.submit();
	}

</script>

<fmt:setBundle basename="resources.dept.deptLables" var="commonLables"
	scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="branch" value="${resValue.branchList}"></c:set>
<c:set var="department" value="${resValue.deptList}"></c:set>
<c:set var="suspensionCauseList" value="${resValue.suspensionCauseList}"></c:set>

<hdiits:form name="inquiryCaseTracking" validate="true" method="POST"
	encType="multipart/form-data">

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<table width=100% id="SuspensionTab0">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>Suspension Details</u></strong> </font></td>
		</tr>
	</table>
	
	<table width="100%" id="SuspensionTab1">
		<tr>
			<td width=25%><fmt:message key="dept.inqCaseNo"/></td>
			<td width=25%><hdiits:number name="inq_text" id="inq_text"></hdiits:number></td>
			<td width=25%><fmt:message key="dept.appDt"  /></td>
			<td width=25%><hdiits:dateTime captionid="dept.suspOrderDt"  bundle="${commonLables}" name="appdate"  /></td>
		</tr>

		<tr>
			<td width=25%><fmt:message key="dept.causeSusp" /></td>
			<td width=25%><hdiits:select name="causeofsuspension"
				id="causeofsuspension">
				<hdiits:option value="-1">----Select----</hdiits:option>
				<c:forEach var="suspensionCauseList" items="${suspensionCauseList}">
					<hdiits:option value="${suspensionCauseList.lookupId}">${suspensionCauseList.lookupName}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
			<td width=25%>Departmental Inquiry File No</td>
			<td width=25%><hdiits:text name="department_text"></hdiits:text></td>
		</tr>


		<tr>
			<td width=25%>Whether Preliminary Inquiry Required?</td>
			<td width=25%><hdiits:radio name="isPrelimReq" value="Y"
				mandatory="false" />Yes <hdiits:radio name="isPrelimReq" value="N"
				mandatory="false" /> No</td>

		</tr>

		<tr>
			<td width=25%>Departmental Name</td>
			<td width=25%><hdiits:text name="departmentalname"
				id="departmentalname"></hdiits:text></td>
			<td width=25%>Remarks</td>
			<td width=25%><hdiits:textarea name="remarks" id="remarks"></hdiits:textarea></td>
		</tr>

		<tr>
			<td width=25%>Suspension From Date</td>
			<td width=25%><hdiits:dateTime captionid="dept.suspOrderDt"  bundle="${commonLables}" 
				name="suspensFromdate" mandatory="true" /></td>

			<td width=25%>Suspension To Date</td>
			<td width=25%><hdiits:dateTime captionid="dept.suspOrderDt"  bundle="${commonLables}" 
				name="suspensTodate" mandatory="true" /></td>
		</tr>				

	</table>

	<table width=100% id="SuspensionTab2">
		<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="4" align="center"><font
				color="#ffffff"> <strong><u>Suspension/Prosection
			Order Details</u></strong> </font></td>
		</tr>
	</table>

	<table width="100%" id="SuspensionTab3">
		<tr>
			<td width=25%>Date Of Next Increment</td>
			<td width=25%><hdiits:dateTime captionid="dept.suspOrderDt"  bundle="${commonLables}"  name="incdate" />
			</td>
			<td width=25%>Department</td>
			<td width=25%><hdiits:select name="depName" id="depName"
				sort="false" mandatory="true">
				<option value="Select"><fmt:message key="dept.select" /></option>
				<c:forEach var="department" items="${department}">
					<hdiits:option value="${department.departmentId}">${department.depName}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>

		<tr>
			<td width=25%>Division</td>
			<td width=25%><hdiits:text name="division" mandatory="true"
				id="division"></hdiits:text></td>
			<td width=25%>Branch</td>
			<td width=25%><hdiits:select name="branchSusp" sort="false"
				mandatory="true">
				<option value="Select"><fmt:message key="dept.select" /></option>
				<c:forEach var="branch" items="${branch}">
					<hdiits:option value="${branch.branchId}">${branch.branchName}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>

		<tr>
			<td width=25%>Suspension Order No.</td>
			<td width=25%><hdiits:number name="suspOrderNo"></hdiits:number></td>
			<td width=25%>Suspension Order Date</td>
			<td width=25%><hdiits:dateTime captionid="dept.suspOrderDt"  bundle="${commonLables}" 
				name="orderdate" /></td>
		</tr>

		<tr>
			<td width=25%>Suspension Order Details</td>
			<td width=25%><hdiits:textarea name="suspdetails"></hdiits:textarea></td>
			<td width=25%>Suspension Allowance</td>
			<td width=25%><hdiits:number name="suspallAllow" maxlength="8"
				size="20"></hdiits:number></td>
		</tr>
	</table>

	<%@ include file="../suspension/SuspensionReview.jsp"%>
	
	<br>
	<table width="100%" align="center">
		<tr>
			<td align="center">
			<center><hdiits:button name="saveSuspensionDtl"
				type="button" id="saveSuspensionDtl" value="Save" onclick="submitDtls()" /> <hdiits:button
				name="editSuspensionDtl" type="button" id="editSuspensionDtl"
				value="Edit" /> <hdiits:button name="closeForm" type="button"
				id="closeForm" value="Close" onclick="closewindow()" /> <hdiits:button name="closeEnquiry"
				type="button" id="closeEnquiry" value="Close Enquiry" /></center>
			</td>
		</tr>
	</table>
	
<hdiits:hidden name="datDiff" id="datDiff"/>

</hdiits:form>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

