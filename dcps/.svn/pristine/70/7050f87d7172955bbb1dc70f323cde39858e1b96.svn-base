
<%
try {
%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@ include file="../../../core/include.jsp"%>
<%@page
	import="java.util.List,com.tcs.sgv.ess.valueobject.EmployeeSearchVO;"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<fmt:setBundle basename="resources.eis.empInfoLables" var="lables"
	scope="request" />
<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />
<c:set var="EmpDetVO" value="${resultValue.EmpDet}" />

<c:set var="address" value="${resultValue.address}" />





<c:out value="${empImage}"></c:out>
<table bgcolor="#386CB7" align="center" width="100%">

	<tr align="center">
		<td><font class="Label3" align="center"><u><font
			class="Label3" align="center" color="white"><b><fmt:message bundle="${lables}" key="EMP.EMPDT"></fmt:message></b></u><span class="UserText"
			lang="en-us"></span></font></td>


	</tr>
</table>

<table width="100%">
  
  <tr id="image">
    <td colspan="4" rowspan="5"  >
    <img id="myImage" name="myImage2" src="" border="1" width="80%" height="70%"></td>
  
  </tr>
  <tr>
    <td><b><fmt:message bundle="${lables}" key="EMP.NAME"></fmt:message></b></td>
    <td><c:out value="${EmpDetVO.empName}"> </c:out></td>
    <td><b><fmt:message bundle="${lables}" key="EMP.ADDRESS"></fmt:message> </b></td>
    <td><c:out value="${address}"></c:out></td>
  </tr>
  <tr>
    <td><b><fmt:message bundle="${lables}" key="EMP.DOB"></fmt:message> </b></td>
    <td><fmt:formatDate value="${EmpDetVO.dob}"
			pattern="dd/MM/yyyy" /></td>
    <td><b><fmt:message bundle="${lables}" key="EMP.DOJ"></fmt:message>  </b></td>
    <td><fmt:formatDate value="${EmpDetVO.doj}"
			pattern="dd/MM/yyyy" /></td>
  </tr>
  <tr>
    <td><b><fmt:message bundle="${lables}" key="EMP.DESIGNATION"></fmt:message></b></td>
    <td><c:out value="${EmpDetVO.designation}"></c:out></td>
    <td><b> <fmt:message bundle="${lables}" key="EMP.POSTNAME"></fmt:message></b></td>
    <td><c:out value="${EmpDetVO.postName}"> </c:out></td>
  </tr>
  <tr>
    <td><b> <fmt:message bundle="${lables}" key="EMP.SALARY"></fmt:message></b></td>
    <td><c:out value="${EmpDetVO.salary}"></c:out></td>
    <td><b><fmt:message bundle="${lables}" key="EMP.PAYSCAL"></fmt:message> </b></td>
    <td><c:out value="${EmpDetVO.scaleStart}"></c:out>---<c:out	value="${EmpDetVO.scaleInc}"></c:out>---<c:out value="${EmpDetVO.scaleEnd}"></c:out></td>
  </tr>
   
</table>

<script type="text/javascript">		
		if ('<%=request.getParameter("empImage")%>'!='Y')
{
 
  document.getElementById("myImage").style.display="none";

}else
{
  document.getElementById('myImage').src="hdiits.htm?actionFlag=viewAttachment&attachmentId="+${EmpDetVO.empPhotoId}+"&attachmentSerialNumber="+${EmpDetVO.photoSrNo};
}
		
	</script>




<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>

