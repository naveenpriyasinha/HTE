
<%
try {
%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="resources.eis.empInfoLables" var="lables" scope="request" />

<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />
<c:set var="EmpDetVO" value="${resultValue.EmpDet}" />

<c:set var="address" value="${resultValue.address}" />





<c:out value="${empImage}"></c:out>

<hdiits:fieldGroup titleCaptionId="EMP.EMPDT" bundle="${lables}" id="emp_info_labels_id">

<!-- <table bgcolor="#386CB7" align="center" width="100%">
	<tr bgcolor="#386CB7">
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
			<strong><u><fmt:message bundle="${lables}" key="EMP.EMPDT"/></u></strong></font></td>
	</tr>	
</table> -->

<table  class="tabtable" width="100%">
  
  <tr>
    <td width="25%" class="captionTag"><hdiits:caption bundle="${lables}" captionid="EMP.NAME"></hdiits:caption></td>
    
    <td width="25%"><c:out value="${EmpDetVO.empName}"> </c:out></td>
    
    <td width="25%" class="captionTag"><hdiits:caption bundle="${lables}" captionid="EMP.ADDRESS"></hdiits:caption></td>
    
    <td width="25%"><c:out value="${address}"></c:out></td>
    
  </tr>
  <tr>
    <td width="25%" class="captionTag"><hdiits:caption bundle="${lables}" captionid="EMP.DOB"></hdiits:caption></td>
    <td width="25%"><fmt:formatDate value="${EmpDetVO.dob}"
			pattern="dd/MM/yyyy" /></td>
    <td width="25%" class="captionTag"><hdiits:caption bundle="${lables}" captionid="EMP.DOJ"></hdiits:caption></td>
    <td width="25%"><fmt:formatDate value="${EmpDetVO.doj}"
			pattern="dd/MM/yyyy" /></td>
  </tr>
  <tr>
    <td width="25%" class="captionTag"><hdiits:caption bundle="${lables}" captionid="EMP.DESIGNATION"></hdiits:caption></td>
    <td width="25%"><c:out value="${EmpDetVO.designation}"></c:out></td>
    <td width="25%" class="captionTag"><hdiits:caption bundle="${lables}" captionid="EMP.POSTNAME"></hdiits:caption></td>
    <td width="25%"><c:out value="${EmpDetVO.postName}"> </c:out></td>
  </tr>
  <tr>
    <td width="25%" class="captionTag"><hdiits:caption bundle="${lables}" captionid="EMP.PAYSCAL"></hdiits:caption></td>
    <td width="25%"><c:out value="${EmpDetVO.scaleStart}"></c:out>---<c:out	value="${EmpDetVO.scaleInc}"></c:out>---<c:out value="${EmpDetVO.scaleEnd}"></c:out></td>
    <td width="25%" class="captionTag"><hdiits:caption bundle="${lables}" captionid="EMP.SALARY"></hdiits:caption></td>
    <td width="25%"><c:out value="${EmpDetVO.salary}"></c:out></td>
  </tr>
   
</table>
</hdiits:fieldGroup>
<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>

