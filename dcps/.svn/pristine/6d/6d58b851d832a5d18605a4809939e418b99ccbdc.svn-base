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
<c:set var="roleId" value="${resValue.roleId}"></c:set>
<c:set var="langId" value="${resValue.langId}"></c:set>
<c:set var="groupForwardFlag" value="${resValue.groupForwardFlag}"></c:set>
<c:set var="postIdsForPool" value="${resValue.postIdsForPool}"></c:set>
<c:set var="loadBalance" value="${resValue.loadBalance}"></c:set>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<script>
window.opener=parent.window.opener
if(window.dialogArguments) 
{
  window.opener = window.dialogArguments;
} 

</script>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<hdiits:form name="HierarchyForm" method="POST" validate="true" action="./hdiits.htm">
<c:if test="${resValue.MaxFlag eq 'Y'}">
	<hdiits:caption captionid="WF.Message" bundle="${fmsLables}"/>
</c:if>
<c:if test="${action eq 'forward'}">
 <script type="text/javascript">
 msg='<fmt:message key="WF.FWDJOBMSG" bundle="${fmsLables}"/>';
 action='forward';
 </script>
</c:if>

<c:if test="${action eq 'return'}">
 <script type="text/javascript">
 msg='<fmt:message key="WF.RTNJOBMSG" bundle="${fmsLables}"/>';
 action='return';
 </script>
</c:if>

<c:if test="${action eq 'return'}">
 <script type="text/javascript">
 msg='<fmt:message key="WF.RTNJOBMSG" bundle="${fmsLables}"/>';
 action='return';
 </script>
</c:if>

<c:choose>
<c:when test="${langId eq '1'}">
<script type="text/javascript">
 appendmsg = '';
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
 appendmsg = '<fmt:message key="WF.APPENDMSG" bundle="${fmsLables}"/>';
 </script>
</c:otherwise>
</c:choose>
<c:set var="srNo" value="${1}" />
<c:set var="counter" value="${0}" />
<center>
<%--<hdiits:checkbox name="poolForward" value="1" onclick="selectAll()"/> --%>

</center>
<hdiits:hidden name="postIdsForGroup"/>

	<c:if test="${loadBalance==1}">	
		<c:if test="${not empty HierarchyDetails}">
			<center><font size="3" color="gray"><b><fmt:message bundle="${fmsLables}" key="WF.MSGPOSTHRCHY"></fmt:message></b></font></center>
				<display:table list="${HierarchyDetails}" pagesize="12" requestURI="" id="row"  style="width:100%">
					<display:column  titleKey="Select" headerClass="datatableheader" media="HTML"><input type="checkbox"  name="postSelect"  value="${row.postId},${row.empName}" onclick="addVal(${row.postId},this)"> </display:column>
					<display:column  titleKey="WF.POSTNAME" headerClass="datatableheader">${row.postName}</display:column>
					<display:column  titleKey="WF.POSTNAME" headerClass="datatableheader">${row.noOfJobs}</display:column>
					<display:column  titleKey="WF.EMPNAME" headerClass="datatableheader">${row.empName}</display:column>
					<display:column  titleKey="WF.DESGNAME" headerClass="datatableheader">${row.designationName}</display:column>
					<display:column  titleKey="WF.LOCNAME" headerClass="datatableheader">${row.locationName}</display:column>
					<c:set var="counter" value="${counter+1}" />
					<c:set var="srNo" value="${srNo+1}" />
				</display:table>
		
			
		
	</c:if>
	</c:if>
	
	<c:if test="${loadBalance==0}">	
		<c:if test="${not empty HierarchyDetails}">
			<center><font size="3" color="gray"><b><fmt:message bundle="${fmsLables}" key="WF.MSGPOSTHRCHY"></fmt:message></b></font></center>
				<display:table list="${HierarchyDetails}" pagesize="12" requestURI="" id="row"  style="width:100%">
					<display:column  titleKey="Select" headerClass="datatableheader" media="HTML"><input type="checkbox"  name="postSelect"  value="${row.postId},${row.empName}" onclick="addVal(${row.postId},this)"> </display:column>
					<display:column  titleKey="WF.POSTNAME" headerClass="datatableheader">${row.postName}</display:column>
					
					<display:column  titleKey="WF.EMPNAME" headerClass="datatableheader">${row.empName}</display:column>
					<display:column  titleKey="WF.DESGNAME" headerClass="datatableheader">${row.designationName}</display:column>
					<display:column  titleKey="WF.LOCNAME" headerClass="datatableheader">${row.locationName}</display:column>
					<c:set var="counter" value="${counter+1}" />
					<c:set var="srNo" value="${srNo+1}" />
				</display:table>
		
			
		
	</c:if>
	</c:if>
<center><hdiits:button name="sentToPool" type="button" bundle="${fmsLables}" captionid="WF.SENTTOALL" onclick="forwardToPoolByPost()"/></center>


<script language="javascript">
function createMarkedPostList()
{
	var urlStyle ='toolbar=no,status=yes,menubar=no,location=no,scrollbars=no'; 	
	window.open('${contextPath}/hdiits.htm?viewName=wf-departmentSrch','',urlStyle);
	window.close();
}
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
function addVal(postId,src)
{
	if(src.checked == true)
	{
		if(document.getElementById("postIdsForGroup").value=='')
			document.getElementById("postIdsForGroup").value=postId
		else
			document.getElementById("postIdsForGroup").value=document.getElementById("postIdsForGroup").value+','+postId;
	}else
	{
		var value=document.getElementById("postIdsForGroup").value;		
		var index=value.lastIndexOf(postId);		
		value1=value.substring(0,index-1);
		if(value1=='')
			value2=value.substring(index+7,value.length);
		else
			value2=value.substring(index+6,value.length);
		
		value=value1+value2;
	
		document.getElementById("postIdsForGroup").value=value;		
	}
}
		
	function forwardToPool()
	{
		window.opener.document.getElementById("postList").value ='${postIdsForPool}';
		window.opener.document.getElementById("commonPoolItemFlag").value ='yes';			
		window.opener.assingItemTogroup();
		window.close();
	}
	
	function selectAll()
	{
	
		var arrLength = document.forms[0].postSelect.length;	
		for(var len=0; len < eval(arrLength); len++)
		{			
			document.forms[0].postSelect[len].checked = true			
		} 		
		document.getElementById("postIdsForGroup").value='${postIdsForPool}';
	}
	
	function forwardToPoolByPost()
	{
	
		var value=document.getElementById("postIdsForGroup").value;		
		var index=value.lastIndexOf(',');
			
		if(index==-1){
			window.opener.parent.document.forms[0].postId_Hidden.value=document.getElementById("postIdsForGroup").value;
			window.opener.parent.document.forms[0].roleId_Hidden.value='${roleId}';
			window.opener.parent.document.forms[0].method="post";
			window.opener.parent.document.forms[0].action="${contextPath}/hdiits.htm?actionFlag=forwardFile";
			window.opener.forward();
		}
		else
		{	
			window.opener.document.getElementById("postList").value =document.getElementById("postIdsForGroup").value;
			window.opener.document.getElementById("commonPoolItemFlag").value ='yes';			
			if(window.opener.document.getElementById("postList").value	==''){			alert("please select posts for group");
				return;
			}		
			window.opener.assingItemTogroup();
		}
		window.close();
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