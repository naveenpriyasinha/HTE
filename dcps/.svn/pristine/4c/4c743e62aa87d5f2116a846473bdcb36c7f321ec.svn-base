<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>


<fmt:setBundle basename="resources.trng.TrainerMstLables" var="trainerLabels" scope="request" />

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<hdiits:form name="selectTrainer" validate="true" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="addTrainer"/>
<hdiits:hidden name="editFlag" default="Y"/>
<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SelectTrainer" bundle="${trainerLabels}"></hdiits:caption> </a></li>
		</ul>
</div>

<div id="tcontent1" class="tabcontent" tabno="0">
<fmt:message key="TR.Trainer"  bundle="${trainerLabels}" var="trainerTitle"></fmt:message>
<TABLE class="tabtable">
<tr>  
	<td class="fieldLabel" colspan="6">
	<jsp:include page="trainerSearch.jsp">
		<jsp:param name="searchTrainerTitle" value="${trainerTitle}"/>
		<jsp:param name="mandatory" value="Yes" />
	</jsp:include>
	</td>
</tr>
<tr>
	<td class="fieldLabel" colspan="6" align="center"><hdiits:formSubmitButton name="formApproveButton" type="button" captionid="TR.EDIT" bundle="${trainerLabels}"/></td>
</tr>
</TABLE>

<br>
		

</div>


<script	type="text/javascript">
		initializetabcontent("maintab")
</script> 
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>