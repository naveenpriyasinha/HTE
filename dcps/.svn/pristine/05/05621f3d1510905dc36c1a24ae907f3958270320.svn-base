<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>


<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="sectionList" value="${resultValue.sectionList}" > </c:set>

	

   <form method="POST" name="ITSection" action="./hrms.htm?viewName=newScaleMaster">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="SECT.sectView" bundle="${commonLables}"></hdiits:caption></b></a></li>
		</ul>
	</div>
<div class="tabcontentstyle">	  
	
	<div id="tcontent1" class="tabcontent">   
		
		<br>
		<a href="./hrms.htm?actionFlag=fillSectionCombo">  Add new Entry </a>
		<br>	
		 
	  	<display:table name="${sectionList}" requestURI="" pagesize="${pageSize}"  id="row" export="true" >
	
		 <display:column class="tablecelltext" title="Section Code" headerClass="datatableheader" >
		  	<a href="./hrms.htm?actionFlag=getSectionData&sectId=${row.sectId}&edit=Y">${row.sectCode}</a>
		 </display:column>	 
		 <display:column  property="sectName" class="tablecelltext" title="Section Name"  headerClass="datatableheader" />    
		 
		  <display:setProperty name="export.pdf" value="true" />
	  	 </display:table>
		
		<BR>
	 	  
	  	<a href="./hrms.htm?actionFlag=fillSectionCombo">  Add new Entry </a>
 
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
  	  