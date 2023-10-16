<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="msg" value="${resValue.msg}" > </c:set> 
<%
try {
%>


<hdiits:form name="frmBankMaster" validate="true" method="POST"
	action="./hrms.htm?actionFlag=getBranchView" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>Information </b></a></li>
			<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
		</ul>
		
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<br> <br> <br>	<br> <br> <br> <div align="center">
	<h1> ${msg} </h1>
	</div>
 	</div>

</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

	