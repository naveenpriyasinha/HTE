<STYLE type="text/css">		
#currentApplication{
	display:none;
}
</STYLE>
<%try{%>
<%@ include file="include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<fmt:setBundle basename="resources.Constants" var="constant" scope="request"/>
<fmt:message bundle="${constant}" key="PROJECT_NAME" var="projectURL"></fmt:message>
<script type="text/javascript" src="<c:url value="/script/login/frmUtils.js"/>"></script>
<script type="text/javascript" src='<c:url value="script/common/tagLibValidation.js"/>'></script>
<center>
<table border=0 cellpspacing=0 cellpadding=2 width="400" height="400">
		<tr>
		<td valign="middle" align="center"></td>
		<td height="100%">
<hdiits:form name="homePage" method="POST" validate="false" action="javascript:submitMessageForm();" >
<hdiits:fieldGroup>
		<table border=0 width="100%" cellpadding="5"><tr><td valign="middle" align="center">
		<img src='<c:url value="/themes/${themename}/images/infoalert-01.png"/>'>
		</td>
		<td id="tdMsg" valign="middle" align="center" colspan="2">
		<c:set var="resultObj" value="${result}"></c:set>
		<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
		<c:set var="msg" value="${resValue.msg}"></c:set>		
		<script type="text/javascript">
			function generateReport()
			{				
				document.forms[0].action = '<%=pageContext.getAttribute("projectURL")%>?${resValue.REPORTACTION}';
				document.forms[0].submit();
			}
		</script>
		<font style="font-size: 14px; font-weight: normal;">
		<c:out value="${resValue.msg}"/>
		</font>
		</td></tr>
		<tr><td valign="middle" align="right" colspan="2">
		<hdiits:submitbutton name="okbutton" value="OK" />		
		</td>
		<td valign="middle" align="right" id="reportbuttonTD">
		<hdiits:button name="reportbutton" value="${resValue.REPORTBUTTONCAPTION}" type="button" onclick="generateReport()"/></td>
		</tr></table>
		</hdiits:fieldGroup>
</hdiits:form>
		</td>
		</tr>
		<%
		ResultObject result=(ResultObject)request.getAttribute("result");
		Map resultMap=(Map)result.getResultValue();
		Map additionLinksMap=(Map)resultMap.get("additionLinks");
		String strMsgAction = pageContext.getAttribute("projectURL") + "?actionFlag=getHomePage";
		if(resultMap != null && resultMap.size()>0 && resultMap.containsKey("MESSAGEACTION"))
		{
			strMsgAction = (String) resultMap.get("MESSAGEACTION");
			if(!strMsgAction.equals("login.jsp"))
			{
				strMsgAction = pageContext.getAttribute("projectURL") + "?"+ strMsgAction;
			}
			String hideMenu = (String) resultMap.get("hideMenu");
			if(hideMenu != null && hideMenu.trim().equals("Y"))
			{
				%>
					<c:set var="show_menu" value="show_menu"> </c:set>
					<input type="hidden" name="show_menu" value="Y" />
 				<%
			}
		}
		%><input type="hidden" name="msgAction" value="<%=strMsgAction%>" /><%

		if(additionLinksMap!=null && additionLinksMap.size()>0)
		{
			Iterator iterator = additionLinksMap.keySet().iterator();
			String key = "";
			String value = "";
			while(iterator.hasNext())
			{
				key = (String)iterator.next();
				value = (String) additionLinksMap.get(key);
				%>
				<tr>
				<td colspan=2>
				<hdiits:a href="<%=value%>" caption="<%=key%>" ></hdiits:a>	
				</td>
				</tr>			
				<%
			}
		}
		%>		
</table>
</center>
<script type="text/javascript">
	var strMsg=document.getElementById("tdMsg").innerHTML;
	strMsg=strMsg.replace(/\\n/g,"<br>\n") ;
	document.getElementById("tdMsg").innerHTML=strMsg;
</script>
<c:if test="${resValue.REPORTACTION == null or resValue.REPORTACTION == ''}">
	<script type="text/javascript">
	document.getElementById("reportbutton").style.display = 'none';
	document.getElementById("reportbuttonTD").style.display = 'none';
	</script>
</c:if>
<%}catch(Exception e)
{
		e.printStackTrace();
}%>
