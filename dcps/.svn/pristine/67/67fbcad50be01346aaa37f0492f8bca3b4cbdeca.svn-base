<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>

 <%
	System.out.println("Inside Jsp Page1");
 	ResultObject rs = (ResultObject)request.getAttribute("result");
	Map map = (Map) rs.getResultValue();
	ArrayList arrIntim=(ArrayList)map.get("intimation");
	ArrayList arrBillContNo=(ArrayList)map.get("BillContNo");
	ArrayList lStrUserName=(ArrayList)map.get("lStrUserName");
	Iterator it=arrIntim.iterator();
	String desc ="hello";
	Map mapTemp = new HashMap();
	if(it.hasNext())
	{
		mapTemp=(HashMap)it.next();
		desc=(String)mapTemp.get("NTF_DESC");
		System.out.println("Description is :-"+mapTemp);
	}
	int i=0;
 %>
 <hdiits:form name="intimation" validate="true" method="post"  >
 <table border="0" cellspacing="0" cellpadding="0" align="center" width="100%">
	  	<tr class="TableHeaderBG"> 
			<td  align="center"><fmt:message key="INTIMATION.TITLE" bundle="${billprocLabels}"></fmt:message></td>
		</tr>
		<tr> 
			<td  align="center">&nbsp;</td>
		</tr>
 </table>

 <display:table list="<%=arrIntim%>" pagesize="12" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
				id="VO"  excludedParams="ajax" varTotals="totals" export="true" partialList="" style="width:100%">
				<display:setProperty name="paging.banner.placement" value="bottom"/>  
 	<display:column class="oddcentre" title="Bill Control No" sortable="true" headerClass="datatableheader" >
	 	<c:out value="<%=arrBillContNo.get(i)%>"/>
 	</display:column>
 	
 	<display:column class="oddcentre" title="From Employee"  sortable="true" headerClass="datatableheader" >
	 	<c:out value="<%=lStrUserName.get(i)%>"/>
 	</display:column>
 
 	<display:column class="oddcentre" title="Message" sortable="true" headerClass="datatableheader" >
 		<c:out value="${VO.NTF_CNTNT}"/>
 	</display:column>
 	<% i++; %>
 	<display:footer media="html">
				  </display:footer>
</display:table>
 </hdiits:form>	
	  