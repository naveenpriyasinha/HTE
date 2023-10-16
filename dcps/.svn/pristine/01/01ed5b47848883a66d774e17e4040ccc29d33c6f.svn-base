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
<c:set var="cadreData" value="${resultValue.cadreList}" > </c:set>



<hdiits:form name="CadreMasterView" validate="true" method="POST" action="">
	
		<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="cm.CadreMaster" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
<br>
<!-- <a href= "./hrms.htm?actionFlag=EditCadreDtls" >Add new Entry </a> -->
<br><br>

	
	<display:table name="${cadreData}" requestURI="" pagesize="${pageSize}"  id="row" export="true" style="width:100%">
	<display:column class="tablecelltext" title="Cadre Name" headerClass="datatableheader" > 
	<a href="./hrms.htm?actionFlag=EditCadreDtls&cadreId=${row.cadreId}&edit=Y"> ${row.cadreName} </a></display:column> 
    <display:column class="tablecelltext" title="Cadre Code" headerClass="datatableheader" style="text-align:center" >${row.cadreCode} </display:column>
    <display:column class="tablecelltext" title="Cadre Desc" headerClass="datatableheader" style="text-align:center" > ${row.cadreDesc} </display:column>  
    </display:table>
  	  
<BR>
  	  


  



	<script type="text/javascript">
	initializetabcontent("maintab")
</script>
</hdiits:form>	
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>