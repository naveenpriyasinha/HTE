<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>




<fmt:setBundle basename="resources.trng.TrainingMstLables" var="trngLables" scope="request" />
<fmt:setBundle basename="resources.trng.NominationLables" var="nmLables" scope="request" />
<fmt:setBundle basename="resources.trng.AllotmentLables" var="allotLables" scope="request" />

<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/></script>
<script type="text/javascript" src="script/training/training.js"></script>

<hdiits:form name="selectTraining" validate="true" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="insertNominate"/>

<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SelectEditTraining" bundle="${trngLables}"></hdiits:caption> </a></li>
		
		</ul>
</div>



<div class="tabcontentstyle">

<div id="tcontent1" class="tabcontent" tabno="0">

<jsp:include page="ViewNominateTrng.jsp"></jsp:include>



</div>



<script	type="text/javascript">
		initializetabcontent("maintab")
</script> 
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</div>
</hdiits:form>

