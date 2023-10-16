<html>
<head>
<%
try
{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="resultSet" value="${resultValue.resultSet}" > </c:set>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
  
</head>
<body>
<form method="POST" name="billheadMstView" action="./hrms.htm?viewName=orderheadMaster">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="BHM.billHeadView" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div class="halftabcontentstyle">	  
	<div id="tcontent1" class="halftabcontent">
	 <div align="center"> <h1>
 </h1> </div> <br>
		  <a href= "./hrms.htm?actionFlag=billheadMaster" >Add new Entry </a>  
		 
		  <display:table name="${resultSet}" requestURI=""    pagesize="${pageSize}"   id="row" defaultorder="ascending" export="true" style="width:100%">
		  <display:column class="tablecelltext" title="Bill No."  headerClass="datatableheader" style="text-align:center"> 
	          <a href="./hrms.htm?actionFlag=billheadMaster&billheadID=${row.billHeadId}&updateflag=Y">${row.billId}</a> 
	          </display:column>				  
			 <display:column  class="tablecelltext"  title="Head Structure" headerClass="datatableheader" style="text-align:left" >
			 
			<b> ${row.demandNo} - ${row.mjrHead} - ${row.subMjrHead} - ${row.minorHead} - ${row.subHeadName} </b>
			 
			 </display:column>
		  	  <display:setProperty name="export.pdf" value="true"/>
  		  </display:table>
		 <br>
	  	 <a href= "./hrms.htm?actionFlag=billheadMaster">  Add new Entry </a>  
	</div>
	<script type="text/javascript">
		
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