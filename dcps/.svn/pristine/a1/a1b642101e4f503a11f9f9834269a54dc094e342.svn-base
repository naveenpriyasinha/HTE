<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>

<fmt:setBundle basename="resources.hod.reports.DropDownLabels" var="PersonDetail" scope="request"/>
<fmt:setBundle basename="resources.trng.NominationLables" var="nomLable" scope="request" />
<hdiits:form name="personDetail" validate="true" method="post" action="./hdiits.htm?actionFlag=EmployeeInTrainingSubjects" >
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>
<div id="tabmenu">
   <ul id="maintab" class="shadetabs"><li  class="selected"><a href="#"  rel="tcontent1">
   Caption</a></li>				
   </ul>
</div>
<div class="tabcontentstyle"> INNER 
	<div id="tcontent1" class="tabcontent" tabno="0">
	<fmt:message key="TR.SEARCHTRNG"  bundle="${nomLable}" var="trainingTitle"></fmt:message>
	 <!-- table for group and point -->
		<table>
			<tr>  
				<td class="fieldLabel" colspan="6">
				<jsp:include page="trainingSearch.jsp">
				<jsp:param name="searchTrainingTitle" value="${trainingTitle}"/>				
				<jsp:param name="mandatory" value="Yes" />
				</jsp:include>
				</td>
			</tr>
		</table>
	<br><br>
	<center>  <hdiits:submitbutton name="submit" value="submit" caption="hello" /></center>
	
</div>
</div>
<script type="text/javascript">
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>