<%
try
{
%>
<%
String backAction="";
String submitAction="";
backAction = "./hrms.htm?" + request.getParameter("backAction");
submitAction = "./hrms.htm?" + request.getParameter("searchAction");
submitAction+="&orderSearchFlag=y";
//System.out.println("submitAction is " + submitAction);
%>
<script>
function searchData()
{
document.forms[0].action = '<%=submitAction%>';
//  orderMstView.action = 
  //orderMstView.submit();
document.forms[0].submit();  
}

</script>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ page import="java.util.*"%>
<%@page language="java" import="com.tcs.sgv.core.valueobject.ResultObject;" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<hdiits:fieldGroup titleCaption="Search Order" mandatory="true"> 
<hdiits:table align="left" >
<tr>
<td> <b>
<hdiits:caption caption="Search Order" captionid="srch_order"/></b></td>
<td><hdiits:text name="orderName" captionid="orderSrch"/>
</td> <td></td>
<td >
   	<hdiits:button  type="button" captionid="Search_Employee"   value="&nbsp;&nbsp;Search&nbsp;&nbsp;" name="search" onclick="searchData()"/>
</td>
</tr>
<tr>
<td><fmt:message key="ST.DATE" bundle="${commonLables}"/></td>
<td><hdiits:dateTime caption="ST.DATE"  name="startDate" validation="txt.isdt" bundle="${commonLables}" minvalue=""/> </td>
<%--<td>
<hdiits:caption caption="Start Date" captionid="srchStDate"/></td>
<td><hdiits:dateTime name="startDate" captionid="orderStDate"/>
</td>   

<td>
<hdiits:caption caption="End Date" captionid="srchOrderEndDate"/></td>
<td><hdiits:dateTime name="endDate" captionid="orderEndDate" />
</td>   --%>
<td><fmt:message key="OM.endDate" bundle="${commonLables}"/></td>
		<td><hdiits:dateTime caption="OM.endDate"  name="endDate" validation="txt.isdt" bundle="${commonLables}" minvalue=""/> </td>
 </tr> 
 </hdiits:table>
</hdiits:fieldGroup>
<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />

<%
}catch(Exception e)
  {
  e.printStackTrace();
  }
  %>
  