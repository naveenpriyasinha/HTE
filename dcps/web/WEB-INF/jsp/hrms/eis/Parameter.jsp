
<%
try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
<script type="text/javascript" src="script/hod/ps/common.js"></script>

	<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/Parameter.js"/>"></script>

 <c:set var="resultObj" value="${result}" > </c:set>
 <c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="monthList" value="${resultValue.monthList}" > </c:set>
 <c:set var="yearList" value="${resultValue.yearList}" > </c:set> 
 <c:set var="billNoList" value="${resultValue.billNoList}" > </c:set> 

 
 
<fmt:setBundle basename="resources.eis.eis_common_lables"	var="adminTokenNumberLabel" scope="request" />
<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>

<hdiits:fieldGroup titleCaption="Tree View Reports">
<hdiits:form name="TokenNumber" validate="true" method="POST" action="javascript:TokenNumber()">


	<br><br>
<table width="85%"  bgcolor="white" align="center" id="parameterTable" >
	<tr>
	<td  align="center"> Year</td>
	<td align="left">
    <hdiits:select name="Year" size="1" sort="false" caption="Year" id="Year" onchange="GetFromMonths()" > 
	<hdiits:option value="-1"> --Select-- </hdiits:option>
	
	<c:forEach items="${yearList}" var="yearList">
	<hdiits:option value="${yearList.lookupShortName}"> ${yearList.lookupDesc} </hdiits:option>
	</c:forEach>
   	</hdiits:select>
    </td>
		<td align="center"> Month </td>
	<td align="left">	
	<hdiits:select name=" Month" size="1" sort="false" caption="Type" id="Month"  > 
    <hdiits:option value="-1"> --Select-- </hdiits:option>
	<c:forEach items="${monthList}" var="month">
	<hdiits:option value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
	</c:forEach>	
	 </hdiits:select>
	 
	 </td>

	</tr>
	<tr>
	    <td align = "center">BillNo</td>
	    <td align = "left">
	       	<hdiits:select name="BillNo" size="1" sort="false"  id="BillNo" >
	<hdiits:option value="-1"> --Select-- </hdiits:option>
	<c:forEach items="${billNoList}" var="billNoList">
	<hdiits:option value="${billNoList.billHeadId}"> ${billNoList.billId}</hdiits:option>
 	</c:forEach>
	
	</hdiits:select>
        </td>
     </tr>
</table>
	
<hdiits:validate locale="${locale}" controlNames="" />
<script type="text/javascript">
	initializetabcontent("maintab")
</script>
</hdiits:form>
</hdiits:fieldGroup>
<%
}catch(Exception e) {e.printStackTrace();}
finally{
}
%>
		