<%
 try {
 %>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>">
</script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>">
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js">
</script>
<script type="text/javascript" src="script/common/person.js">
</script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>">
</script> 


<fmt:setBundle basename="resources.gnl.CourtCases.CCLables" var="ccLables" scope="request" />
<c:set var="name" value='<%=request.getParameter("prsnType")%>' >
</c:set>
<c:set var="dispFlag" value='<%=request.getParameter("dispFlag")%>' >
</c:set>
<c:set var="title" value='<%=request.getParameter("title")%>' >
</c:set>
<c:set var="addressName" value='<%=request.getParameter("addressName")%>' >
</c:set>
<hdiits:fieldGroup bundle="${ccLables}" expandable="true" id="prsnDtlFldGrp${name}" titleCaptionId="${title}">
<table align="left" width="100%">
  <tr id="typeTD${name}" style="display:${dispFlag}">
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.prsnType"  bundle="${ccLables}" /></b>
    </td>
    <td>
    	<hdiits:radio name="prsnType${name}" value="Person" captionid="HRMS.CRT.person" bundle="${ccLables}" onclick="selectComponent('Person','${name}');"/>
    	<hdiits:radio name="prsnType${name}" value="Employee" captionid="HRMS.CRT.employee" bundle="${ccLables}" onclick="selectComponent('Employee','${name}');"/>	
     </td>
  </tr>
  <tr id="name${name}">
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.prsnName"  bundle="${ccLables}" /></b>
    </td>
    <td >
    	<div id="textTD${name}">	
      <hdiits:text caption="Person Name" name="prsnName${name}" mandatory="true" maxlength="40"/>
      </div>
    
    <div id="searchTD${name}">
      <hdiits:search name="empName${name}" id="empName${name}" url="./hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false" 
				    style="" mandatory="true" readonly="true"/>
	</div>
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.FName"  bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:text name="fatherName${name}" caption="FName" maxlength="40" />
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.age" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:number name="age${name}"  caption="Age" maxlength="3" />
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.desig" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:text name="desig${name}" caption="Designation" maxlength="40" />
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.mob" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:number name="mob${name}" caption="MobileNumber" maxlength="15" />
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.eMail" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:text name="eMail${name}" caption="EMail" maxlength="50" />
    </td>
  </tr>
  <tr>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.offNo" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:number name="offNo${name}" caption="OfficeNumber" maxlength="15" />
    </td>
    <td>
      <b><hdiits:caption captionid="HRMS.CRT.fax" bundle="${ccLables}"/></b>
    </td>
    <td>
      <hdiits:number name="fax${name}" caption="FaxNumber" maxlength="15" />
    </td>
  </tr>
  <tr >
  <td colspan="4">
	<jsp:include page="//WEB-INF/jsp/common/address.jsp">
	<jsp:param name="addrName" value="Address${name}" />
    <jsp:param name="addressTitle" value="${addressName}" />
    <jsp:param name="addrLookupName" value="Permanent Address" />
    <jsp:param name="mandatory" value="Y" />
    </jsp:include>
	 

</td>
</tr>
<tr>
<td>
<input type="hidden" name="personId" value="" />
<input type="hidden" name="personFlag" value="0" />
</td>
</tr>
</table>
</hdiits:fieldGroup>
<script>
document.forms[0].prsnType${name}[0].click();
//alert(document.getElementById('name${name}').innerHTML);

</script>
<%
 }
 catch(Exception e)
 {
 e.printStackTrace();
 }
 %>