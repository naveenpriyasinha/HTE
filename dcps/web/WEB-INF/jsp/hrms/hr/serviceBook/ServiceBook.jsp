<%@ include file="../../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<html>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<fmt:setBundle basename="resources.eis.eisLables" var="empEditListCommonLables" scope="request" />




<hdiits:form name="frmServiceBook" validate="true" method="POST"
	action=" " encType="multipart/form-data">


	<div id="tabmenu">
	
<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			Personal Details 
		</b></a></li>
		<li class="selected"><a href="/hdiits.htm?actionFlag=reportService&reportCode=330110&action=generateReport&dynamicReport=false" rel="tcontent2"><b>
			Service Details 
			</b></a></li>
</ul>

		

</div>	


		<div id="tcontent1" class="tabcontent" tabno="0">
		
		 <%@ include file="/WEB-INF/jsp/hrms/hr/serviceBook/mainSrvcBook.jsp"%>
		</div>
		
		<div id="tcontent2" class="tabcontent" tabno="1">
	   <%@ include file="/WEB-INF/jsp/hrms/hr/serviceBook/ServiceBookByAppWise.jsp"%>
		
		</div>
		


	
		
				
</hdiits:form>
<script type="text/javascript">

		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		
		initializetabcontent("maintab");
</script>