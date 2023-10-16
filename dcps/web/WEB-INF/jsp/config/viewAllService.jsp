<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<c:set var="resultObj" value="${result}" > </c:set>	

<form name="frmcsearch" method="POST" action="./ifms.htm" onsubmit="return validate_form(this)" method="post">
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="serviceList" value="${resultValue.serviceList}" > </c:set>
	<div align="right"><input type="submit" value="delete"  class="DispButton"/></div>
		<input type="hidden" name="actionFlag" value="deleteConfiguration"/>	
<%
int i = 1;
%>

  <display:table list="${serviceList}" pagesize="12" requestURI="ifms.htm?actionFlag=viewAllConfiguration" 
  defaultorder="descending" id="row" excludedParams="ajax" varTotals="totals" export="true" style="width:100%">
	<display:setProperty name="paging.banner.placement" value="bottom"  />      	
	<display:setProperty name="paging.banner.items_name" value="this will be at banner"/>
    <display:column class="tablecelltext" titleKey="SERIAL_NUMBER" sortable="true" headerClass="datatableheader" >
    <%=i++%>
    </display:column>    
	<display:column property="serviceType" class="tablecelltext" titleKey='SERVICE_TYPE' sortable="true" headerClass="datatableheader"  />   
    
    <display:column class="tablecelltext" titleKey='SERVICE_CLASS'
    sortable="true" headerClass="datatableheader" > <a href="ifms.htm?viewName=addService&srvcid=${row.serviceId}
    &srvclass='${row.serviceClass }'&srvdesc='${row.serviceDesc}'&srvmethod='${row.serviceMethod}'&srvname='${row.serviceName}'
    &srvstatus='${row.serviceStatus}'&srvtype='${row.serviceType}'&crtdate='${row.createdDate}'&crtempid='${row.createdEmpId}'
    &updtdate='${row.updatedDate}'&updtempid=${row.updatedEmpId}&editmode=y" > ${row.serviceClass}</a> </display:column>

    <display:column property="serviceMethod" class="tablecelltext" titleKey ='SERVICE_METHOD' sortable="true" headerClass="datatableheader" />    
    <display:column property="serviceDesc" class="tablecelltext" titleKey='SERVICE_DESC' sortable="true" headerClass="datatableheader" />    
    
    <display:column property="serviceName" class="tablecelltext" titleKey='SERVICE_NAME' sortable="true" headerClass="datatableheader"  />
    <display:column class="tablecelltext" titleKey='DELETE' headerClass="datatableheader" ><input name="serviceid" value="${row.serviceId}"  index="<%=i%>" type="checkbox" > </display:column>	
    <display:footer media="html">
  </display:footer>
  </display:table>
    </form>
  