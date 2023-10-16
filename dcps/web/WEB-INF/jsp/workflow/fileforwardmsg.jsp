
<% try {  %>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="postName" value="${resValue.postName}"></c:set>
<c:set var="postId" value="${resValue.postId}"></c:set>
<c:set var="action" value="${resValue.action}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="sender" value="${resValue.sender}"></c:set>
<c:set var="errorMsg" value="${resValue.error}"></c:set>
<c:set var="ProceedFmsAction" value="${resValue.ProceedFmsAction}"></c:set>
<script type="text/javascript">

var action;
var sender = '${sender}';
var cont='${resValue.ProceedFmsAction}';
var FmsAlertMsg='${resValue.FmsAlertMsg}'

var errorMsg='${resValue.error}'
var intervalId,actionIntervalId;
function  performFmsAction(action)
{
	if(action=='forward')
	{
		window.opener.forward();
		window.clearInterval(actionIntervalId);
	}
	if(action=='return')
	{
		window.opener.returnDoc();
		window.clearInterval(actionIntervalId);
	}
	if(action=='returndown')
	{
		window.opener.returnDownDoc();
		window.clearInterval(actionIntervalId);
	}
}


if(cont=="No")
{
	if(FmsAlertMsg!="")
	{
		alert(FmsAlertMsg);
	}
	else
	{
		alert('<fmt:message key="WF.FmsActMsg" bundle="${fmsLables}"></fmt:message>');
	}
		
	window.close();
}
</script>
<hdiits:form name="CorrespondenceMsg" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >





<table style="display: none" id="corrMvmntTable">
<tr>
<td>
<br><br>


<IMG src="themes/defaulttheme/images/workflow/pc2.png">
<br>


<b><label>${resValue.fromEmpName}</label></b>
</br>
<b><label>${resValue.fromDesgName}</label></b>


</td>
<td width="300" valign=middle>
<Marquee id="marq" loop=-1 scrollamount=5 direction=right>
<IMG align=middle src="themes/defaulttheme/images/workflow/file1.png" border="0">
</Marquee>
</td>
<td align=center>
<br><br>
<IMG src="themes/defaulttheme/images/workflow/pc2.png">
<br>

<b><label>${resValue.toEmpName}</label></b>
</br>
<b><label>${resValue.toDesgName}</label></b>
</td>
</tr>
</table>



</hdiits:form>
<c:if test="${ProceedFmsAction eq 'Yes'}">
	<c:if test="${postName eq 'noPost'}">
	
				<c:if test="${action eq 'forward'}">
			 			<script type="text/javascript">
			 				alert('<fmt:message key="WF.NoPostForwardFile" bundle="${fmsLables}"></fmt:message>');
			 			</script>	
			 	</c:if>	
			 	<c:if test="${action eq 'return'}">
						<script type="text/javascript">	
								alert('<fmt:message key="WF.NoPostReturnFile" bundle="${fmsLables}"></fmt:message>');
			 			</script>
				</c:if>	
				<c:if test="${action eq 'returnDown'}">
						<script type="text/javascript">	
								alert('<fmt:message key="WF.NoPostReturnFile" bundle="${fmsLables}"></fmt:message>');
			 			</script>
				</c:if>		
			  <script>	
			 	window.close();
			  </script>
	</c:if>
	<c:if test="${postName ne 'noPost'}">
	
				
				
				<c:if test="${action eq 'forward'}">
					<c:if test="${langId eq '1'}">
							<script type="text/javascript">
								if(!(confirm('<fmt:message key="WF.FileForwardActMsg" bundle="${fmsLables}"></fmt:message>  <c:out value="${postName}?"></c:out>')))
								{
										window.close();
								}		
								else
								{
									document.getElementById('corrMvmntTable').style.display='';
									if(sender != ''){										
										window.opener.document.getElementById('forwardFromSupportHandler').value='yes';
										}
									
									
									window.opener.document.getElementById('postId_Hidden').value=${postId};										
									actionIntervalId=window.setInterval("performFmsAction('forward');",8000);
									
								}
							</script>
					</c:if>	
					
				</c:if>
				<c:if test="${action eq 'return'}">
					<c:if test="${langId eq '1'}">
							<script type="text/javascript">
								if(!(confirm('<fmt:message key="WF.FileReturnActMsg" bundle="${fmsLables}"></fmt:message><c:out value="${postName}?"></c:out>')))
								{
										window.close();
								}		
								else
								{
									document.getElementById('corrMvmntTable').style.display='';
									window.opener.document.getElementById('postId_Hidden').value=${postId};
									actionIntervalId=window.setInterval("performFmsAction('return');",8000);
								}
							</script>
					</c:if>
				</c:if>
				<c:if test="${action eq 'returnDown'}">
					<c:if test="${langId eq '1'}">
							<script type="text/javascript">
								if(!(confirm('<fmt:message key="WF.FileReturnActMsg" bundle="${fmsLables}"></fmt:message><c:out value="${postName}?"></c:out>')))
								{
										window.close();
								}		
								else
								{
									document.getElementById('corrMvmntTable').style.display='';
									window.opener.document.getElementById('postId_Hidden').value=${postId};
									actionIntervalId=window.setInterval("performFmsAction('returndown');",8000);
									
								}
							</script>
					</c:if>
				</c:if>
				
				<c:if test="${action eq 'forward'}">
					<c:if test="${langId eq '2'}">
							<script type="text/javascript">
								if(!(confirm('<fmt:message key="WF.CommonActMsg" bundle="${fmsLables}"></fmt:message><c:out value="${postName}"></c:out><fmt:message key="WF.FileForwardActMsg" bundle="${fmsLables}"></fmt:message>')))
								{
										window.close();
								}		
								else
								{
									document.getElementById('corrMvmntTable').style.display='';
									if(sender != '')
										window.opener.document.getElementById('forwardFromSupportHandler').value='yes';
										
									window.opener.document.getElementById('postId_Hidden').value=${postId};	
									actionIntervalId=window.setInterval("performFmsAction('forward');",8000);
									
								}
							</script>
					</c:if>	
					
				</c:if>
				<c:if test="${action eq 'return'}">
					<c:if test="${langId eq '2'}">
							<script type="text/javascript">
								if(!(confirm('<fmt:message key="WF.CommonActMsg" bundle="${fmsLables}"></fmt:message><c:out value="${postName}"></c:out><fmt:message key="WF.FileReturnActMsg" bundle="${fmsLables}"></fmt:message>')))
								{
										window.close();
								}		
								else
								{
									document.getElementById('corrMvmntTable').style.display='';
									window.opener.document.getElementById('postId_Hidden').value=${postId};
									actionIntervalId=window.setInterval("performFmsAction('return');",8000);
									
								}
							</script>
					</c:if>
				</c:if>
				<c:if test="${action eq 'returnDown'}">
					<c:if test="${langId eq '2'}">
							<script type="text/javascript">
								if(!(confirm('<fmt:message key="WF.CommonActMsg" bundle="${fmsLables}"></fmt:message><c:out value="${postName}"></c:out><fmt:message key="WF.FileReturnActMsg" bundle="${fmsLables}"></fmt:message>')))
								{
										window.close();
								}		
								else
								{
									document.getElementById('corrMvmntTable').style.display='';
									window.opener.document.getElementById('postId_Hidden').value=${postId};
									actionIntervalId=window.setInterval("performFmsAction('returndown');",8000);
									
								}
							</script>
					</c:if>
				</c:if>
	</c:if>
</c:if>

<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>
