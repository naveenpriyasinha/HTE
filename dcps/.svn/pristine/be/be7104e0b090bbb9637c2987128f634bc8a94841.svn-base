<%
try{
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!--<fmt:setBundle basename="resources.eis.eisLables_en_US" var="enLables" scope="request"/>


--><fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>
<c:if test="${resultValue.status =='1'}">

<script>



alert("Record Successfully Added");
</script>
</c:if>
<script>
function onclose()
{
		
		
		document.GradeMstView.action="./hrms.htm?actionFlag=getHomePage";
		document.GradeMstView.submit();
		
}
</script>	
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>

<form method="POST" name="GradeMstView" action="./hrms.htm?viewName=gradeMstAdd">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.grade_lst" bundle="${enLables}"/></b></a></li>
		</ul>
	</div>
		  
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	   <br>  
		  
		  <%-- <a href= "./hrms.htm?viewName=gradeMstAdd">  Add new Entry </a>--%>
		  <br>
		  
	
	
	<display:table name="${actionList}" requestURI="" pagesize="${pageSize}" id="row" export="false" style="width:100%">

 
	     
	 <%-- <display:column class="tablecelltext" titleKey="eis.grade_name"  headerClass="datatableheader" > 
	  <a href="./hrms.htm?actionFlag=getGradeData&edit=Y&gradeid=${row.gradeId}">${row.gradeName}  </a>
	  </display:column>	 --%>
	   <display:column  property="gradeName" class="tablecelltext" title="Group Name"  headerClass="datatableheader"  style="text-align: center;font-size:12px;" /> 
		<display:column property="gradeDesc" class="tablecelltext" title="Description"  headerClass="datatableheader" style="text-align: center;font-size:12px;" /> 

     
     
  	  <display:setProperty name="export.pdf" value="false" />

  	  </display:table>
  	  
  	  <br/>
  	  <br/>
		<center>   <hdiits:button name="Back" type="button" captionid="eis.close" bundle="${Lables}" onclick="self.location.href = 'ifms.htm?actionFlag=getHomePage';" /></center><!--onclick="onclose()"  -->
		  
		 <br/>
		 <br/>
	
	  	</div>  
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>

</form>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
  	  