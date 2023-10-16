
<%
try {
%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ include file="../../core/include.jsp"%>
<%@page
	import="java.util.List,com.tcs.sgv.ess.valueobject.EmployeeSearchVO;"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<fmt:setBundle basename="resources.EssCommonLables" var="lables"
	scope="request" />
<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />
<c:set var="EmpDetVO" value="${resultValue.EmpDet}" />




<table bgcolor="#386CB7" align="center" width="100%">

	<tr align="center">
		<td><font class="Label3" align="center"><u><font
			class="Label3" align="center" color="white"><b><hdiits:caption
			captionid="HRMS.EMP" bundle="${lables}" /></b></u><span class="UserText"
			lang="en-us"></span></font></td>


	</tr>
</table>
<table width="100%">
	<tr>
		<td><b> <hdiits:caption captionid="HRMS.Name"
			bundle="${lables}" /></b></td>
		<td><c:out value="${EmpDetVO.empName}"></c:out></td>
	<!-- <td><b> <hdiits:caption captionid="HRMS.Address" bundle="${lables}" /></b></td> <td> //<c:out value="${EmpDetVO.empAdd}"></c:out></td> -->
	 <td>
	 </td>
	 <td>
	 </td>
	</tr>
	<tr>
		<td><b><hdiits:caption captionid="HRMS.Designation"
			bundle="${lables}" /></b></td>
		<td><c:out value="${EmpDetVO.designation}"></c:out></td>
		<td><b> Location </b></td>
		<td><c:out value="${EmpDetVO.cityName}"></c:out></td>
	</tr>
	<tr>
		<td><b> <hdiits:caption captionid="HRMS.BasicSalary"
			bundle="${lables}" /></b></td>
		<td>
		
	   	<c:if test="${EmpDetVO.salary!=-1}"> 
		
		<c:out value="${EmpDetVO.salary}"></c:out>
		</c:if>
		<c:if test="${EmpDetVO.salary==-1}">  
		<c:out  value="No record Found"></c:out>
		</c:if>
		</td>
		<td><b><hdiits:caption captionid="HRMS.PayScale"
			bundle="${lables}" /> </b></td>
		<td>
			<c:if test="${EmpDetVO.scaleStart!=-1}"> 
		<c:out value="${EmpDetVO.scaleStart}"></c:out>--<c:out
			value="${EmpDetVO.scaleInc}"></c:out>--<c:out
			value="${EmpDetVO.scaleEnd}"></c:out>
			</c:if>
			<c:if test="${EmpDetVO.salary==-1}">  
		    </c:out>
		    </c:if>
			
			</td>
	</tr>
	<tr>
		<td><b> <hdiits:caption captionid="HRMS.DateofJoining"
			bundle="${lables}" /></b></td>
		<td><fmt:formatDate value="${EmpDetVO.doj}" pattern="dd/MM/yyyy" />
		</td>
		<td><b> <hdiits:caption captionid="HRMS.DateofRetirement"
			bundle="${lables}" /></b></td>
		<td><fmt:formatDate value="${EmpDetVO.dor}" pattern="dd/MM/yyyy" />
		</td>
	</tr>


</table>


<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>

