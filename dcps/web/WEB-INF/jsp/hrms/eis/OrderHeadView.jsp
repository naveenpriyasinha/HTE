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
<c:set var="ohMpgList" value="${resultValue.resultSet}" > </c:set>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

</head>
<body>
<hdiits:form method="POST" name="orderheadMstView" validate="true" action="./hrms.htm?viewName=orderheadMaster">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="OM.orderheadView" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div class="halftabcontentstyle">	  
	<div id="tcontent1" class="halftabcontent">
	 <div align="center"> <h1>
 </h1> </div> <br>
 <table  width="85%" align="center" name="searchTablePost" id="searchTablePost">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/hrms/eis/SearchOrder.jsp">
				<jsp:param name="formName" value="orderheadMstView"/>
				<jsp:param name="searchAction" value="actionFlag=getOrderHeadData"/>
				<jsp:param name="backAction" value="actionFlag=getOrderHeadPostData&postSearchFlag=n"/>
				</jsp:include>

			</td>
		</tr>
		</table>
 		
 
  <br>
		  <a href= "./hrms.htm?actionFlag=orderheadMaster">Add new Entry </a>  
		 
		  <display:table name="${ohMpgList}" requestURI=""  defaultsort="2" pagesize="${pageSize}" sort="list"  id="row" defaultorder="ascending" export="true" style="width:100%">
		  <display:column class="tablecelltext" title="Sanction Order No " headerClass="datatableheader" style="text-align:left"> 
	          <a href="./hrms.htm?actionFlag=orderheadMaster&ohMapId=${row.orderHeadId}&updateflag=Y">${row.orderName}</a> 
	          </display:column>				  
			 <display:column property="subHeadName" class="tablecelltext" title="Head Name" headerClass="datatableheader" /> 
		  	  <display:setProperty name="export.pdf" value="true"/>
  		  </display:table>
		 <br>
	  	 <a href= "./hrms.htm?viewName=orderheadMaster">  Add new Entry </a>  
	</div>
	<script type="text/javascript">
		
		initializetabcontent("maintab")
	</script>
</div>
</hdiits:form>
<%
}

  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
</body>
</html>