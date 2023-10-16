

<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="java.util.List"%>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src=<c:url value="/script/hrms/eis/Address.js"/>"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="gMangData" value="${resultValue.gMangData}" > </c:set>
<c:set var="msg" value="${resultValue.msg}" > </c:set>
<script>Ststem.out.println(":::::::msg:::::::::: " + msg); </script>
<c:out value="${msg}"/>
<script>
if("${msg}"!=null&&"${msg}"!='')
{
	alert("${msg}");					
}
	

</script>
<hdiits:form name="GroupManagementView" validate="true" method="POST" action="">
	
		<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="gm.GroupManagementView" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
<br>
<a href= "./hrms.htm?actionFlag=showGroupManagement" >Add new Entry </a>
<br><br>



<display:table name="${gMangData}" requestURI=""    pagesize="${pageSize}"   id="row" defaultorder="ascending" export="true" style="width:100%">
	
             <display:column class="tablecelltext" title="Type"  headerClass="datatableheader" style="text-align:center" >${row.typeName}</display:column>				  
			 <display:column class="tablecelltext"  title="Component" headerClass="datatableheader" style="text-align:center" >
			 <a href=  "./hrms.htm?actionFlag=updateGrpMangDtls&groupManagementId=${row.grpMangId}&edit=Y" id="otherLink${row.componentName}"><b>${row.componentName } </b> </a>
		     </display:column>																					
		  	 <display:column  class="tablecelltext"  title="Start Date" headerClass="datatableheader" style="text-align:center" >${row.startDate}</display:column>
		  	 <display:column  class="tablecelltext"  title="End Date" headerClass="datatableheader" style="text-align:center" >${row.endDate}</display:column>
		  	 <display:column  class="tablecelltext"  title="Amount" headerClass="datatableheader" style="text-align:center" >${row.amount}</display:column>
		  	 <display:column  class="tablecelltext"  title="Class" headerClass="datatableheader" style="text-align:center" >${row.gradeName}</display:column>
		  	 <display:column  class="tablecelltext"  title="Designation" headerClass="datatableheader" style="text-align:center" >${row.dsgnName}</display:column>
  		  </display:table>

<br>
<a href= "./hrms.htm?actionFlag=showGroupManagement" >Add new Entry </a>

	<script type="text/javascript">
	initializetabcontent("maintab")
</script>
</hdiits:form>	
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>




	
	



