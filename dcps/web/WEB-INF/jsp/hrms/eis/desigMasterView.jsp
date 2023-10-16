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
	
	<script>
function onclose()
{
		
		
		document.DesigMstView.action="./hrms.htm?actionFlag=getHomePage";
		document.DesigMstView.submit();
		
}
</script>	
	
	
	
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>



	
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>

<form method="POST" name="DesigMstView" action="./hrms.htm?viewName=dsgnMasteradd">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="eis.desig_dtl" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">	  
	<div id="tcontent1" class="tabcontent">
	   <br> 
		  <br>
		  
	
	<!-- <a href= "./hrms.htm?viewName=dsgnMasteradd">  Add new Entry </a> -->
	<display:table name="${actionList}" requestURI=""  pagesize="${pageSize}" id="row" export="true" style="width:100%">

 
	     
	  <display:column class="tablecelltext"  title="Name"  headerClass="datatableheader" > 
	     <a href="./hrms.htm?actionFlag=getDesigData&desigid=${row.dsgnId}&edit=Y">${row.dsgnName}  </a> 
	   </display:column>
		<display:column value ="${row.dsgnName}" class="tablecelltext" title="Description"  headerClass="datatableheader" /> 
		<display:column property="dsgnShrtName" class="tablecelltext" title="Short Name"  headerClass="datatableheader" /> 
     
     
  	  <display:setProperty name="export.pdf" value="true" />
  	  </display:table>
		  	<center>   <hdiits:button name="Back" type="button" captionid="eis.close" bundle="${commonLables}" onclick="onclose()"/></center>
		  
		 <br>
	<!-- <a href= "./hrms.htm?viewName=dsgnMasteradd">  Add new Entry </a>	   -->
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>
</div>
</form>
<c:if test="${resultValue.status =='1'}">

<script>
alert("Record Successfully Added");

</script>
</c:if>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
  	  