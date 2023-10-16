<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="../core/include.jsp" %>
<%@page import="java.util.List"%>

<%@page import="com.tcs.sgv.acl.valueobject.AclRoleMst"%>
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
	
	var total = document.forms[0].roleid.length;
		var selected = false;
	if (!total)
	{
		if(document.forms[0].roleid.checked==true)		
			selected = true;
	}	
	else
	{
		for (i=0;i<total;i++)
		{
			if(document.forms[0].roleid[i].checked==true)
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
<c:set var="RoleList" value="${resultValue.RoleList}" > </c:set>
<c:set var="searchName" value="${resultValue.searchName}" > </c:set>
<c:set var="delete" value="${resultValue.delete}" > </c:set>
<%String searchName=pageContext.getAttribute("searchName").toString();

if(!("All".equals(searchName)))
{
%>
<c:set var="searchValue" value="${resultValue.searchValue}" > </c:set>
<input type="hidden" name="searchValue" value="${searchValue}"/>	

 
   <%}
	List<AclRoleMst> RoleList=(List<AclRoleMst>)(pageContext.getAttribute("RoleList"));
  
  	if(RoleList.size()!=0)
   	{
  		String delete=pageContext.getAttribute("delete").toString();
  		
  		if("true".equals(delete))
  		{
   %>

		<input type="submit" value="Delete" class="DispButton" onsubmit/>
		<%} %>
		<input type="hidden" name="actionFlag" value="deleteRole"/>	
		<input type="hidden" name="searchName" value="${searchName}"/>							
		
	
<%
  	
int i = 1;
int counter=0;
%>	 	
	
   	<display:table list="${RoleList}" pagesize="12" requestURI="" id="row"  export="true" style="width:100%" decorator="com.tcs.sgv.acl.decorator.TableDecoratorForViewRole">
   	<%

   	AclRoleMst aclRoleMstVO = (AclRoleMst) RoleList.get(counter);
			pageContext.setAttribute("aclRoleMstVO", aclRoleMstVO);
		%>
   	<display:setProperty name="ListOfActions.pdf" value="true" />
   	
	<display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader"  ><%= i++ %> </display:column>  	
	<display:column property="roleId" class="tablecelltext" titleKey="Role_id" sortable="true" headerClass="datatableheader" />
	
	<% if(!("true".equals(delete))){%>
	<a style="cursor:hand" onClick="getSelectedValue('this.innerHTML','${row.roleId}')">
    <display:column class="tablecelltext" titleKey="Role_Name"   sortable="true" headerClass="datatableheader" property="roleName" ></display:column>
     
    <% }
	else {%>
	 <display:column class="tablecelltext"  titleKey="Role_Name"   sortable="true" headerClass="datatableheader" property="roleName" ></display:column>
	<%}%>
    
	<display:column  class="tablecelltext" titleKey="Role_ShortName" sortable="true" headerClass="datatableheader" property="roleShortName" />
	
    <display:column  class="tablecelltext" titleKey="Role_Desc" sortable="true" headerClass="datatableheader" property="roleDesc"  >    	
    	</display:column>
   <c:set var="roleStatus" value="${row.cmnLookupMstByStatus.lookupName}" > </c:set>
    <display:column  class="tablecelltext" titleKey="Role_Status" headerClass="datatableheader"><c:out value="${roleStatus}"/></display:column>	
   	<%    	if("true".equals(delete))
   	{%>
    <display:column class="tablecelltext" titleKey="Del" headerClass="datatableheader" media="HTML"><input name="roleid" value="${row.roleId}"  index="<%=i%>" type="checkbox" > </display:column>	
  <%} %>
  
    <%
		counter++;
		%>
  </display:table>   
        
        
 <script text="javascript">
        function getSelectedValue(roleName,roleId)
		{
			alert(roleName);
			window.opener.parent.document.getElementById("roleTableId").style.display='';
			window.opener.parent.document.form1.roleName.value=roleName;
			window.opener.parent.document.form1.roleId.value=roleId;
			
			window.close();
		}
</script>
</form>
<%
   	}else
   	{
   		out.println("No Role Found");
   	}
  	
}catch(Exception e){
	e.printStackTrace();
}
%>      