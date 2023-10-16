<%
	try
	{
%>
		<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
		<%@ include file="../core/include.jsp"%>
		
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
		
		<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request"  />
		<script type="text/javascript">
		window.moveTo(250,250);
		window.resizeTo(500,300);
		</script>
		<c:set var="resultObj" value="${result}"></c:set>
		<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
		<c:set var="corrNumList" value="${resValue.corrNumList}"></c:set>
		<c:set var="fileNumber" value="${resValue.fileNumber}"></c:set>
		<c:set var="srNo" value="${1}" />
		
		<!--   <table  align="center" border="1" width="100%">
			<c:forEach var="corrNum" items="${corrNumList}" varStatus="varCnt">
				<c:if test="${varCnt.index eq 0}">
					<tr>
						<td colspan="3">
							<c:out value="List of Correspondence Added to File Number : ${fileNumber}"/>
						</td>
					</tr>
					<tr>
						<td>
						</td>
					</tr>
					<tr>
						<td align="left" width="30%">
							<c:out value="Sr.No."/>
						</td>
						<td align="left">
							<c:out value="Correspondence Number"/>
						</td>
					</tr>
				</c:if>
				<tr>
						<td align="left" width="30%">
							<c:out value="${varCnt.index+1}"/>
						</td>
						<td align="left">
							<c:out value="${corrNum.corrNo}"></c:out>
						</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="2" align="center">
					<hdiits:button name="ActionButton" type="button" value="Ok" captionid="WF.Ok" bundle="${fmsLables}" onclick="WorkflowAction()"/>
				</td>
			</tr>
		</table> -->
		<b>
		
		
		<fmt:message bundle="${fmsLables}" key="WF.ListCorr"></fmt:message>
		<c:out value="${fileNumber}"/></b>
		<display:table list="${corrNumList}" pagesize="12" requestURI="" id="corrNum"  defaultsort="1" style="width:100%">
		
				
		
				
				<display:column titleKey="WF.SRNO" headerClass="datatableheader" sortable="true">${srNo}</display:column>
				<display:column titleKey="WF.CrNo" headerClass="datatableheader" sortable="true">${corrNum.corrNo}</display:column>
				
				
			
				<c:set var="srNo" value="${srNo+1}" />
			</display:table>
			<br>
			<br>
			
			<center>		<hdiits:button type="button"  name="btnOk" captionid="WF.Ok" bundle="${fmsLables}"  onclick="WorkflowAction()"></hdiits:button></center>
			
					
		<script>
		
		function WorkflowAction()
		{
			if(window.opener.document.getElementById('tabclickcnt').value=="tab2")
			{
				var aa = window.opener.document.getElementById("myFrame").src;
				window.opener.document.getElementById("myFrame").src = aa;
			}
			window.close();
		}
		</script>
<%
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
%>


<script>
	try
	{
		//window.open("hdiits.htm?actionFlag=wf_NewWorkFlowHomePage&docType=WorkList&moduleName=WorkList&menuName=forCorrespondenceInbox",window.opener.parent.name);
		//self.close();
	}
	catch(e)
	{
		//alert(" Please Close This Window");
	}
</script>