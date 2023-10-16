<%
try {
	

%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="commlist" value="${resValue.commlist}"></c:set>

<script>

function viewCommuniqueDtl(commnumber,frompost,initatorcommno)
{
	intiatorcommno=initatorcommno;
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'; 
	window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&attchment=yes&commno='+commnumber+'&intiatorcommno='+intiatorcommno,'',urlStyle);
}


</script>
<hdiits:form name="communiquelistfrm" method="post" validate="true" encType="multipart/form-data">



	<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
	</jsp:include>
	<br>
	

	<display:table list="${commlist}" id="row" style="width:100%" pagesize="10" >
		

	 	<display:column class="tablecelltext" titleKey="WF.CommNo"  headerClass="datatableheader"  ><a href="#" onclick="viewCommuniqueDtl('${row[0]}','${resValue.loginPost}','${row[0]}')">${row[0]}</a></display:column>
		<display:column class="tablecelltext" titleKey="WF.CommFrom"  headerClass="datatableheader"  >${row[1]}</display:column>
		
		<display:column class="tablecelltext" titleKey="WF.CommSubject"   headerClass="datatableheader"  >${row[3]}</display:column>
		<fmt:formatDate value="${row[4]}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="recDate"/>
		<display:column class="tablecelltext" titleKey="WF.CommRecDate"   headerClass="datatableheader"  >${recDate}</display:column>
		
		
		
	</display:table>




</hdiits:form>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>