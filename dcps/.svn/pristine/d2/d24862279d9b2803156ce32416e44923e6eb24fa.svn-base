<%
try
{
%>
<%
String backAction="";
String submitAction="";
backAction = "./hrms.htm?" + request.getParameter("backAction");
submitAction = "./hrms.htm?" + request.getParameter("searchAction");
//System.out.println("submitAction is " + submitAction);
%>
<script>
function searchData()
{
  OrderHeadPostView.action = '<%=submitAction%>';
  OrderHeadPostView.submit();
}

</script>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp" %>
<%@ page import="java.util.*"%>
<%@page language="java" import="com.tcs.sgv.core.valueobject.ResultObject;" %>


<hdiits:fieldGroup titleCaption="Search Post" mandatory="true"> 
<hdiits:table align="left" >
<tr>
<td>
<hdiits:caption caption="Search Post" captionid="srch_post"/></td>
<td><hdiits:text name="post_srchText_" captionid="PostSrch"/>
</td>
<td width="25%" align="left" colspan="2">
   	<hdiits:button  type="button" captionid="Search_Employee"   value="&nbsp;&nbsp;Search&nbsp;&nbsp;" name="search" onclick="searchData()"/>
</td>
</tr>
<tr>
<td> 
<hdiits:caption caption="Search Order" captionid="srch_order"/></td>
<td><hdiits:text name="order_srchText_" captionid="orderSrch"/>
</td>   
<td>  <a href=  <%=backAction%>>  Back </a>   </td>
 </tr> 
 </hdiits:table>
</hdiits:fieldGroup>


<%
}catch(Exception e)
  {
  e.printStackTrace();
  }
  %>
  