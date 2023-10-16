<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"></fmt:setBundle>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>


<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="lstEmpExemptDtls" value="${resultValue.lstEmpExemptDtls}" > </c:set>

	

   <form method="POST" name="empExemption" action="./hrms.htm?viewName=newScaleMaster">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="empExemptView" bundle="${commonLables}"></hdiits:caption></b></a></li>
		</ul>
	</div>
<div class="tabcontentstyle">	  
	
	<div id="tcontent1" class="tabcontent"> 		
		<br>
		<a href="./hrms.htm?actionFlag=getEmpExemptData&edit=N">  Add new Entry </a>
		<br>	
		 
	  	<display:table name="${lstEmpExemptDtls}" requestURI="" pagesize="${pageSize}" defaultsort="1" defaultorder="descending" id="row" export="true" >
	
		<display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" >
		  	<a href="./hrms.htm?actionFlag=getEmpExemptData&empExemptDtlsId=${row.itexemptDtlsId}&edit=Y">${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname}</a>
		 </display:column>
		 <display:column  class="tablecelltext" title="Exemption Type"  headerClass="datatableheader" >  ${row.hrItExemptTypeMst.itexemptName }  </display:column>
		 
		 <display:column  property="amount" class="tablecelltext" title="Exemption Amount"  headerClass="datatableheader" />
		 
		  <display:setProperty name="export.pdf" value="true" />
	  	 </display:table>
		
		<BR>
	 	  
	  	<a href="./hrms.htm?actionFlag=getEmpExemptData&edit=N">  Add new Entry </a>
 
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
  	  