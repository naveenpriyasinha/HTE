<%try{ %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj"	value="${result}"> </c:set> 
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="receiver" value="${resultMap.receiver}"></c:set>

<fmt:setBundle basename="resources.workflow.workFlowLables" var="commonLables" scope="request"/>

<script type="text/javascript">

	function refresh_me()
	{
		window.opener.window.opener.document.forms[0].action = "hdiits.htm?actionFlag=getDocListOfWorkList&moduleName=WorkList&menuName=forWorkList";
		window.opener.window.opener.document.forms[0].submit();
		window.opener.close();
		window.close();
	}

</script>						

<table align="center">
	<tr>
		<td>
			<b><font color="green" face="verdana">Parallel File Successfully send to ${receiver}.</font></b>
		</td>
	</tr>
	<tr>
		<td>
			<hdiits:button name="okBtn" type="button" value="OK" onclick="refresh_me()" />
		</td>
	</tr>
</table>
	
<% }
catch(Exception e)
{e.printStackTrace();}
%>