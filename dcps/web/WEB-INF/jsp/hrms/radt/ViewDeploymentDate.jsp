<%
try{
%>


<%@ include file="../core/include.jsp" %>

<script type="text/javascript" src="/themes/hdiits/tabcontent.css"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<link rel="stylesheet" type="text/css" href="/themes/hdiits/tabcontent.css"/>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="moduleDetail" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="moduleDetailList" value="${moduleDetail.moduleDetailList}" > </c:set>

	<%int i=1;%>
	<form method="POST" name="testing" action="./hdiits.htm">
<div id="tabmenu">
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="#" rel="tcontent1"></a></li>
	
</ul>
</div>
<div class="tabcontentstyle">	  

 <div id="tcontent1" class="tabcontent">
	  
  <display:table name="${moduleDetailList}" pagesize="12" requestURI=""
  defaultsort="1" defaultorder="ascending" id="row" export="true" style="width:100%">
     
     
     <display:column defaultorder="ascending" class="tablecelltext" titleKey='SR_NO' sortable="true" headerClass="datatableheader" ><%=i++%></display:column>

     <display:column property="dbId" class="tablecelltext" titleKey ='DB_ID' sortable="true" headerClass="datatableheader" />    
     <display:column property="startDate" class="tablecelltext" titleKey='ST_DT' format="{0,date,dd-MM-yyyy}"sortable="true" headerClass="datatableheader" />    
     <display:column property="endDate" class="tablecelltext" titleKey='END_DT' format="{0,date,dd-MM-yyyy}" sortable="true" headerClass="datatableheader" />
     
  	  <display:setProperty name="export.pdf" value="true" />
  	  </display:table>
<BR>

  
  </div>
  	  
  	<a href= "./hdiits.htm?viewName=mainopt">   <fmt:message key="GO_TO_MAIN_MENU" />   <BR><BR>
  	    <a href= "./hdiits.htm?viewName=deploymentdate">  <fmt:message key="SEARCH_AGAIN" />   <BR>
  

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
  	  