<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eisLables" var="empEditListCommonLables" scope="request"/>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<%
try {
%>


<hdiits:form name="frmBankMaster" validate="true" method="POST"
	action="./hrms.htm?actionFlag=getBranchView" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="eis.MessagePage" bundle="${empEditListCommonLables}"/></a></li>
		</ul>
		
	</div>
	
	<div id="tcontent1" class="tabcontent" tabno="0">
	<br> <br> <br>	<br> <br> <br> 
	
	<center>
<hr width="70%" align="center"> 
	<center><b><c:out value="${resValue.ApprovedReject}"></c:out></b></center>
<hr width="70%" align="center">
</center>

 	</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>"'/>
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

	