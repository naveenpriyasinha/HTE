<html>
<head>
<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="commonLables" scope="request"/>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
</head>
<body>
<form method="POST" name="loanMstView" action="./hrms.htm?viewName=loanMaster&elementId=9000190">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="LM.loanMaster" bundle="${commonLables}" style=""/></b></a></li>
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >	  
	 <div align="center"> <h1>
	   <c:out value="${resultValue.msg}" /> <br> </h1> </div>  &nbsp;
		  <a href= "./hrms.htm?viewName=loanMaster&elementId=9000190">  Add new Entry </a>
		  <br> <br>
		  <display:table name="${actionList}" requestURI=""   pagesize="${pageSize}"  id="row" export="false" style="width:100%">
			  <display:column property="loanAdvId" class="tablecelltext" title="Loan Id"  headerClass="datatableheader" style="text-align: center;font-size:12px;"/>    
			  <display:column class="tablecelltext" title="Loan Name" headerClass="datatableheader" style="text-align: center;font-size:12px;">
			  <a href="./hrms.htm?actionFlag=getLoanData&loanid=${row.loanAdvId}&edit=Y&elementId=9000190">${row.loanAdvName}  </a>
			  </display:column>	
			  
			  
		  	  <display:setProperty name="export.pdf" value="false" />
  		  </display:table>
		 <br>&nbsp;
	   	 <!-- <a href= "./hrms.htm?viewName=loanMaster&elementId=9000190">  Add new Entry </a> -->
			<br/>
			<br/>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>
</div>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
</body>
</html>