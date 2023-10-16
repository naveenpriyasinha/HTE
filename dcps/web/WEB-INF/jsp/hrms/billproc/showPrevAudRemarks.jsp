<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>

	<c:set var="resultObj" value="${result}" > </c:set>		
 	<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>			
    <c:set var="PrevRemarksList" value="${resValue.PrevRemarksList}" scope="request"> </c:set>	
    <%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

	<body>
		<table align="center" width="90%" >
			<tr>	<td>&nbsp;</td></tr>
			<tr>	<td>&nbsp;</td></tr>
			
			<tr class="TableHeaderBG"> 
				<td align="center" colspan="3" class="datatableheader">
					<b><fmt:message key="CMN.RemarksHeading" bundle="${billprocLabels}"></fmt:message></b>
				</td>
			</tr>

			<tr><td>&nbsp;</td></tr>
							
			<c:forEach var="prevRemarks" items="${PrevRemarksList}" >
				<tr><td>&nbsp;</td></tr>
					<tr class="TableBorderLTRBN">
					<td align="left" width="15%">
						<b>
							<fmt:message key="CMN.UserName" bundle="${billprocLabels}"></fmt:message>						
						</b>
					</td>
					<td align="left" width="70%">
						&nbsp;:&nbsp;&nbsp;<c:out  value="${prevRemarks.empName}"/>
					</td>
					<td>&nbsp;</td>
					
				</tr>
				<tr>
					<td align="left" width="15%">
						<b>
							<fmt:message key="CMN.REMARKS" bundle="${billprocLabels}"></fmt:message>
						</b>
					</td>
					
					
					<% int i=0; %>
					<c:forEach var="remarks" items="${prevRemarks.remarks}">
						<td align="left" width="50%">
						<% i=1; %>
							&nbsp;:&nbsp;&nbsp;<c:out value="${remarks[0]}"/>  [ <fmt:message key="ADT.AT" bundle="${billprocLabels}"></fmt:message> :
							<c:choose>
	   										<c:when test='${remarks[2] == null}'>
	   										<c:out value="${remarks[1]}"/>
	   										</c:when>
	   										<c:otherwise>
	   										<c:out value="${remarks[2]}"/>
	   										</c:otherwise>
	   						</c:choose>
							
							] 
						</td>
						
						
						
					</c:forEach>
					<% if(i==0) { %>
						<td align="left" width="70%">
							&nbsp;:&nbsp;&nbsp; [ <fmt:message key="ADT.NOREMARK" bundle="${billprocLabels}"></fmt:message> ]
						</td>
					<% } %>
					<td>&nbsp;</td>
					
				</tr>
				<c:if test='${prevRemarks.objections != null}'>
				<tr><td>&nbsp;</td></tr>
			<tr><td colspan="3">
			<table width="90%" align="center" class="TableBorderLTRBN" border="1" cellspacing="0" cellpadding="1">
				<tr class="TableHeaderBG">
					<td align="left" width="20%"  class="datatableheader">
						<b><fmt:message key="ADT.OBJCODE" bundle="${billprocLabels}"></fmt:message></b>
					</td>
					<td align="left" width="50%"  class="datatableheader">
						<b><fmt:message key="ADT.OBJDESC" bundle="${billprocLabels}"></fmt:message></b>
					</td>
					<!--<td align="left" width="30%">
						<b>Objection Date</b>
					</td>
					
				--></tr>
				
				<c:forEach var="obj" items="${prevRemarks.objections}">
					<tr>
						<td align="left" width="20%">
							<c:out value="${obj[0]}"/>
						</td>
						<td align="left" width="50%">
							<c:out value="${obj[1]}"/>
						</td>
						<!--<td align="left" width="30%">
							<c:choose>
	   										<c:when test='${obj[3] == null}'>
	   										<c:out value="${obj[2]}"/>
	   										</c:when>
	   										<c:otherwise>
	   										<c:out value="${obj[3]}"/>
	   										</c:otherwise>
	   						</c:choose>
						</td>
					--></tr>
				</c:forEach>
				</table>
				</td>
			</tr>
			</c:if>
			<tr><td colspan="3"><hr></td></tr>
			 </c:forEach>
 
			 <tr><td>&nbsp;</td></tr>
			 
			 <tr>
			 	<td colspan="3" align="center">
			 		<hdiits:button type="button" name="btnClose" value='<%=buttonBundle.getString("CHQCOMMON.CLOSE")%>' onclick="self.close();"/>
			 	</td>
			 </tr>
		</table>
	</body>
</html>