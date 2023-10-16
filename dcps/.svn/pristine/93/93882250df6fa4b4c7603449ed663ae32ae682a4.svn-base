<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="../core/include.jsp" %>
<%@page import="java.util.List"%>

<%@page import="com.tcs.sgv.ess.valueobject.OrgUserMst"%>
<fmt:setBundle basename="resources.acl.acl" var="accessControlLables" scope="application"/>

<%
try
{
%> 
  <script ="text/javascript" src="script/common/tabcontent.js">
</script>


<script type="text/javascript">
function validate_form(thisform)
{
	
	var total = document.forms[0].userid.length;
		var selected = false;
	if (!total)
	{
		if(document.forms[0].userid.checked==true)		
			selected = true;
	}	
	else
	{
		for (i=0;i<total;i++)
		{
			if(document.forms[0].userid[i].checked==true)
			{
				selected = true;
				break;
			}
		}
	}
	if (selected==false)
	{
		alert("Select a role to delete");
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
<c:set var="UserList" value="${resultValue.UserList}" > </c:set>
<c:set var="searchName" value="${resultValue.searchName}" > </c:set>
<c:set var="delete" value="${resultValue.delete}" > </c:set>
<%String searchName=pageContext.getAttribute("searchName").toString();
if(!("All".equals(searchName)))
{
%>
<c:set var="searchValue" value="${resultValue.searchValue}" > </c:set>
<input type="hidden" name="searchValue" value="${searchValue}"/>	

 
 <%}
List<OrgUserMst> UserList=(List<OrgUserMst>)(pageContext.getAttribute("UserList"));

	if(UserList.size()!=0)
	{
		String delete=pageContext.getAttribute("delete").toString();
		
		if("true".equals(delete))
		{
%>

	<input type="submit" value="Delete" class="DispButton" onsubmit/>
	<%} %>
	<input type="hidden" name="actionFlag" value="deleteUser"/>	
	<input type="hidden" name="searchName" value="${searchName}"/>							
	

<%
	
int i = 1;
%>	 	

	<display:table list="${UserList}" pagesize="12" requestURI="" id="row"  export="true" style="width:100%">
	<display:setProperty name="ListOfActions.pdf" value="true" />
	
<display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader"  ><%= i++ %> </display:column>  	
<display:column property="userId" class="tablecelltext" titleKey="User_id" sortable="true" headerClass="datatableheader" />
<display:column class="tablecelltext" titleKey="User_name"  sortable="true" headerClass="datatableheader" >	<% if(!("true".equals(delete))){%> <a style="cursor:hand" onClick="getSelectedValue('${row.userName}','${row.userId}')">${row.userName}</a><% }else {%>${row.userName} <%}%></display:column>
<c:set var="userStatus" value="${row.cmnLookupMst.lookupName}" > </c:set>
    <display:column  class="tablecelltext" titleKey="User_Status" headerClass="datatableheader"><c:out value="${userStatus}"/></display:column>	
	<%    	if("true".equals(delete))
   	{%>
    <display:column class="tablecelltext" titleKey="Del" headerClass="datatableheader" media="HTML"><input name="userid" value="${row.userId}"  index="<%=i%>" type="checkbox" > </display:column>	
  <%} %>
</display:table>   
    
    
<script text="javascript">
    function getSelectedValue(userName,userId)
	{
		window.opener.parent.document.getElementById("userTableId").style.display='';
		window.opener.parent.document.form1.UserName.value=userName;
		window.opener.parent.document.form1.userId.value=userId;
		
		window.close();
	}
</script>
</form>
<%
	}
else
{
	out.println("No User Found");
}
  	
}catch(Exception e){
	e.printStackTrace();
}
%>      