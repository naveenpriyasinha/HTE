<%try{ %>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="loginUser"	value="${result.resultValue.baseLoginMap}"></c:set>

<fmt:setBundle basename="resources.acl.UserStatisticsReport" var="userStatisticsReport" scope="request"/>

<script type="text/javascript" src='<c:url value="script/common/tabcontent.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/calendar.js"/>'></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
 
<script type="text/javascript">
function generateReport()
{
	showProgressbar();
	  var PropvalueArray =  new Array('Datefrom','Dateto');
	  
	  if(!validateSpecificFormFields(PropvalueArray))
	  {
	  		return false;
	  }
	  else
	  {
	  		if(document.forms[0].reportCode.value == '6001')
	  		{
				document.forms[0].cleanupFlag.value = 'true';
			}
			document.userStatisticsReport.submit();
	  } 
}
</script>

<caption><font color="blue"></font></caption>

<hdiits:form name="userStatisticsReport" method="POST" validate="true" action="/hdiits/hdiits.htm" >
<div id="tabmenu">
<ul id="maintab" class="shadetabs"> 
   <li class="selected"><a href="#" rel="tcontent1"> <fmt:message bundle="${userStatisticsReport}" key="USRPT.UserStatisticsReport"></fmt:message></a></li>
</ul>

</div>
<div class="tabcontentstyle">

<div id="tcontent1" class="tabcontent" tabno="0">
<TABLE class=tabtable >
<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="USRPT.FROMDATE" bundle="${userStatisticsReport}"/><hdiits:caption captionid="USRPT.DateFormat" bundle="${userStatisticsReport}" captionfor="reportDate"/></td>
		<td class="fieldLabel" width="25%"><hdiits:dateTime name="Datefrom" showCurrentDate="true" captionid="USRPT.FROMDATE" bundle="${userStatisticsReport}" ></hdiits:dateTime></td>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="USRPT.TODATE" bundle="${userStatisticsReport}"/><hdiits:caption captionid="USRPT.DateFormat" bundle="${userStatisticsReport}" captionfor="reportDate"/></td>
		<td class="fieldLabel" width="25%"><hdiits:dateTime name="Dateto" showCurrentDate="true" captionid="USRPT.TODATE" bundle="${userStatisticsReport}" ></hdiits:dateTime></td> 
</tr>
</TABLE>
<TABLE class=tabtable >
<tr><td>
<fmt:message bundle="${userStatisticsReport}" key="USRPT.LOCATION" var="location"></fmt:message>
<jsp:include page="/WEB-INF/jsp/common/SearchLocation.jsp">
						<jsp:param name="SearchLocation" value="LocationId"/>
						<jsp:param name="searchLocationTitle" value="${location}"/>
						<jsp:param name="searchOn" value="Police Station,SDPO,SP,CP,HQ" />
						<jsp:param name="mandatory" value="No"/>
						<jsp:param name="hideFields" value="Addr,City-Dist,State-Pin" />
</jsp:include></td></tr>

<script type="text/javascript">editLocation('${loginUser.locationVO.locId}','LocationId' );</script>

</TABLE>
<TABLE class=tabtable >
<tr>
<td class="fieldLabel" width="25%" align="center"><hdiits:button name="reportGen" captionid="USRPT.BTN" type="button" bundle="${userStatisticsReport}"    onclick="generateReport()"/>
</td></tr>
</TABLE>
</div>

<hdiits:hidden name="reportCode" default='<%=request.getParameter("reportCode") %>' />
<hdiits:hidden name="action" default="generateReport"/>
<hdiits:hidden name="actionFlag" default="reportService"/>
<hdiits:hidden name="cleanupFlag" default="false"/>

</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>

<hdiits:validate  locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>

	<%}catch(Exception e)
	{
	e.printStackTrace();
	}%>
