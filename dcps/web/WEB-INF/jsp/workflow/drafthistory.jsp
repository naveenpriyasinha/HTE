<%
try {
	

%>

<%@ include file="../core/include.jsp"%>
<%@ page autoFlush="true" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request" />

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="HierarchyDetails" value="${resValue.listFmsDraftHistoryVO}"></c:set>


<hdiits:form name="HierarchyForm" method="POST" validate="true" action="./hdiits.htm">
<hdiits:hidden name="fileId" default="${resValue.fileId}"/>

<hdiits:hidden name="successFlag" default="${resValue.Success}"/>
<hdiits:hidden name="tabDtlId" default="${resValue.tabDtlId}"/>
<hdiits:hidden name="approveFlag" default="${resValue.approveFlag}"/>
<hdiits:hidden name="corrDraftFlag" default="${resValue.CorrDraftFlag}"/>
	<h1><fmt:message key="WF.HistoryDoc" bundle="${fmsLables}"></fmt:message></h1>					   
	
	
	<display:table list="${HierarchyDetails}" pagesize="12" requestURI="" id="row"  style="width:100%">
		
		<display:column titleKey="Select" headerClass="datatableheader" media="HTML"><input type="radio" name="radio" id="${row.id}" value="${row.id}"> </display:column>
		<display:column  titleKey="WF.POSTNAME" headerClass="datatableheader">${row.userName}</display:column>
		
		<display:column titleKey="WF.Draft" headerClass="datatableheader"><a href="javascript:openDocument('${row.url}');"> <c:out value="${row.fileName}"></c:out></display:column>
		
		</display:table>
	
		
	<center><td><hdiits:button type="button"  name="history" id="history" captionid="WF.REPLACE" bundle="${fmsLables}" onclick="replace();" /></td></center>
</hdiits:form>



<script><!--
	function replace()
	{	
		
		if(document.getElementById('approveFlag').value=='yes')
		{
			alert('<fmt:message key="WF.CANTUPDATE" bundle="${fmsLables}"></fmt:message>');
			return;
		}
		if(confirm('<fmt:message key="WF.REPLACEDRAFT" bundle="${fmsLables}"></fmt:message>'))
		{
			var radioObj = document.getElementsByName('radio');
			var radLength = document.getElementsByName('radio').length;
			var flag=true;
			for(var cnt=0;cnt<radLength && flag==true ;cnt++)
			{
				if(radioObj[cnt].checked == true)
				{
					var id=radioObj[cnt].value;		
					
					var action='';
					action="${contextPath}/hdiits.htm?actionFlag=UpdateDraftByHistory&fileId=${resValue.fileId}&id="+id;
						
					document.getElementById("HierarchyForm").method="post";
					document.getElementById("HierarchyForm").action=action;								
					document.getElementById("HierarchyForm").submit();				
					flag=false;
				}
			}
		}				
				
		
	}
	
	function openDocument(url)	
	{			
			window.open(url);
	}
	
	window.onload=test
	function test()
	{	
			
		if(document.getElementById('successFlag').value=="True")
		{				
				var url="hdiits.htm?actionFlag=fetchCorrDraft";				
				window.opener.document.forms[0].tabDtlId.value=document.getElementById('tabDtlId').value;	
				window.opener.submitForm();
				window.close();
		}
	}
--></script>

<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
