 <%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.ess.foreigntourtravel.foreigntourtravel" var="commonLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	

<c:set var="fTourlist" value="${resValue.fTourlist}" />
<c:set var="userId" value="${resValue.userId}" />

<script>
function getSelectedTourDtl()
{
	var fileId = document.getElementById("tourName").value;
	var code='FT';
	document.getElementById('Close').disabled=true;
	document.forms[0].action="./hrms.htm?actionFlag=displayFttReq&fileId="+fileId+"&code="+code;
	showProgressbar('Submitting Request...<br>Please wait...');
	document.forms[0].submit();
}

function goToHomePage()
{
	document.forms[0].action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";
	document.forms[0].submit();
}

</script>

<hdiits:form name="foreignTourTravel" validate="true" method="post">

<br>

<hdiits:fieldGroup bundle="${commonLables}"  id="SelectTour" titleCaptionId="HRMS.tourdtl" expandable="false">
<table class="tabtable" name="travelRequest" id="travelRequest">


<tr>
	<hdiits:hidden name="userId" id="userId" default="${userId}"/>
	<hdiits:hidden name="retId" id="retId" default="${retId}"/>
 	<td  colspan="1" width="50%" align="right">
		<b><hdiits:caption  bundle="${commonLables}"  captionid="HRMS.tourname"/></b>&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
	<td colspan="1" width="50%"> 
	 		<hdiits:select name="tourName" size="1" id="tourName" caption="tourname" mandatory="true" onchange="getSelectedTourDtl();">
	  			<hdiits:option value="0"><fmt:message bundle="${commonLables}"  key="HRMS.select" /></hdiits:option>
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
<td width="100%" colspan="2" align="center">
  		<hdiits:button  type="button" id="Close" name="Close" captionid="HRMS.close" bundle="${commonLables}" onclick="goToHomePage();"/>
</td>
</tr>
</table>
</hdiits:fieldGroup>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

  <%
}catch(Exception e){
	e.printStackTrace();
}

%>