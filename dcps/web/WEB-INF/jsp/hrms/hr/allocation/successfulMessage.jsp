
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="common/script/tabcontent.js"></script>

<fmt:setBundle basename="resources.posting.postingLabels" var="empEditListCommonLables" scope="request" />


<script type="text/javascript">

	function closeWindow()
	{
//	window.close;
	var urlstyle="hdiits.htm?actionFlag=getHomePage"
	document.forms[0].action=urlstyle;
	document.forms[0].submit();
	}
	
	function backWindow()
	{
		if(backFlag=="recruitment")
		{
			document.frmEmpBasicInfo.action="hrms.htm?actionFlag=showEmpBasicInfo&userId="+seluserid+"&workFlowEnabled=false";
			document.frmEmpBasicInfo.submit();
		}
		else if(backFlag=="allocation")
		{
			document.frmEmpBasicInfo.action="hrms.htm?actionFlag=showEmpJoiningDetails&userId="+seluserid+"&workFlowEnabled=false";
			document.frmEmpBasicInfo.submit();
		}
		else if(backFlag=="posting")
		{
			document.frmEmpBasicInfo.action="hrms.htm?actionFlag=getEmpPrePstngDtls&userId="+seluserid+"&workFlowEnabled=false";
			document.frmEmpBasicInfo.submit();
		}
		else
		{
			window.close();
		}
	}

</script>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="userName" value="${resValue.userName}"/>
<c:set var="selUserId" value="${resValue.selUserId}"/>
<c:set var="backFlag" value="${resValue.backFlag}"/>

<hdiits:form name="frmEmpBasicInfo" validate="true" method="POST" action="">

	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="SUCC_MSG" bundle="${empEditListCommonLables}"/></b></a></li>
	</ul>
	</div>



	<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent" tabno="0">
		<br><br><br><br><br>
			<center>
			<hr width="25%">
				<c:if test="${not empty userName}">
					<b><fmt:message key="CrtUserSuccessMsg1" bundle="${empEditListCommonLables}"/> <font color="blue"><c:out value="${userName}"></c:out></font> <fmt:message key="CrtUserSuccessMsg2" bundle="${empEditListCommonLables}"/></b>
				</c:if>
				<c:if test="${empty userName}">
					<b><fmt:message key="SuccessfullMsg" bundle="${empEditListCommonLables}"/></b>
				</c:if>
			<hr width="25%">
			</center>
	

	<table align="center">	
		<tr>
			<c:if test="${not empty backFlag}">
			<td align="center">
				<br></br><hdiits:button name="Back" type="button" captionid="BACK" bundle="${empEditListCommonLables}" onclick="backWindow()"></hdiits:button>
			</td>
			</c:if>
			<td align="center">
				<br></br><hdiits:button name="Close" type="button" captionid="CLOSE" bundle="${empEditListCommonLables}" onclick="closeWindow()"></hdiits:button>
			</td>
		</tr>
	</table>

	</div>
<script type="text/javascript">
	var seluserid = '${selUserId}';
	var backFlag = '${backFlag}';
	var userName = '${userName}';
	initializetabcontent("maintab");
</script>

</div>
</hdiits:form>	

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
