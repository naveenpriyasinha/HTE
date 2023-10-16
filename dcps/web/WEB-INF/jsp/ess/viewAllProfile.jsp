
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="../core/include.jsp" %>
<%int i=1; %>
<%
try{%>
	 
	  <c:set var="resultObj" value="${result}" > </c:set>
      <c:set var="empMap" value="${resultObj.resultValue}" > </c:set>	  
	  <c:set var="empList" value="${empMap.empList}" > </c:set>	
	  <display:table list="${empList}" pagesize="12" requestURI="hdiits.htm?actionFlag=viewAllProfile" 
        defaultsort="1" defaultorder="descending" id="row" excludedParams="ajax" varTotals="totals" export="true" style="width:100%">
	<display:setProperty name="paging.banner.placement" value="bottom"  />
    <display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader"  ><%=i++ %> </display:column>
	<display:column  class="tablecelltext" titleKey='EMPLOYEE_ID' sortable="true" headerClass="datatableheader" >${row[0]}</display:column>
	<display:column titleKey='NAME' headerClass="datatableheader"  >${row[1]} ${row[2]} ${row[3]} ${row[4]} </display:column>
    <display:column  class="tablecelltext" titleKey ='DESIGNATION' sortable="true" headerClass="datatableheader">${row[7]}</display:column>    
    <display:footer media="html">
  </display:footer>
  </display:table> 
 
<%
}catch(Exception e){
	e.printStackTrace();
}
%>