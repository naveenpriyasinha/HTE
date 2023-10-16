<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="lstInvestTypeMst" value="${resultValue.lstInvestTypeMst}" > </c:set>	
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="investTypeMasterView" bundle="${enLables}"></hdiits:caption></b></a></li>

</ul>
</div>
<div class="tabcontentstyle">	  

 <div id="tcontent1" class="tabcontent">
   
	   <c:out value="${resultValue.msg}" /> <br>
	  <a href= "./hrms.htm?actionFlag=getInvestmentTypeData&edit=N">  Add new Entry </a>
	  <br>
  <display:table name="${lstInvestTypeMst}" requestURI="" pagesize="${pageSize}"  id="row" export="true" style="width:100%">	

	  <display:column class="tablecelltext" title="Investment Name" headerClass="datatableheader" style="text-align:left"> 
			  <a href="./hrms.htm?actionFlag=getInvestmentTypeDataById&investTypeId=${row.investTypeId}&edit=Y">${row.investName} </a>
		 </display:column>
		 <display:column class="tablecelltext" title="Section Code" headerClass="datatableheader" value="${row.hrItSectionMst.sectCode}"> 
	     </display:column>
 


  	  <display:setProperty name="export.pdf" value="true" />
  	  </display:table>
<BR>
  	  
  	<a href= "./hrms.htm?actionFlag=getInvestmentTypeData&edit=N">  Add new Entry </a>

  
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
  		  //System.out.println("There is some error:-");
  		  e.printStackTrace();
  	  }
  	  %>
  	  