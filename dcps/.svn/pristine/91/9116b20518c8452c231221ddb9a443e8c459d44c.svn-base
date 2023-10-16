<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>




<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="empInvestList" value="${resultValue.empInvestList}" ></c:set>

	

   <form method="POST" name="empInvestment" action="./hrms.htm?viewName=newScaleMaster">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="EMPINVEST.empInvestView" bundle="${commonLables}"></hdiits:caption></b></a></li>
		</ul>
	</div>
<div class="tabcontentstyle">	  
	
	<div id="tcontent1" class="tabcontent">   
		
		<br>
		<a href="./hrms.htm?actionFlag=getEmpInvestmentData&edit=N">  Add new Entry </a>
		<br><br><br>	
		 
	  	<display:table name="${empInvestList}" requestURI="" pagesize="${pageSize}" id="row" export="true" style="width:100%">
			
		  	<display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" >
		  	<a href="./hrms.htm?actionFlag=getEmpInvestmentData&investDtlsId=${row.investDtlsId}&edit=Y" > ${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname}</a>
		  	</display:column>
	   	<display:column class="tablecelltext" title="Investment Type" headerClass="datatableheader"  value="${row.hrInvestTypeMst.investName}"> </display:column>	
			<display:column class="tablecelltext" title="Investment Amount" headerClass="datatableheader"  value="${row.currencyamount}"  > </display:column>	
		<%-- 	<display:column class="tablecelltext" title="Proof Submited" headerClass="datatableheader"  >
			<c:if test="${row.proofSubmitFlag eq '1'}">
				<c:out var="proofSubmittion" value="Yes"></c:out>
			</c:if>
			<c:if test="${row.proofSubmitFlag eq '0'}">
				<c:out var="proofSubmittion" value="No"></c:out>
			</c:if>				
			</display:column>
			<display:column class="tablecelltext" title="Proof Approved" headerClass="datatableheader"  >
			<c:if test="${row.approvalFlag eq '1'}">
				<c:out var="proofApproval" value="Yes"></c:out>
			</c:if>
			<c:if test="${row.approvalFlag eq '0'}">
				<c:out var="proofApproval" value="No"></c:out>
			</c:if>				
			</display:column>
			--%>
	  	    <display:setProperty name="export.pdf" value="true" />
	  	</display:table>
		
		<BR>
	 	  
	  	<a href="./hrms.htm?actionFlag=getEmpInvestmentData&edit=N">  Add new Entry </a>
 
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
  	  