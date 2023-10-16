<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="../core/include.jsp" %>

<%@ page contentType="text/html;charset=UTF-8"%>			
<%
try
{
%>
  <link rel="stylesheet" type="text/css" href="common/css/tabcontent.css"/>
  <script ="text/javascript" src="common/script/tabcontent.js">
</script>


<script type="text/javascript">
function validate_form(thisform)
{
	var r=confirm("Are you sure? you want to delete this")
	if (r==true)
   	{
    	return true
    }
  	else
    {
    	return false
    }
}


</script>

<form name="frmcsearch" method="POST" action="./ifms.htm" onsubmit="return validate_form(this)" method="post">
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="configurationList" value="${resultValue.configurationList}" > </c:set>

 <!--   <input type="button" value="delete" name="actionFlag" value="deleteConfiguration"> 			  -->	  
		<input type="submit" value="delete" class="DispButton"/>
		<input type="hidden" name="actionFlag" value="deleteActionConfiguration"/>							
<%
int i = 1;
%>	 	  
  <display:table list="${configurationList}" pagesize="12" requestURI="ifms.htm?actionFlag=viewConfiguration"
   id="row" excludedParams="ajax" varTotals="totals" export="true" partialList="" style="width:100%">
	<display:setProperty name="paging.banner.placement" value="bottom"  />    
	<display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader" defaultorder="ascending" sortName="external" ><%= i %> </display:column>  	
	<display:column class="tablecelltext" titleKey='DELETE' headerClass="datatableheader" ><input name="serviceid" value="${row.actionId}"  index="<%=i%>" type="checkbox" > </display:column>	
	<display:column class="tablecelltext" titleKey='ACTION_NAME'
    sortable="true" headerClass="datatableheader" > <a href="./ifms.htm?actionFlag=displayactionname&actionName=displayactionname &actid=${row.actionId}&actname=${row.actionName}&actdesc=${row.actionDesc}&editmode=y" > ${row.actionName}</a> </display:column>
    <display:column property="actionDesc" class="tablecelltext" titleKey='ACTION_DESC' sortable="true" headerClass="datatableheader" />
    <% i++; %>
    <display:footer media="html">
  </display:footer>
  </display:table>  
        </form>
<%
}catch(Exception e){
	e.printStackTrace();
}
%>      