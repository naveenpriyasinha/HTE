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
<script type="text/javascript" >
function showApprovedOffices()
{
	var url = "./hrms.htm?actionFlag=viewApprovedOffices";
	///alert(url);
	document.DDOStatusView.action=url;
	document.DDOStatusView.submit();
	showProgressbar();
}

function showRejectedOffices()
{
	var url = "./hrms.htm?actionFlag=viewRejectedOffices";
	//alert(url);
	document.DDOStatusView.action=url;
	document.DDOStatusView.submit();
	showProgressbar();
}
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="zpDDOOfficelst" value="${resultValue.zpDDOOfficelst}" > </c:set>
</head>
<body>
<form method="POST" name="DDOStatusView">
<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>STATUS VIEW</b></a></li>
		</ul>
	</div>

	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	 <div align="center">
	 <input type="button" name="approvedOffices" value="View Approved Offices" onclick="showApprovedOffices()"/>
	 
	 <input type="button" name="rejectedOffices" value="View Rejected Offices" onclick="showRejectedOffices()"/>
	 
	 </div>
	 <script type="text/javascript">
	
		initializetabcontent("maintab")
	</script>
	</div>
</form>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
</body>
</html>