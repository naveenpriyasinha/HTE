
<html>
<head>


<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  




<c:set var="msg" value="${resultValue.msg}" > </c:set>
<c:set var="action" value="${resultValue.action}" ></c:set>


</head>
<body>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<div class="halftabcontentstyle">	  
	<div id="tcontent1" class="halftabcontent">
	 <div align="center"> <h1>
	   <c:out value="${resultValue.msg}" />  </h1> 


<br/><br/>


<hdiits:form method="POST" name="Redirect" validate="true" action="hrms.htm?actionFlag=updatePayBillStatus&action=${action}&msg=0">
<hdiits:button name="OK" type="button" caption="OK" onclick="form.submit();"/>
</hdiits:form>
</div>
</div>
</div>

</body>
	</html>   
	  
 
