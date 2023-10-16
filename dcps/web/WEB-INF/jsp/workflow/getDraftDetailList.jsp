<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="draftlist" value="${resValue.draftlist}"></c:set>
<c:set var="aclElementMstList" value="${resValue.aclElementMstList}" scope="session"></c:set>
<c:set var="hideMenuLookupID" value="${resValue.hideMenuLookupID}"></c:set>
<script>

function viewDraftDtl(commnumber)
{
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0,type=fullWindow,fullscreen'; 
	window.open('hdiits.htm?actionFlag=FMS_viewDraftDetail&commno='+commnumber,'',urlStyle);
	
}
function change_parent_url()
{
	document.location='hdiits.htm?actionFlag=FMS_getCommuniqueDraftDetailList&moduleName=WorkList&menuName=CommuniqueDraftMenu'
}

</script>
<hdiits:form name="draftlistfrm" method="post" validate="true" encType="multipart/form-data">
<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
</jsp:include>
<br>

	<display:table list="${draftlist}" id="row" style="width:100%" pagesize="10" requestURI="" defaultsort="1"  defaultorder="ascending">
	 	<display:column class="tablecelltext" title="Draft No" sortable="true"  headerClass="datatableheader"  ><a href="#" onclick="viewDraftDtl('${row[0]}')">${row[0]}</a></display:column>
		<display:column class="tablecelltext" title="Subject" sortable="true" headerClass="datatableheader"  >${row[1]}</display:column>
		<display:column class="tablecelltext" title="Draft Date" sortable="true"  headerClass="datatableheader"  >${row[2]}</display:column>
		
	</display:table>




</hdiits:form>



<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>