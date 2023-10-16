<html>
<head>
<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="bankList" value="${resultValue.bankList}" />
<c:set var="listSize" value="${resultValue.listSize}" > </c:set>
<c:set var="bankIDlist" value="${resultValue.bankIDlist}" > </c:set>

</head>
<body>
<form method="POST" name="bankMstView" action="./hrms.htm?viewName=branchMaster">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="BR.BRANCHMASTER" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >	  
	
	<div align="center"> <h1>
	   <c:out value="${resultValue.msg}"/> <br> </h1> 
	</div> 
		<br>&nbsp;
		  <a href= "./hrms.htm?actionFlag=getBranchMaster">  Add new Entry </a>
		  <br> <br>
		  
		<c:if test="${resultValue.listSize ne 0}">
		  <display:table name="${actionList}" requestURI="" pagesize="${pageSize}" id="row" export="false" style="width:100%">
			  
			<display:column class="tablecelltext" title="Branch Name" headerClass="datatableheader" style="text-align: center;font-size:12px;">
				<a href="./hrms.htm?actionFlag=getBranchData&branchId=${row.branchId}&bankCode=${row.bankCode}&edit=Y">${row.branchName}</a>
			</display:column>	
			  
			<display:column class="tablecelltext" title="Bank Name" headerClass="datatableheader" style="text-align: center;font-size:12px;">
				<c:forEach items ="${resultValue.bankList}" var="list">
					<c:if test="${list.bankCode == row.bankCode}">
						${list.bankName}
					</c:if>
				</c:forEach>
			</display:column>

			<display:column class="tablecelltext" title="MICR Code" headerClass="datatableheader"  value="${row.micrCode}" style="text-align: center;font-size:12px;"></display:column>	
				<display:setProperty name="export.pdf" value="false" />
		</display:table>
	   </c:if>

		 <br>&nbsp;
	   	 <!-- <a href= "./hrms.htm?actionFlag=getBranchMaster"> Add new Entry </a> -->
	
	<br/><br/>
	
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
	</script>

</div>
<%
}
  	  catch(Exception e)
  	  {
  		  e.printStackTrace();
  	  }
%>
</form>
</body>
</html>