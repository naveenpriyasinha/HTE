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

<fmt:setBundle basename="resources.eis.eisLables" var="enLables" scope="request"/>



<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>

<c:if test="${resultValue.status =='1'}">

<script>
alert("Record Successfully Added");
</script>
</c:if>


	
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>

<form method="POST" name="componentMstView" action="./hrms.htm?viewName=componentMasteradd">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="eis.component_lst" bundle="${enLables}"></hdiits:caption></a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">	  
	<div id="tcontent1" class="tabcontent">
	   <br>  
		  <a href= "./hrms.htm?actionFlag=getComponentAdd"><hdiits:caption captionid="eis.desig_add_nw_entry" bundle="${enLables}"></hdiits:caption></a>
		  <br><br><br>
		  
	
	
	<display:table name="${actionList}" requestURI=""  defaultsort="1" defaultorder="ascending" id="row" export="true" style="width:100%">

 
	     
	  <display:column class="tablecelltext" titleKey="eis.component_desc"  headerClass="datatableheader" style="text-align:center"> 
	  <a href="./hrms.htm?actionFlag=getComponentAdd&componentid=${row.componentCode}&edit=Y">${row.componentDesc}  </a>
	  </display:column>	
		
		<display:column property="expression" class="tablecelltext" titleKey="eis.component_exp"  headerClass="datatableheader"  style="text-align:center"/> 
      
     
     
  	  <display:setProperty name="export.pdf" value="true" />
  	  </display:table>
		  
		  
		 <br>
	   	 <a href= "./hrms.htm?actionFlag=getComponentAdd">  <hdiits:caption captionid="eis.desig_add_nw_entry" bundle="${enLables}"></hdiits:caption> </a>
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
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
  	  