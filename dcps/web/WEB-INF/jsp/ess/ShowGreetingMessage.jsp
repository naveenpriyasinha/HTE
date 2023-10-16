<%try{%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<c:set var="resultObj"	value="${result}"/>
<c:set var="resultMap" value="${resultObj.resultValue}"/>
<fmt:setBundle basename="resources.ess.Lables" var="lables" scope="request"/>
<fmt:message var="birthDayMsg" key="GREETING_BIRTHDAY" bundle="${lables}"></fmt:message>
<%
	Map resMap = (Map) pageContext.getAttribute("resultMap");
	if(resMap != null && resMap.size() > 0)
	{
		ArrayList listBirthDatePersons = (ArrayList) resMap.get("birthdatePersons");
		if(listBirthDatePersons != null && listBirthDatePersons.size()>0 )
		{
			int totalPersons = listBirthDatePersons.size();
			String themename = (String) session.getAttribute("themename");
			String imagePath = "themes/" + themename + "/images/bullet.gif";
			StringBuffer sbdiv = new StringBuffer("");
			%>
			<div style="width: 100%; border: 1px solid #CFCFCF; background: #efefef; ">
					    <div style=" text-align: center; background: #a5aad6; color: white; padding-bottom: 3px; padding-top: 3px;" >
					    <B> ${birthDayMsg} </B> 
					    </div>
					    
			<%
			sbdiv.append("<div style='background: #efefef; overflow: auto; text-align: left; padding-left: 3px; border-top: 0px solid white;");
			if(totalPersons > 3)
			{
				sbdiv.append(" height: 90px;  ");
			}
			sbdiv.append("'>");
			
			out.println(sbdiv.toString());
			
			for(int x=0;x<totalPersons;x++)
			{
				%>
					<img src="<%=imagePath%>" > &nbsp; <%=listBirthDatePersons.get(x)%> 
					<BR>
				<%
			}
			%>
			
			 </div>
			 </div> <%
		}
	}
}
catch(Exception e)
{
	System.out.println("Error in show Greeting message : " +e);
}
%>