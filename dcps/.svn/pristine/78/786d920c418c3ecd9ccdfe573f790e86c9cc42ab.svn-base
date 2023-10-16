<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>


<%@page import="com.tcs.sgv.common.utils.DateUtility"%>
<fmt:setBundle basename="resources.trng.ResultLables" var="resLables" scope="request" />

<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"/></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	


<hdiits:form name="selectTrng" validate="true" method="POST" encType="multipart/form-data" action="./hdiits.htm?actionFlag=getResultScreen">

<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="TR.SELECTTRNG" bundle="${resLables}"></hdiits:caption> </a></li>
		</ul>
</div>

<div id="tcontent1" class="tabcontent" tabno="0">
<fmt:message key="TR.SEARCHSCH"  bundle="${resLables}" var="scheduleTitle"></fmt:message>
<TABLE class="tabtable">
		<tr>  
		<td class="fieldLabel" colspan="6">
		<jsp:include page="scheduleSearch.jsp">
		<jsp:param name="searchScheduleTitle" value="${scheduleTitle}"/>
		<jsp:param name="mandatory" value="Yes" />
		</jsp:include>
		</td>
		</tr>
		<tr height="10"></tr>
		
		<tr>
		<td class="fieldLabel" colspan="6" align="center"><hdiits:formSubmitButton name="formApproveButton" type="button" captionid="TR.SUBMIT" bundle="${resLables}"/></td>
		</tr>
		
</table>  


</div>

<script	type="text/javascript">
		initializetabcontent("maintab")
</script> 
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>