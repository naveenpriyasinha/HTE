<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
  <c:out value='${resultObj.resultValue.result}' /> 
<c:set var="resValue" value="${resultObj.resultValue.result}" > </c:set>
<c:set var="DeptList" value="${resultObj.resultValue.resultSet}">
</c:set> 
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<fmt:setLocale value="${sessionScope.locale}"/>

	<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

${resValue.msg}

<script type="text/javascript" language="JavaScript">
function submitForm(){ 
	win = window.open("common/progressbar.html",'','width=270,height=30,titlebar=0,toolbar=0,left=400,top=300');
	return true;
}
function init()
{
	//alert(${resValue.msg});
	
}
</script>
<hdiits:form name="deptMaster" validate="true" method="POST"
	action="./hdiits.htm?viewName=DeptMaster" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#"  rel="tcontent2"><b> <hdiits:caption captionid="HR.EIS.DeptMaster" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="tabcontentstyle">
	 
	<div id="tcontent2" class="tabcontent" tabno="1">
	
	   	  <c:out value="${resultValue.msg}" /> <br>
	      <a href= "./hrms.htm?viewName=DeptMaster">  Add new Entry </a>
		  <br>
		  <display:table pagesize="${pageSize}" name="${DeptList}" requestURI=""  defaultsort="1" defaultorder="descending" id="row" export="true" style="width:100%">
			  <display:column class="tablecelltext" title="Department Name" headerClass="datatableheader" > 
	          <a href="./hrms.htm?actionFlag=getDeptData&deptId=${row.deptId}&edit=Y">${row.deptName}  </a>
	          </display:column>	
			  <display:column class="tablecelltext" title="Department Short Name" headerClass="datatableheader" value="${row.deptShortName}" > </display:column>	
			  <display:column  class="tablecelltext" title="Department Description" headerClass="datatableheader" value="${row.deptDesc}"> </display:column>	
		  	  <display:setProperty name="export.pdf" value="true" />
  		  </display:table>
		 <br>
	   	 <a href= "./hrms.htm?viewName=DeptMaster">  Add new Entry </a>
	
	</div>

	
</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript" language="javascript">
init();
</script>

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


