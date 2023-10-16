<html>
<head>
<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="commonLables" scope="request"/>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
</head>
<body>
<form method="POST" name="exprMstView" action="./hrms.htm?viewName=exprMaster">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="EX.ruleMaster" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">	  
	<div id="tcontent1" class="tabcontent">
	 <div align="center"> <h1>
	   <c:out value="${resultValue.msg}" /> <br> </h1> </div> <br>
		  <a href= "./hrms.htm?actionFlag=getExprMaster">  Add new Entry </a>
		  <br> <br>
		  <display:table name="${actionList}" requestURI="" pagesize="${pageSize}"  id="row" export="true" style="width:100%">
			  <display:column property="ruleId" class="tablecelltext" title="Rule ID"  headerClass="datatableheader" />    
			  <display:column class="tablecelltext" title="Rule Description" headerClass="datatableheader">
			  <a href="./hrms.htm?actionFlag=getRuleData&ruleid=${row.ruleId}&edit=Y">${row.ruleDescription}  </a>
			  </display:column>	
			  <display:column class="tablecelltext" title="Rule Expression" headerClass="datatableheader">
			  ${row.ruleExpression}
			  </display:column>
			  <display:column class="tablecelltext" title="Rule Value" headerClass="datatableheader">
			  ${row.ruleValue }
			  </display:column>
			  
		  	  <display:setProperty name="export.pdf" value="true" />
  		  </display:table>
		 <br>
	   	 <a href= "./hrms.htm?actionFlag=getExprMaster">  Add new Entry </a>
	</div>
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