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
<hdiits:form name="defulaterlistfrm" method="post" validate="true" encType="multipart/form-data">
	<display:table list="${commlist}" id="row" style="width:100%" pagesize="10"  >
	<display:column class="tablRecelltext" titleKey="WF.EMPNAME"  headerClass="datatableheader"  >${row[0]}</display:column>
	<fmt:formatDate value="${row[1]}" pattern="dd/MM/yyyy HH:mm" dateStyle="medium" var="sentDate"/>
	<display:column class="tablRecelltext" titleKey="WF.SendDate"   headerClass="datatableheader"  >${sentDate}</display:column>
	</display:table>
</br>
</br>	
<center>
<hdiits:button name="closebtn" captionid="WF.Close" bundle="${fmsLables}"  type="button"  onclick="window.close()"/>

</center>
	
</hdiits:form>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>