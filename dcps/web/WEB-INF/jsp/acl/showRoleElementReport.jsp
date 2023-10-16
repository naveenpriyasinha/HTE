<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="com.tcs.sgv.acl.service.Permission"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ElementList" value="${resValue.elementRoleList}"></c:set>
<c:set var="parameterMap" value="${resValue.parameterMap}"></c:set>
<c:set var="roleId" value="${parameterMap.roleId }"></c:set>
<c:set var="roleName" value="${parameterMap.roleName }"></c:set>
<c:set var="permission" value="${parameterMap.permission }"></c:set>
<c:set var="langId" value="${parameterMap.langId}"></c:set>
<c:set var="elementType" value="${parameterMap.elementType}"></c:set>

<c:set var="str" value="roleId=${roleId}&permission=${permission}&langId=${langId}&actionType=getChildElements"></c:set>
<c:set var="hlink" value="./hdiits.htm?actionFlag=getRoleElementReport&${str}&parentId="></c:set>

 

<%
int i = 1;
%>	


<body>
<form name="form1" method="post" action="./hdiits.htm" >
		 
		  <c:if test="${elementType == 'MODULE'}">
		
			<display:table list="${ElementList}" id="row" requestURI="" pagesize="12"  export="true" style="width:100%">
			
			<display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader" ><%=i++%></display:column>
			<display:column class="tablecelltext" titleKey="Role_Name" headerClass="datatableheader" sortable="true" >${roleName}</display:column>
			<display:column class="tablecelltext" titleKey="Mname" headerClass="datatableheader" sortable="true" > <a href="${hlink}${row.elementId}&elementType=SCREEN&moduleName=${row.elementName}">${row.elementName}</a></display:column>
			<c:set var="permission" value="${row.permissionObject}" > </c:set>
		<% 	Permission permissionObj=(Permission)pageContext.getAttribute("permission");
		    String permissionStr=permissionObj.toString(); %>
			<display:column class="tablecelltext" titleKey="Permission" headerClass="datatableheader"  sortable="true"><%= permissionStr%></display:column>
			
			
		</display:table>
				
			
	</c:if>
	
	<c:set var="moduleName" value="${parameterMap.moduleName}"></c:set>

	<c:if test="${elementType == 'SCREEN'}">
	
			<b>Module Name : </b><font color='blue'>[ <c:out value="${moduleName}"/> ]</font><br>
		 	<display:table list="${ElementList}" id="row" requestURI="" pagesize="12"  export="true" style="width:100%">
			
			<display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader" ><%=i++%></display:column>
			<display:column class="tablecelltext" titleKey="Role_Name" headerClass="datatableheader"  sortable="true">${roleName}</display:column>
			<display:column class="tablecelltext" titleKey="Sname" headerClass="datatableheader" sortable="true" > <a href="${hlink}${row.elementId}&elementType=FIELD&moduleName=${moduleName}&screenName=${row.elementName}">${row.elementName}</a></display:column>
			<c:set var="permission" value="${row.permissionObject}" > </c:set>
		<% 	Permission permissionObj=(Permission)pageContext.getAttribute("permission");
		    String permissionStr=permissionObj.toString(); %>
			<display:column class="tablecelltext" titleKey="Permission" headerClass="datatableheader"  sortable="true"><%= permissionStr%></display:column>
			
			
		</display:table>
				
	</c:if>
	<c:if test="${elementType == 'FIELD'}">		
	
			<c:set var="screenName" value="${parameterMap.screenName}"></c:set>
			 <b>Module Name : </b><font color='blue'>[ <c:out value="${moduleName}"/> ]</font><br>
			<b>Screen Name : </b><font color='blue'>[ <c:out value="${screenName}"/> ]</font><br>
			<display:table list="${ElementList}" id="row" pagesize="12" requestURI="" export="true" style="width:100%">
			
			<display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader" ><%=i++%></display:column>
			<display:column class="tablecelltext" titleKey="Role_Name" headerClass="datatableheader"  sortable="true">${roleName}</display:column>
			<display:column class="tablecelltext" titleKey="Fname" headerClass="datatableheader" sortable="true">${row.elementName}</display:column>
			<c:set var="permission" value="${row.permissionObject}" > </c:set>
		<% 	Permission permissionObj=(Permission)pageContext.getAttribute("permission");
		    String permissionStr=permissionObj.toString(); %>
			<display:column class="tablecelltext" titleKey="Permission" headerClass="datatableheader"  sortable="true"><%= permissionStr%></display:column>
			
			
		</display:table>
				
	</c:if>  
			
			
		
	
	</form>
</body>




