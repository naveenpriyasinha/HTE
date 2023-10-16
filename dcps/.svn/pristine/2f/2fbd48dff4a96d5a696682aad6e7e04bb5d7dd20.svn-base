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
<c:set var="aclElementMstList" value="${resValue.aclElementMstList}" scope="session"></c:set>
<c:set var="hideMenuLookupID" value="${resValue.hideMenuLookupID}"></c:set>
<%@page import="java.util.ArrayList"%>
<style type="text/css">
.displaySpecific
{
	background-color:#EEDFCC;
} 

.displayGeneric
{
	background-color:rgb(233,233,233);
}
</style>

<%

request.setAttribute("dyndecorator", new org.displaytag.decorator.TableDecorator()
{

    public String addRowClass()
    {
    	try
    	{
    				String commcat=(((ArrayList)getCurrentRowObject()).get(4)).toString();
    				System.out.println("commcat"+commcat);
    				
    		    	if(commcat.equalsIgnoreCase("Generic"))
		        	{
		        		getPageContext().setAttribute("className","displayDataalt");
		        		return "displayGeneric";
		        	}
		        	else
		        	{
		        		getPageContext().setAttribute("className","displayData");
		        		return "displaySpecific";
		        	}
		        
	     }
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return "displayDataalt";
    	}
    }
});
 %>
<script>

function viewCommuniqueDtl(commnumber)
{
	
	
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0,type=fullWindow,fullscreen'; 
	window.open('hdiits.htm?actionFlag=FMS_viewCommuniqueDetail&moduleName=WorkList&menuName=commonRecAction&attchment=yes&fromDeletedComm=yes&commno='+commnumber,'',urlStyle);


}
function showNewCommunique()
{
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0'; 
	window.open('hdiits.htm?viewName=wf-newcommunique','',urlStyle);
}
function change_parent_url()
{
	document.location='hdiits.htm?actionFlag=FMS_getCommuniqueDetailList&moduleName=WorkList&menuName=commonRecAction';
}
</script>
<hdiits:form name="communiquelistfrm" method="post" validate="true" encType="multipart/form-data">
<br>
	<jsp:include flush="true" page="../workflow/LinkSpecificMenu.jsp">
		<jsp:param value="${hideMenuLookupID}" name="hideMenuLookupID"/>
	</jsp:include>
	<br>
	<fieldset>
	<b><legend ><fmt:message key="WF.Legend" bundle="${fmsLables}"></fmt:message></legend></b>

	<table border="0" >
		<tr>
			<td bgcolor="#EEDFCC" width="10%">
			</td>
			<td>
				Specific Communique
			</td width="40%">
			<td bgcolor="rgb(233,233,233)" width="10%">
			</td>
			<td width="40%">
				Generic Communique
			</td>
		</tr>
	</table>
	</fieldset>

	<display:table list="${commlist}" id="row" style="width:100%" pagesize="10" requestURI="" defaultsort="1"  defaultorder="ascending" decorator="dyndecorator">
	 	<display:column class="tablecelltext" titleKey="WF.CommNo"  sortable="false"  headerClass="datatableheader"  ><a href="#" onclick="viewCommuniqueDtl('${row[0]}')">${row[0]}</a></display:column>
		<display:column class="tablecelltext" titleKey="WF.CommFrom" sortable="false" headerClass="datatableheader"  >${row[1]}</display:column>
		
		<fmt:formatDate value="${row[2]}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="stmtDate"/>
		<display:column class="tablecelltext" titleKey="WF.CommRecDate"  sortable="false" headerClass="datatableheader"  >${stmtDate}</display:column>
		<display:column class="tablecelltext" titleKey="WF.CommSubject" sortable="false" headerClass="datatableheader"  >${row[3]}</display:column>
		<display:column class="tablecelltext" titleKey="WF.CommCat" sortable="false" headerClass="datatableheader"  >${row[4]}</display:column>
		<display:column class="tablecelltext" titleKey="WF.CommStatus" sortable="false" headerClass="datatableheader"  >${row[5]}</display:column>
	</display:table>




</hdiits:form>



<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>