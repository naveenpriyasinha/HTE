<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%int i=1;%>

<c:set var="resultObj" value="${result}" > </c:set>	

	  <c:set var="configurationList" value="${resultObj.resultValue}" > </c:set>	  

  <display:table list="${configurationList}" pagesize="12" requestURI="ifms.htm?actionFlag=viewAllConfiguration" 
  defaultsort="0" defaultorder="descending" id="row" excludedParams="ajax" varTotals="totals" export="true" style="width:100%">
	<display:setProperty name="paging.banner.placement" value="bottom"  />      	
	<display:setProperty name="paging.banner.items_name" value="this will be at banner"/>
    <display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader"  ><%=i++ %> </display:column>
    <display:column property="stype" class="tablecelltext" titleKey="SERVICE_TYPE" sortable="true" headerClass="datatableheader" total="true" />
    <display:column property="sclass" class="tablecelltext" titleKey='SERVICE_CLASS' sortable="true" headerClass="datatableheader"  />
    <display:column property="smethod" class="tablecelltext" titleKey='SERVICE_METHOD' sortable="true" headerClass="datatableheader" group="1" />
    
    <display:column property="sdesc" class="tablecelltext" titleKey ='SERVICE_DESC' sortable="true" headerClass="datatableheader" />    
    <display:column property="sname" class="tablecelltext" titleKey='SERVICE_NAME' sortable="true" headerClass="datatableheader" />    
    <display:column property="sstatus" class="tablecelltext" titleKey='SERVICE_STATUS' sortable="true" headerClass="datatableheader" />
    <display:column property="saname" class="tablecelltext" titleKey='ACTION_NAME' sortable="true" headerClass="datatableheader"  />
    <display:footer media="html">
  </display:footer>
  </display:table> 
  