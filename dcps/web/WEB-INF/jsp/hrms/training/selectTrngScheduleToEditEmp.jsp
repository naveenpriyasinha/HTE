 
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>

<fmt:setBundle basename="resources.trng.selectTrngScheduleToAddEmp" var="selectTrngScheduleToAddEmp" scope="request" />
<fmt:setBundle basename="resources.hod.reports.DropDownLabels" var="PersonDetail" scope="request"/>
<hdiits:form name="personDetail" validate="true" method="post" action="./hdiits.htm?actionFlag=EmployeeInTrainingSubjects" >
<script type="text/javascript"
	src="<c:url value="script/common/calendar.js"/>">
	</script>
<div id="tabmenu">
   <ul id="maintab" class="shadetabs">
   		<li  class="selected"><a href="#"  rel="tcontent1">
			   <hdiits:caption	captionid="TR.caption" bundle="${selectTrngScheduleToAddEmp}" /></a></li>				
   </ul>
</div>
<div class="tabcontentstyle"> 
	<div id="tcontent1" class="tabcontent" tabno="0">
	 <fmt:message key="TR.search"  bundle="${selectTrngScheduleToAddEmp}" var="trngTitle"></fmt:message>
		<table>
			<tr>  
				<td class="fieldLabel"  >
 				<jsp:include page="SelectedEmpSearchEdit.jsp">
 					<jsp:param name="searchTrainingTitle" value="${trngTitle}"/>
 					<jsp:param name="mandatory" value="Yes" />
				</jsp:include>
				</td>
			</tr>
		</table>
	<br><br>
		<center><hdiits:formSubmitButton type="button" name="submitQuery"  captionid="TR.button" bundle="${selectTrngScheduleToAddEmp}"/></center>
</div>
</div>
<script type="text/javascript">
		initializetabcontent("maintab")
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>