
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="java.util.List"%>
<%
//System.out.println("\n\n-------------------------------------------in common.jsp------------------------------------------------------------\n\n");
%>

<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>


<c:set var="resultObj" value="${result}" ></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" ></c:set>
<c:set var="actionList" value="${resValue.actionList}"></c:set>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<hdiits:form action="hrms.htm?actionFlag=getcommondtls&elementId=300712" method="Post" name="common" validate="true" >
<table width="60%" border="1" align="center" height="50%" bordercolor="green">
<tr>
<td>
<c:forEach var="CustomCommon" items="${actionList}" varStatus="dExpcounter" >
<table border="0" align="center">
<tr>
<td width="25%" align="center"> 
   <label for="txtddoName"><b><fmt:message key="CM.DDOName" bundle="${commonLables}"/></b></label>
</td>
 <td width="25%" > 
   <hdiits:text id="txtddoName" name="txtddoName"  caption ="txtddoName" maxlength="150" readonly="true" default="${CustomCommon.ddoName}" />
</TD>
<td width="25%"> 
<br>
</td>
</tr>
<tr>
<td width="25%" align="center"> 
   <label for="txtName"><b><fmt:message key="CM.Name" bundle="${commonLables}"/></b></label>
 </td>
 <td width="25%"> 
   <hdiits:text id="txtName" name="txtName"  caption ="txtName" maxlength="150" readonly="true" default="${CustomCommon.empFname} ${CustomCommon.empMname} ${CustomCommon.empLname}"/>
</TD>
<td width="25%" align="center"> 
   <label for="txtcadreName"><b><fmt:message key="CM.Cadre" bundle="${commonLables}"/></b></label>
 </td>
 <td width="25%"> 
   <hdiits:text id="txtcadreName" name="txtcadreName"  caption ="txtcadreName" maxlength="150" readonly="true" default="${CustomCommon.cadre}"/>
   
</TD>
</tr>
<tr>
<td width="25%" align="center"> 
  <label for="txtdesName"><b><fmt:message key="CM.Designation" bundle="${commonLables}"/></b></label>
  </td>
 <td width="25%"> 
   <hdiits:text id="txtdesName" name="txtdesName"  caption ="txtdesName" maxlength="150" readonly="true" default="${CustomCommon.dsgnName}"/>
</TD>
<td width="25%" align="center"> 
   <label for="txtgrpName"><b><fmt:message key="CM.Group" bundle="${commonLables}"/></b></label>
 </td>
 <td width="25%"> 
   <hdiits:text id="txtgrpName" name="txtgrpName"  caption ="txtgrpName" maxlength="150" readonly="true" default="${CustomCommon.groupId}"/>
</TD>

</tr>

</TABLE>
</c:forEach>
</td>
</tr>
</table>
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>