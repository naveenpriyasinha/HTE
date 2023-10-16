<%try{%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.List"%><c:set var="resultObj"	value="${result}"/>
<c:set var="resultMap" value="${resultObj.resultValue}"/>
<fmt:setBundle basename="resources.ess.Lables" var="lables" scope="request"/>
<fmt:message var="birthDayMsg" key="GREETING_BIRTHDAY" bundle="${lables}"></fmt:message>
<%
	Map resMap = (Map) pageContext.getAttribute("resultMap");
	if(resMap != null && resMap.size() > 0)
	{
		Map baseLoginMap = (Map) resMap.get("baseLoginMap");
		//List holidayGreetingMsg = (List) resMap.get("holidayGreetingMsg");
		List holidayGreetingMsg = (List) baseLoginMap.get("holidayGreetingMsg");
		if(holidayGreetingMsg != null && !holidayGreetingMsg.isEmpty())
		{
			%>
				<table style="background: #efefef; border: 0px outset #d8ddf9; text-align: left; width: 100%;" cellpadding="2" cellspacing="0">
					<tr>
						<td style="background: #8388b4; color: white; text-align: center; border: 1px outset #d8ddf9; ">
					<%
						String hldymsg = "";
					    int listSize = holidayGreetingMsg.size();
						for(int x=0;x<listSize;x++)
						{
							hldymsg = (String) holidayGreetingMsg.get(x);
							if( !hldymsg.trim().equals("") ){

								out.print("<B>");
								out.print(hldymsg);
								out.print("</B>");
								if(x != listSize-1 ) out.print("<BR>"); 
							}
						}
					%>
					</td>
					</tr>
					
					</table>
			<%
		}
		
	}
}
catch(Exception e)
{
	System.out.println("Error in show Greeting message : " +e);
}
%>