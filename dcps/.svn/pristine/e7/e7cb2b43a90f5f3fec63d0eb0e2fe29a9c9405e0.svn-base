<%try{ %>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	 <script type="text/javascript" src="script/leave/DateDifference.js"></script> 
	<script type="text/javascript" src="script/leave/DateVal.js"></script>
	<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
	<script type="text/javascript" src="script/common/calendar.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="common/script/tabcontent.js"></script>
	<script type="text/javascript" src="script/leave/leavevalidation.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
   <link rel="stylesheet" href="<c:url value="/common/css/tabcontent.css" />"/>  
	<c:set var="resultObj" value="${result}" > </c:set>	
	<c:set var="resultValue" value="${resultObj.resultValue}"> </c:set>
	<c:set var="empVO" value="${resultValue.empVO}"> </c:set>	
  
 <fmt:setBundle basename="resources.search.search" var="comLable" scope="request"/>	
 <% session.setAttribute("frmname",""+request.getParameter("frmname"));  %>
<script type="text/javascript">
	function openSearchWindow()
	{
		var href='<%=request.getRequestURL() %>'+'?actionFlag=allData&frmname='+'<%=session.getAttribute("frmname")%>&userId=<%=request.getParameter("userId")%>';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=no,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
	}

</script>
 <hdiits:table width="100%" height="100%">
 	<hdiits:tr>
 		<hdiits:td>
 			<br>
 			<br>
 			<br>
 			<br>
		  
		
		<hdiits:hidden name="hempDsgnId"></hdiits:hidden>
		<hdiits:hidden name="hempLocId"></hdiits:hidden>
		<hdiits:hidden name="hempDepId"></hdiits:hidden>
		<hdiits:hidden name="hempPostId"></hdiits:hidden>
		<hdiits:hidden name="hempUserId"></hdiits:hidden>
		<hdiits:hidden name="empUser"  />
			 <hdiits:table width="100%" height="100%">

			 	<hdiits:tr>
 					<hdiits:td>
			 		</hdiits:td>
 					<hdiits:td>
						<hdiits:caption captionid="empname" bundle="${comLable}"/>
			 		</hdiits:td>
 					<hdiits:td colspan="25">
						<hdiits:text name="empName" readonly="true" size="30" tabindex="6"/> 					
			 		</hdiits:td>
			 		<hdiits:td >
						<hdiits:caption captionid="designation" bundle="${comLable}"/>
			 		</hdiits:td>
 					<hdiits:td>
						<hdiits:text name="empDsgn" readonly="true" size="30" tabindex="6"/>
			 		</hdiits:td>			 		

			 		
			 	</hdiits:tr>
			 	<hdiits:tr>
 					<hdiits:td>
			 		</hdiits:td>
					<hdiits:td>
						<hdiits:caption captionid="department" bundle="${comLable}"/>
			 		</hdiits:td>
 					<hdiits:td colspan="25">
						<hdiits:text name="empDep" readonly="true" size="30" tabindex="6"/>
			 		</hdiits:td>			 	
			 		<hdiits:td >
						<hdiits:caption captionid="post" bundle="${comLable}"/>
			 		</hdiits:td>
 					<hdiits:td>
						<hdiits:text name="empPost" readonly="true" size="30" tabindex="6"/>
						<c:out value="${empVO.empId }" ></c:out>
			 		</hdiits:td>
			 	</hdiits:tr>

			 	<hdiits:tr>
					<hdiits:td>
			 		</hdiits:td>
 					<hdiits:td>
						<hdiits:caption captionid="location" bundle="${comLable}"/>
			 		</hdiits:td>
 					<hdiits:td colspan="25">
						<hdiits:text name="empLoc" readonly="true" size="30" tabindex="6"/> 					
			 		</hdiits:td>
 							 		
 					<hdiits:td>

						<c:choose >
 							<c:when test="${param.showEmpId}">
 								<hdiits:caption captionid="empid" bundle="${comLable}"/>
 							</c:when>
 							<c:otherwise >
 							</c:otherwise>
 						</c:choose>						
			 		</hdiits:td>
 					<hdiits:td>
 						<c:choose >
 							<c:when test="${param.showEmpId}">
 								<hdiits:text name="hempId" readonly="true" size="30" tabindex="6"/>
 							</c:when>
 							<c:otherwise >
 								<hdiits:hidden name="hempId" />
 							</c:otherwise>
 						</c:choose>
						<img src="images/payroll/search_icon.gif" onclick="openSearchWindow()">
			 		</hdiits:td>			 		

			 		
			 	</hdiits:tr>			 		 	
			 </hdiits:table>
		  
 			
 		</hdiits:td>
 	</hdiits:tr>
 </hdiits:table>

<%
}catch(Exception e){
	e.printStackTrace();
}

%>