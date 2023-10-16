<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="../include.jsp" %>

<%@ page contentType="text/html;charset=UTF-8"%>			
  <script ="text/javascript" src="script/common/tabcontent.js">
</script>


<script type="text/javascript">
function validate_form(thisform)
{
	 
	var total = document.forms[0].actionid.length;
	
		var selected = false;
	if (!total)
	{
		if(document.forms[0].actionid.checked==true)		
			selected = true;
	}	
	else
	{
		for (i=0;i<total;i++)
		{
			if(document.forms[0].actionid[i].checked==true)
			{
				selected = true;
				break;
			}
		}
	}
	if (selected==false)
	{
		alert("Select it");
		return false;
	}
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

<form name="frmcsearch" method="POST" action="./hdiits.htm" onsubmit="return validate_form(this)" method="post">
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>

 <!--   <input type="button" value="delete" name="actionFlag" value="deleteConfiguration"> 			  -->	  
		<input type="submit" value="Delete" class="DispButton"/>
		<input type="hidden" name="actionFlag" value="deleteActionConfiguration"/>							
<%
int i = 1;
%>	 	  
  <display:table list="${actionList}" pagesize="12" requestURI="hdiits.htm?actionFlag=viewConfiguration"
   id="row" excludedParams="ajax" varTotals="totals" export="true" partialList="" style="width:100%">
	<display:setProperty name="paging.banner.placement" value="bottom"  />    
	<display:column class="tablecelltext" titleKey="SERIAL_NUMBER"  media="HTML" headerClass="datatableheader" defaultorder="ascending" sortName="external" ><%= i %> </display:column>  	
	<display:column class="tablecelltext" media="HTML" titleKey='DELETE' headerClass="datatableheader" ><input name="actionid" value="${row.actionId}"  index="<%=i%>" type="checkbox" > </display:column>	
	<display:column class="tablecelltext" titleKey='ACTION_NAME'
    sortable="true" headerClass="datatableheader" media="HTML"> <a href="./hdiits.htm?actionFlag=getActiondetails&actionName=displayactionname &actid=${row.actionId}&actname=${row.actionName}&actdesc=${row.actionDesc}&actstatus=${row.actionStatus}&editmode=y" > ${row.actionName}</a> </display:column>
    <display:column property="actionDesc" class="tablecelltext" titleKey='ACTION_DESC' sortable="true" headerClass="datatableheader" />
    <% i++; %>
    <c:set var="actionStatus" value="${row.actionStatus}"  > </c:set>
    <%String actionStatus=pageContext.getAttribute("actionStatus").toString();
    %>
    <display:column class="tablecelltext" title="ACTION_STATUS" media="HTML" headerClass="datatableheader"><input name="action_Status" type="checkbox" value="ACTV" index="<%=i%>" disabled="disabled" <%if(actionStatus.equals("ACTV")){%>checked="true" <%} %>>ACTV</display:column>
    <display:footer media="html">
  </display:footer>
   	<display:setProperty name="export.pdf" value="true" />
  </display:table>  
   </form>
