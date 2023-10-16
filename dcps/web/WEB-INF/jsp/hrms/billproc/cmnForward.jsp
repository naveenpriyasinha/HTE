<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<script language = "javascript">
		function submitForm(url)
		{
			alert("URL in submit form L:  " + url);
			document.frmForward.action =url;
			document.frmForward.submit();
			alert("After submitting data to objecitons");
			return true;
		}
		function forwardBill(url)
		{
			alert("Post id selected : " +document.frmForward.cmbName.value);
			alert("Url in submit data : " + url);
			if(submitForm(url))
			{
				alert(' before closing ');
				self.close();
				window.opener.parent.close();
			}
		}
		function singleUser()
		{
			alert("Your Bill is forwarded Successfully");
			self.close();
			window.opener.parent.close();
		}		
		</script>
	</head>
	
	<body>
		<%
			System.out.println(" ---- in sessino ----- " + session.getAttribute("HierarchyUsers"));
		%>
		<hdiits:form validate="true" name="frmForward" method="post">
		<hdiits:table align="center" width="70%">
			<hdiits:tr><hdiits:td colspan="2"><br></hdiits:td></hdiits:tr>
				<c:set var="userMap" value="${sessionScope.HierarchyUsers}" scope="session"></c:set>
				<c:out value="${userMap}"></c:out>
				<c:set var="userList" value="${userMap.Users}"></c:set>
				<c:out value="${userList}"></c:out>
				<c:set var="forwardType" value="${userMap.forwardType}"></c:set>
				<c:out value="-------${forwardType}"></c:out>
				<c:choose>
					<c:when  test="${forwardType==null}">				
						<c:out value="${forwardType}"/>
						<c:out value="Bill is forwarded successfully"/>
					</c:when>

					<c:otherwise>
						<hdiits:tr>
							<hdiits:td colspan="2" align="center">
								Forward to
							</hdiits:td>
						</hdiits:tr>

						<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
	
						<hdiits:tr><hdiits:td colspan="2" align="center">List Of Officers</hdiits:td></hdiits:tr>

						<hdiits:tr>
							<hdiits:td colspan="2" align="center" width="100%">					
								<select name="cmbName" size="5">
									<c:forEach var="user" items="${userList}">
										<option value='<c:out value="${user[1]}" ></c:out>'>
										 	<c:out value="${user[0]}" ></c:out>
										 </option>
									 </c:forEach>
								</select>
							</hdiits:td>
						</hdiits:tr>

						<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
						<hdiits:tr><hdiits:td><br></hdiits:td></hdiits:tr>
			
						<hdiits:tr>
							<hdiits:td>
								<hdiits:button type="button" name="btnForward" value="Forward" onclick="javascript:forwardBill('ifms.htm?actionFlag=multipleForward');"/>
								<hdiits:button type="button" name="btnClose" value="Close" onclick="window.close();" />
							</hdiits:td>
						</hdiits:tr>
					</c:otherwise>
				</c:choose>
			</hdiits:table>
		</hdiits:form>			
	</body>
</html>