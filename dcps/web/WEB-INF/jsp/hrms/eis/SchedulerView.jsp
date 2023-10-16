<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="deptList" value="${resValue.deptList}"></c:set>
<c:set var="billList" value="${resValue.billList}"></c:set>
<c:set var="listSize" value="${resValue.listSize}" > </c:set>
<c:set var="schedulerList" value="${resValue.schedulerList}" > </c:set>
<c:set var="duplicateMsg" value="${resValue.duplicateMsg}" > </c:set>


<script>
if('${msg}'!= null && '${msg}'!='')
	//alert('${msg}');
</script>

<%--
<script>alert('${listSize}');</script>  --%>
<hdiits:form name="scheduler" validate="true" method="POST" action="./hrms.htm?actionFlag=ListPageOfSchedulerDetails" encType="text/form-data">


 <c:if test="${duplicateMsg ne null}">
  <script> alert(${duplicateMsg}); </script>
 </c:if>
 
<%-- 
abhilash add for update  
<hdiits:form name="scheduler" validate="true" method="POST" action="./hrms.htm?viewName=SchedulerEdit" encType="text/form-data">

abhilash add for update --%>

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="SR.Scheduler" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	
	
	
	<div class="halftabcontentstyle">	  
<div id="tcontent1" class="halftabcontent">
	  <br><br>
		 <a href= "./hrms.htm?actionFlag=listOfDeptandBill">  Add new Entry </a>
		 <br> <br>  
	
	 
	
	
	<%--<script>alert('Now ur going to scheduler view JSP');</script> --%>

	
	 <hdiits:hidden  default ="${row.schedulerId}" name="txtSchedulerID" caption="Branch ID"  /> 

	<c:if test="${listSize ne 0}">
		  <display:table name="${schedulerList}" requestURI="" pagesize="${pageSize}" id="row" export="true" style="width:100%">
			  
			<display:column class="tablecelltext" title="Bill Number" headerClass="datatableheader" style="text-align:center">
				<a href = "./hrms.htm?actionFlag=updateBillnoFromDept&SchedulerId=${row.schedulerId}&edit=Y" id="otherLink${row.schedulerId}">   ${row.hrpaybillsubheadmpg.billId}  </a>
			</display:column>	
			  
			<display:column class="tablecelltext" title="Department" headerClass="datatableheader" value="${row.cmnLocationMst.locName}"  > </display:column>	
			  <display:column class="tablecelltext" title="Day" headerClass="datatableheader" value="${row.day}"  > </display:column>				  			
		</display:table>
	   </c:if>

	<br>
		 <a href= "./hrms.htm?actionFlag=listOfDeptandBill">  Add new Entry </a>
	
	<script type="text/javascript">
		initializetabcontent("maintab");	 
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