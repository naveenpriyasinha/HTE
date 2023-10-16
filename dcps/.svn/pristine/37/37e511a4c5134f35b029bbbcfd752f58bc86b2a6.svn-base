<%
try {
	

%> 
<script type="text/javascript">
 var msg,action;
 </script>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="HierarchyDetails" value="${resValue.HierarchyCustomList}"></c:set>
<c:set var="MarkedHierarchyCustomList" value="${resValue.MarkedHierarchyCustomList}"></c:set>
<c:set var="action" value="${resValue.action}"></c:set>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<script>
if(window.dialogArguments) 
{
  window.opener = window.dialogArguments;
} 
</script>
<c:if test="${action eq 'forward'}">
 <script type="text/javascript">
 msg="Do you want to farward job to ";
 </script>
</c:if>

<c:if test="${action eq 'return'}">
 <script type="text/javascript">
 msg="Do you want to return job to ";
 </script>
</c:if>

<hdiits:form name="RoleHierarchyForm" method="POST" validate="true" action="./hdiits.htm">
<hdiits:hidden name="roleId" default="${resValue.roleId}"/>
<hdiits:hidden name="actionType" default="${action}"/>
<c:out value="${resValue.hirFlag}"></c:out>
<hdiits:hidden name="hirFlag" default="${resValue.hirFlag}"/>
<c:if test="${resValue.MaxFlag eq 'Y'}">
	<hdiits:caption captionid="WF.Message" bundle="${fmsLables}"/>
</c:if>
<c:if test="${resValue.MaxFlag eq 'Y'}">
	<hdiits:caption captionid="WF.Message" bundle="${fmsLables}"/>
</c:if>

<c:set var="srNo" value="${1}" />
<c:set var="counter" value="${0}" />

<c:if test="${not empty HierarchyDetails}">
<c:if test="${resValue.MaxFlag ne 'Y'}">
<center><font size="3" color="gray"><b><fmt:message bundle="${fmsLables}" key="WF.MSGROLEHRCHY"></fmt:message></b></font>
		<display:table list="${HierarchyDetails}" pagesize="12" requestURI="" id="row"  style="width:100%">
			<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="radio"  name="postSelect"  value="${row.roleId}" onclick="showVal('normal')"> </display:column>
			<display:column  titleKey="WF.ROLENAME" headerClass="datatableheader">${row.roleName}</display:column>			
			<c:set var="counter" value="${counter+1}" />
			<c:set var="srNo" value="${srNo+1}" />
		</display:table>
</c:if>
</c:if>

<c:if test="${not empty MarkedHierarchyCustomList}">
<center><font size="3" color="gray"><b><fmt:message bundle="${fmsLables}" key="WF.MSGMARKEDHRCHY"></fmt:message></b></font>
	<display:table list="${MarkedHierarchyCustomList}" pagesize="12" requestURI="" id="row"  style="width:100%">
			<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="radio"  name="postSelectForMarked"  value="${row.postId},${row.empName}" onclick="showVal('marked')"> </display:column>
			<display:column  titleKey="WF.POSTNAME" headerClass="datatableheader">${row.postName}</display:column>
			<display:column  titleKey="WF.EMPNAME" headerClass="datatableheader">${row.empName}</display:column>
			<c:set var="counter" value="${counter+1}" />
			<c:set var="srNo" value="${srNo+1}" />
	</display:table>
</c:if>


<script language="javascript">

 function selectAll()
		{
			var len = '${srNo}';
			for(var counter=0; counter<eval(len)-1; counter++)
			{
				document.forms[0].postSelect[counter].checked=true;
			}
		}
 
  function deselectAll()
		{
			var len = '${srNo}';
			for(var counter=0; counter<eval(len)-1; counter++)
			{
				document.forms[0].postSelect[counter].checked=false;
			}
		}
	function showVal(decisionFlag)
		{
			var radbutName;
			if(decisionFlag == 'normal')
				radbutName = 'postSelect';
			else
				radbutName = 'postSelectForMarked';
		
			var radioObj = document.getElementsByName(radbutName);
			var radLength = document.getElementsByName(radbutName).length;
			for(var cnt=0;cnt<radLength;cnt++)
			{
				if(radioObj[cnt].checked == true)
				{
					document.getElementById("roleId").value=radioObj[cnt].value;
					if(confirm(msg))
					{		
							document.getElementById("RoleHierarchyForm").action="${contextPath}/hdiits.htm?actionFlag=fms-ResolvePostByRole";
							document.getElementById("RoleHierarchyForm").submit();													
					}
					else
						return false;		
					
					
				}
			}
		}
 
</script>
</hdiits:form>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>