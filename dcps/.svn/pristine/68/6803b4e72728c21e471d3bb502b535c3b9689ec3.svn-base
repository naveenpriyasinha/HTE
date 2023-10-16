<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Employee</title>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>



</head>
<body>

<hdiits:form name="SearchEmployeeSub" validate="true" onsubmit="validateSearchEmployee()">	   
<table  width="85%" align="center" bgcolor="#D0D0D0" name="searchTable" id="searchTable">
<tr><td><h2><b>Search Employee</b></h2></td></tr>
		<tr>
			<td >				
				Employee Name: 
			</td>
			<td>
			<hdiits:text name="empName" />
			</td>
			<td>
				Bill Nunber:
			</td>
			<TD	width ="25%"><hdiits:select style="width:30%"  name="bill" size="1" sort="false" caption="Bill No." captionid="bill"    >
	    <hdiits:option value="">----Select----</hdiits:option>
        
		 <c:forEach var="billHeadMpg" items="${billNoList}">
         <hdiits:option value="${billHeadMpg.billHeadId}"><c:out value="${billHeadMpg.billId}"> </c:out></hdiits:option>
         </c:forEach>
		 </hdiits:select > 
	   </TD>
		</tr>
		
		
	</table>
</hdiits:form>	
</body>
</html>