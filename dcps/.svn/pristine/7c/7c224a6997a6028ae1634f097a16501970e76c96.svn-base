<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="enLables" scope="request"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>

<fmt:setBundle basename="resources.eis.eisLables_en_US" var="Lables" scope="request"/>
	<script>
function onclose()
{
		
		
		document.testing.action="./hrms.htm?actionFlag=getHomePage";
		document.testing.submit();
		
}
</script>	



	<%int i=1;%>
	
<form method="POST" name="testing" action="./hrms.htm?viewName=EmployeeMaster">
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="DM.deductionMaster" bundle="${enLables}"/></b></a></li>
	
</ul>
</div>
	  

 <div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
   
	   <c:out value="${resultValue.msg}" /> <br>

	  <br>
	
	<display:table name="${actionList}" requestURI="" pagesize="${pageSize}"  id="row" export="false" style="width:100%"> 
	  <display:column  class="tablecelltext" title="Short Name" headerClass="datatableheader"  style="text-align: center;font-size:12px;">
	   <!-- <a href="./hrms.htm?actionFlag=getDeducData&deducCode=${row.deducCode}&edit=Y" >  -->  ${row.deducName} <!-- </a>--> 
	  </display:column>
      <display:column property="deducDisplayName" class="tablecelltext" title="Full Name"  headerClass="datatableheader" style="text-align: center;font-size:12px;"/>    
      <display:column property="deducDesc" class="tablecelltext" title="Deduction Description"  headerClass="datatableheader" style="text-align: center;font-size:12px;"/>    
  	  <display:setProperty name="export.pdf" value="false" />
  	</display:table>
  	<br/><br/>
	 <center>   <hdiits:button name="Back" type="button"  value = "Close" captionid="eis.close" bundle="${Lables}" onclick="self.location.href = 'ifms.htm?actionFlag=getHomePage';" /></center>
   <BR><!-- onclick="onclose()" -->
  	  


  
  
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
</div>
  
  	  <%
}
  	  catch(Exception e)
  	  {
  		  //System.out.println("There is some error:-");
  		  e.printStackTrace();
  	  }
  	  %>
  	  