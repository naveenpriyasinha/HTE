<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp" %>

	<c:set var="resultObj"	value="${result}"/>
	<c:set var="resultMap" value="${resultObj.resultValue}"/>
		 		
<fmt:setBundle basename="resources.trng.TrngAttendedLables" var="trngAttendedLables" scope="request" /> 
<fmt:setBundle basename="resources.trng.trschLables" var="trschLables"	scope="request" />
<script type="text/javascript" src="<c:url value="script/hrms/training/externalTrngAttendedModified.js"/>"></script>
<fmt:message key="TR.MSG1" bundle="${trngAttendedLables}" var="msg"/>
<hdiits:form name="frmexternalTrngAttendedModified" validate="true" method="post" action="./hdiits.htm?" encType="multipart/form-data">
<hdiits:hidden name="actionFlag" default="externalTrngAttendedModified" /> 
<hdiits:hidden name="schId"/> 

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li  class="selected">
		<a href="#"  rel="tcontent1">
			<hdiits:caption	captionid="TR.TrngAttended" bundle="${trngAttendedLables}" /> 
		</a>
	</li>
	</ul>
</div>
	
	<div id="tcontent1" class="tabcontent" tabno="0" >   
	<fmt:message key="TR.SEARCH_TRAINING_SCHEDULE"  bundle="${trschLables}" var="scheduleTitle"></fmt:message>
	<table class="tabtable"> 
		<tr>  
			<td class="fieldLabel" colspan="6">
			<jsp:include page="./trngSchSearchForTrngAttended.jsp">
				<jsp:param name="searchScheduleTitle" value="${scheduleTitle}"/>
				<jsp:param name="mandatory" value="Yes" />
			</jsp:include>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" colspan="6" align="center">
				<center><hdiits:button name="RedirectQuery" type="button" onclick="redirectForm('${msg}')" value="SubmitButtonQuery" captionid="TR.GetDetails" bundle="${trngAttendedLables}"/></center>   
				
			</td>
		</tr>
	</table>
	</div>    

	<script type="text/javascript">
		initializetabcontent("maintab")		 
	</script>    
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
 	
         