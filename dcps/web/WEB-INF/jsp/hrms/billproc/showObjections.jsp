<%@ page language="java" contentType="text/plain" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject, java.util.Map, java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>' />
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<%
System.out.println("in jsp ");
	ResultObject rs = (ResultObject)request.getAttribute("result");
	Map map = (Map)rs.getResultValue();
	Map billMap = (Map)map.get("objMap");
	System.out.println("***************  " + billMap);
	if(billMap != null)
	{
		Set set = billMap.keySet();
		Iterator  it =  set.iterator();
		System.out.println("***************----------  " + it.toString());
		while(it.hasNext())
		{
			String billControlNum = (String) it.next();
			
			Map userMap = (Map)billMap.get(billControlNum);
			System.out.println("---billControlNum----- " +billControlNum);
			
		%>
			<table>
			<tr>
				<td>
				-------------------------------------------------------------------------------			
				</td>
			</tr>
			<tr>
				<td>
					<fmt:message key="CHQCOMMON.BILLCONTROLNO" bundle="${billprocLabels}"></fmt:message>:<%=billControlNum%>
				</td>
			</tr>
			<tr>
				<td>
				-------------------------------------------------------------------------------			
				</td>
			</tr>
					
		<%		
			Set set1 = userMap.keySet();
			Iterator  it1 =  set1.iterator();
			while(it1.hasNext())
			{
				String userName = (String)it1.next();
				List objList = (List)userMap.get(userName);
				System.out.println("-----userName--- " +userName);
		%>	
				<tr>
					<td>
					-------------------------------------------------------------------------------			
					</td>
				</tr>
		 		<tr>
			 		<td>		
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=userName%>	
					</td>
				</tr>
				<tr>
					<td>
					-------------------------------------------------------------------------------			
					</td>
				</tr>
				<tr>
				<td>
					<table>
					<tr>
						<td width="15%"><fmt:message key="ADT.OBJCODE" bundle="${billprocLabels}"></fmt:message></td>
						<td width="55%"><fmt:message key="ADT.OBJDESC" bundle="${billprocLabels}"></fmt:message></td>
						<td width="30%"><fmt:message key="CMN.DATE" bundle="${billprocLabels}"></fmt:message></td>
					</tr>
					<%
							Iterator itr = objList.iterator();
							while(itr.hasNext())
							{
								Object[] tuple = (Object[]) itr.next();
							%>
							<tr>
								<td>
								<%= (String)tuple[0]%>  
								</td>
								<td>
								<%= (String)tuple[1]%> 
								</td>
								<td>
								<%= (String)tuple[2]%>
								</td>
							</tr>
							<%
							
							}
					%>
					</table>
				</td>
				</tr>	
		<%
			}
		%>
			</table>
		<%
		char c = 12;
		out.print(c);
		}
	}
%>
<script>
	window.print();
</script>