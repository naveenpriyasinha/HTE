 <%
try {
%>
<%@ include file="../../../core/include.jsp"%>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	<script type="text/javascript" src="script/leave/DateDifference.js"></script> 
	<script type="text/javascript" src="<c:url value="/script/leave/leavevalidation.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/script/leave/DateVal.js"/>"></script>
	<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
	<script type="text/javascript" src="script/leave/DateVal.js"></script>
	<script type="text/javascript" src="script/common/calendar.js"></script>
	<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
	<script type="text/javascript" src="common/script/tabcontent.js"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.foreigntourtravel.foreigntourtravel" var="commonLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<c:set var="fTourlist" value="${resValue.fTourlist}" />
<c:set var="userId" value="${resValue.userId}" />

<script>
function getSelectedTourDtl()
{
	var fileId = document.getElementById("tourName").value;
	var code='FT';
	document.forms[0].action="./hrms.htm?actionFlag=displayFttReq&fileId="+fileId+"&code="+code;
	document.forms[0].submit();
}

</script>

<hdiits:form name="foreignTourTravel" validate="true" method="post">

<br>

<table class="tabtable" name="travelRequest" id="travelRequest">


	<tr bgcolor="#386CB7">
	
	
	
		<td class="fieldLabel" colspan="5" align="center" width="25%">
			<font color="#ffffff">
			<strong><u><fmt:message key="HRMS.tourdtl"/></u></strong>
			</font>
		</td>
	</tr>

<tr>
	<hdiits:hidden name="userId" id="userId" default="${userId}"/>
	<hdiits:hidden name="retId" id="retId" default="${retId}"/>
 	<td width="25%" colspan="1" align="center">
		<b><fmt:message key="HRMS.tourname"/></b>&nbsp;&nbsp;:&nbsp;&nbsp;
	 
	 		<hdiits:select name="tourName" size="1" id="tourName" caption="tourname" mandatory="true" onchange="getSelectedTourDtl();">
	  			<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
	  			<c:forEach var="fTourlist" items="${fTourlist}" >
	  			<fmt:formatDate value="${fTourlist.dateOfDep}"
					pattern="dd/MM/yyyy" var="fromDate" />
							<hdiits:option value="${fTourlist.tourId}">
								${fTourlist.tourName}@${fromDate}
							</hdiits:option>
						</c:forEach>
			</hdiits:select>
	</td>
</tr>

<tr>
<hdiits:button style="display:none" id="save" type="button" name="save" captionid="HRMS.save" bundle="${commonLables}"/>
  		<hdiits:button style="display:none" type="button" id="Close" name="Close" captionid="HRMS.close" bundle="${commonLables}"/>
</tr>
</table>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>"'/>
</hdiits:form>

  <%
}catch(Exception e){
	e.printStackTrace();
}

%>