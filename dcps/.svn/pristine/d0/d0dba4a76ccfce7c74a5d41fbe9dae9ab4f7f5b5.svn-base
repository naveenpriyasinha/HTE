<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.acl.acl" var="accessControlLables" scope="application"/>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="postRoleList" value="${resValue.postRoleList}"></c:set>
 <%
int i = 1;
%>	

<body>
			<display:table list="${postRoleList}" pagesize="12" requestURI="" id="row"  defaultsort="1"  export="true" style="width:100%">
			
			<display:column titleKey="SERIAL_NUMBER" headerClass="datatableheader" sortable="true"><%=i++%></display:column>
			<display:column titleKey="Post_Name" headerClass="datatableheader" sortable="true">${row.orgPostMst.postName}</display:column>

			<display:column titleKey="Role_Name" headerClass="datatableheader" sortable="true">${row.aclRoleMst.roleName}</display:column>
		</display:table>
</body>

 