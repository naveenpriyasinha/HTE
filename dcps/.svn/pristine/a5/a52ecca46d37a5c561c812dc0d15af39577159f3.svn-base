<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript">
//------------------11-4-2007 Changes----------------------

	function set_values(a1,a2,a3,a4,a5)
	{
		document.frmsearch.actionName.value=a1;
		document.frmsearch.actid.value=a2;
		document.frmsearch.actname.value=a3;
		document.frmsearch.actdesc.value=a4;
		document.frmsearch.editmode.value=a5;
		
		document.frmsearch.action = "ifms.htm?viewName=addAction";
		document.frmsearch.submit();
		
	}
</script>

<% try{ %>

<form name="frmsearch" method="post" action="./ifms.htm" method="post">

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="serviceList" value="${resultValue.serviceList}" > </c:set>
  <display:table list="${serviceList}" pagesize="12" requestURI="ifms.htm?actionFlag=viewAllConfiguration" 
  defaultsort="0" defaultorder="descending" id="row" excludedParams="ajax" varTotals="totals" export="true" style="width:100%">
	<display:setProperty name="paging.banner.placement" value="bottom"  />      	
	<display:setProperty name="paging.banner.items_name" value="this will be at banner"/>
    <display:column property="actionId" class="tablecelltext" titleKey="ACTION_ID" sortable="true" headerClass="datatableheader" total="true" />
    <display:column class="tablecelltext" titleKey='ACTION_NAME' sortable="true" headerClass="datatableheader" > 
    
    <!-- <a href="./ifms.htm?actionFlag=displayactionname&actionName=displayactionname &actid=${row.actionId}&actname=${row.actionName}&actdesc=${row.actionDesc}&editmode=y" > ${row.actionName}</a>  -->
    <a href="#" onclick="set_values('displayactionname','${row.actionId}','${row.actionName}','${row.actionDesc}','${row.actionName}')"> ${row.actionName}</a>
    </display:column>
    
    <display:column property="actionDesc" class="tablecelltext" titleKey='ACTION_DESC' sortable="true" headerClass="datatableheader" group="1" />
    <display:column property="orderNo" class="tablecelltext" titleKey='ORDER_NO' sortable="true" headerClass="datatableheader" />
    <display:footer media="html">
  </display:footer>
  </display:table> 
  
  	 <input type="hidden" name="actionName">
     <input type="hidden" name="actid">    
     <input type="hidden" name="actname">            
     <input type="hidden" name="actdesc">
     <input type="hidden" name="editmode">
</form>
  <% }catch(Exception e)
  {
	 e.printStackTrace();
  }%>
  