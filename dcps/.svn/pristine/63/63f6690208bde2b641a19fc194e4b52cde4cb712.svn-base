<% 
try
{
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" 	var="QtrLables" scope="request" />
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/eis/commonUtils.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="waitting" value="${resValue.waitting}"></c:set>
<c:set var="PoliceSt" value="${resValue.PoliceSt}"></c:set>
<c:set var="PoliceLine" value="${resValue.PoliceLine}"></c:set>
<c:set var="QtrType" value="${resValue.QtrType}"></c:set>


<hdiits:form name="form1" validate="" action="hrms.htm?actionFlag=insertQtrAlloReq" method="post">
<br>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="HRMS.TotalWaitting" bundle="${QtrLables}"/></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">

<c:if test="${flag == 1 }" >
		<hdiits:fieldGroup titleCaptionId= "HRMS.TotalWaitting" bundle="${QtrLables}" id="TotalWaiting">
	<!--  <TABLE width="100%">
	<TBODY>
  	<TR bgColor=#386cb7>
    	<TD align="center" class=fieldLabel colSpan=5><FONT color=#ffffff ><STRONG><U><fmt:message key="HRMS.TotalWaitting" bundle="${QtrLables}"/>
      </U></STRONG></FONT></TD></TR></TBODY>
	</TABLE>-->
	<br>
	<TABLE width="100%" border="0">
	<TR>
		<TD><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b></TD>
		<TD><c:out value="${PoliceSt}"> </c:out></TD>
		<TD><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></TD>
		<TD><c:out value="${PoliceLine}"> </c:out></TD>
	</TR>
	<TR>
		<TD><b><hdiits:caption captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" /></b></TD>
		<TD><c:out value="${QtrType}"> </c:out></TD>
		<TD></TD>
		<TD></TD>
	</TR>
	</TABLE>
	<br>
	<TABLE width="100%" border="1" style="border-collapse: collapse;" borderColor="BLACK">
	<TR>
		<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.SrNo" bundle="${QtrLables}" /></b></TD>
		<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.Name" bundle="${QtrLables}" /></b></TD>
		<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.Designation" bundle="${QtrLables}" /></b></TD>
		<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.DateApp" bundle="${QtrLables}" /></b></TD>
		<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.Remarks" bundle="${QtrLables}" /></b></TD>
	</TR>
    <c:forEach var="waitting" items="${waitting}">
		<TR>
			<TD><c:out value="${waitting.srNo}"/></TD>
			<TD><c:out value="${waitting.userName}"/></TD>
			<TD><c:out value="${waitting.designation}"/></TD>
			<TD><fmt:formatDate value="${waitting.dtOfApp}" pattern="dd/MM/yyyy"/></TD>
			<TD><c:out value="${waitting.rmk}"/></TD>
		</TR>
	</c:forEach>
</TABLE>
</hdiits:fieldGroup>
</c:if>

<c:if test="${flag == 0 }" >
	<TABLE width="100%" border="1">
		<TR align="center">
			<TD><b><fmt:message key="HRMS.NotFound" bundle="${QtrLables}" /></b></TD>
		</TR>
	</TABLE>
</c:if>	


<TABLE id="submit" width="100%" >
<TR>
	<TD width="50%" align="center">
		<hdiits:button name="Close" type="button" value="Close"  captionid="HRMS.Close" bundle="${QtrLables}" onclick="window.close();"/>
	</TD>
</TR>
</TABLE>
<script type="text/javascript">
	initializetabcontent("maintab");
</script>
</div></div> 
</hdiits:form>
<%
}
catch(Exception e){
	e.printStackTrace();
}
%>	