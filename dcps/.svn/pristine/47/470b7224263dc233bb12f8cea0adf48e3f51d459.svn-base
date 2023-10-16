<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@page import="com.tcs.sgv.common.utils.DateUtility"%>

<fmt:setBundle basename="resources.trng.TrngAttendedLables" var="trngAttendedLables" scope="request" /> 

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="trainingMstList" value="${resValue.trainingMstList}" > </c:set>

<hdiits:form name="frmAttended" validate="true" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="externalTrngAttended"/>
 
<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.TrngAttended" bundle="${trngAttendedLables}"></hdiits:caption> </a></li>
		</ul>
</div>

<div id="tcontent1" class="tabcontent" tabno="0">
<TABLE class="tabtable">
<fmt:message key="TR.Search_Emp" bundle="${trngAttendedLables}" var="title"></fmt:message>
<tr>  
	<td class="fieldLabel" colspan="6">
	<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
		<jsp:param name="SearchEmployee" value="searchEmpInTrngAttendedExternal"/> 
		<jsp:param name="searchEmployeeTitle" value="${title}"/> 
		<jsp:param name="mandatory" value="Yes"/> 					
	</jsp:include>
	</td>
</tr>
<tr>
	<td class="fieldLabel" colspan="6" align="center"><hdiits:formSubmitButton name="formApproveButton" type="button" captionid="TR.GetDetails" bundle="${trngAttendedLables}"/></td>
</tr>
</TABLE>

<br>
		

</div>


<script	type="text/javascript">
		initializetabcontent("maintab")
</script> 
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
 