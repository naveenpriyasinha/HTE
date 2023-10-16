<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="corrNo" value="${resValue.corrNo}"></c:set>
<c:set var="flagFromWorkList" value="${resValue.flagFromWorkList}"></c:set>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="responcereqFlag" value="${param.responcereq}"></c:set>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="DisplayMsgCorrCreate" method="POST" action="./hdiits.htm"  validate="true" >
<hdiits:hidden name="fileId" id="hid_fileId" default="${resValue.fileId}"/>
	<br><br><br>
	<center>
	<c:choose>
	<c:when test="${flagFromWorkList eq 'workList' or 'corrSpecific'}">
		<h3>
			<c:if test="${langId eq '1'}">
				<fmt:message key="WF.CorrCrtMsg1"  bundle="${fmsLables}"/> ${corrNo}
			</c:if>
			<c:if test="${langId eq '2'}">
				<fmt:message key="WF.CorrCrtMsg1"  bundle="${fmsLables}"/> ${corrNo} <fmt:message key="WF.CorrCrtMsg2"  bundle="${fmsLables}"/>
			</c:if>
		</h3>
		<br>
		<c:if test="${resValue.whetherCorrForwarded eq 'Yes'}">
			<h3>
				<c:if test="${langId eq '1'}">
					<fmt:message key="WF.ForwardMsg"  bundle="${fmsLables}"/> <c:out value="${resValue.empName}"/>
				</c:if>
				<c:if test="${langId eq '2'}">
					<fmt:message key="WF.CorrFwdMsg1"  bundle="${fmsLables}"/> <c:out value="${resValue.empName}"/> <fmt:message key="WF.CorrFwdMsg2"  bundle="${fmsLables}"/>
				</c:if>
			</h3>
		</c:if>
		<br>
		<hdiits:button name="button_closewin" type="button" value="OK" onclick="closewin()"/>
	</c:when>
	<c:otherwise>
		<h3>
			<c:if test="${langId eq '1'}">
				<fmt:message key="WF.CorrCrtMsg1"  bundle="${fmsLables}"/> ${corrNo} <fmt:message key="WF.CorrAddMsg1"  bundle="${fmsLables}"/>
			</c:if>
			<c:if test="${langId eq '2'}">
				<fmt:message key="WF.CorrCrtMsg1"  bundle="${fmsLables}"/> ${corrNo} <fmt:message key="WF.CorrCrtMsg2"  bundle="${fmsLables}"/> <fmt:message key="WF.CorrAddMsg1"  bundle="${fmsLables}"/>
			</c:if>
		</h3>
		<br>
		<hdiits:button name="button" type="button" value="OK" onclick="showTappalSide()"/>
	</c:otherwise>
	</c:choose>
	</center>
</hdiits:form>

<script type="text/javascript">
	function showTappalSide()
	{
			var currenttabid = window.opener.document.getElementById('currenttabid').value;
			//alert(currenttabid);
			var fileId = document.getElementById('hid_fileId').value;
			if(currenttabid=='tab2'){
				window.opener.parent.frames["Target_frame"].document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=showaddedCorrespondence&corrCriteria=Incoming&fileId="+fileId;
				window.opener.parent.frames["Target_frame"].document.forms[0].method = "post";
				window.opener.parent.frames["Target_frame"].document.forms[0].submit();
			}
			window.close();
		
	}
	function closewin()
	{
		if('${responcereqFlag}'!='yes')
		{
			if("${flagFromWorkList}" == "corrSpecific")
				window.opener.parent.frames["dataFrame"].document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=getDocListOfWorkList&moduleName=WorkList&menuName=forCorrespondenceInbox";
			else
				window.opener.parent.frames["dataFrame"].document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=getDocListOfWorkList&moduleName=WorkList&menuName=forWorkList";
			
			window.opener.parent.frames["dataFrame"].document.forms[0].method = "post";
			window.opener.parent.frames["dataFrame"].document.forms[0].submit();
			}
			window.close();
		}
</script>