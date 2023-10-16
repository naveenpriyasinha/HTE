<html>
<head>
<%
try{
%>

<%@ include file="../../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="DistrictOfficeLables" scope="request" />
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="zpdistrictOfficelst" value="${resultValue.zpdistrictOfficelst}" > </c:set>
</head>
<body>
<form method="POST" name="DistrictOfficeView" action="./hrms.htm?actionFlag=loadDistrictScreen">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<%-- %><li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ADMIN_DISTRICT_OFFICE" bundle="${DistrictOfficeLables}"/></b></a></li>--%>
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >	  
	 <div align="center"> <h1>
	   <c:out value="${resultValue.msg}" />  </h1> </div> <br>&nbsp;
		  <a href= "./hrms.htm?actionFlag=loadDistrictScreen">  Add new Entry for District Office </a>  
		  <br>
		  <display:table name="${zpdistrictOfficelst}" requestURI="" pagesize="${pageSize}" sort="list" id="row" export="false" style="width:100%">
			  <display:column property="adminOfficeCode" class="tablecelltext" title="Admin Office Code"  headerClass="datatableheader" style="text-align: center;font-size:12px;" />    
			  <display:column class="tablecelltext" title="District Name" headerClass="datatableheader" style="text-align: center;font-size:12px;">
			  <a href="./hrms.htm?actionFlag=loadDistrictScreen&distId=${row.distId}&edit=Y">${row.distMstOfficeName}</a>
			  </display:column>	
			  
			  
		  	  <display:setProperty name="export.pdf" value="false" />
  		  </display:table>
		 <br>&nbsp;
	  <!-- 	 <a href= "./hrms.htm?actionFlag=loadDistrictScreen">  Add new Entry For District Office </a>  
	  	 <br/><br/> -->
	
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