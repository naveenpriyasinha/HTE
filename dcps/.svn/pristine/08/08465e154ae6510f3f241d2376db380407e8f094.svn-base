<%@ include file="../core/include.jsp" %>
  
  <script type="text/javascript" src="script/common/tabcontent.js">
</script>
        <!-- body part of any page start-->
        <br>
        <table align="center" width="10%" cellpadding=5>	    
         <c:set var="resultObj" value="${result}" > </c:set>
         <c:set var="empMap" value="${resultObj.resultValue}" > </c:set>	  
	     <c:set var="empList" value="${empMap.empList}" > </c:set>	 		
				
		 <c:set var="flag" value="true" > </c:set>		
		 <c:forEach var="employee" items="${empList}" >	  	
		 	
			<tr>
			<td align="right" >
		  	<A href="images/EmployeeImage/<c:out value='${employee[0]}'/>.jpg" target="_blank" >
			<img src="images/EmployeeImage/<c:out value='${employee[0]}'/>.jpg" height=100 widht=100 border="0"></A>
			</td>
			<td align="left">
			<table cellpadding=1 cellsapcing=0>
			<tr><td  class="HeaderLabel" align="center"><a title='<fmt:message bundle="${englishLabels}" key="NAME"/>'><fmt:message key="NAME"/></a></td><td align="left"><c:out value='${employee[1]} ${employee[2]} ${employee[3]} ${employee[4]}'/></td></tr>
			<tr><td  class="HeaderLabel" align="center"><a title='<fmt:message bundle="${englishLabels}" key="DATE_OF_JOINING"/>'><fmt:message key="DATE_OF_JOINING"/></a></td><td align="left"><c:out value='${employee[5]}'/></td></tr>					
			<tr><td  class="HeaderLabel" align="center"><a title='<fmt:message bundle="${englishLabels}" key="CONTACT_NO"/>'><fmt:message key="CONTACT_NO"/></a></td><td align="left"><c:out value='${employee[6]}'/></td></tr>								
			<tr><td  class="HeaderLabel" align="center"><a title='<fmt:message bundle="${englishLabels}" key="DESIGNATION_NAME"/>'><fmt:message key="DESIGNATION_NAME"/></a></td><td align="left"><c:out value='${employee[7]}'/></td></tr>								
		<!--<tr><td  class="HeaderLabel" align="center"><a title='<fmt:message bundle="${englishLabels}" key="DESIGNATION_NAME"/>'><fmt:message key="DESIGNATION_NAME"/></a></td><td align="left"><c:out value='${employee[4]}'/></td></tr>  -->															
		
			</table>
			</td>					
			</tr>					
		</c:forEach>
		</table>
        <!-- body part of any page end-->