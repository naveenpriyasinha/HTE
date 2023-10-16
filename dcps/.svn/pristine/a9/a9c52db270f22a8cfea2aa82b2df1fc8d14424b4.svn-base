<%
try {
%>

<%@ include file="../core/include.jsp"%>
<%@ page autoFlush="true" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>

<fmt:setBundle basename="resources.workflow.FMSLables" var="wfLables"	scope="request" />
<fmt:message key="WF.Select" bundle="${wfLables}" var="select"></fmt:message>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="FmsFileCheckList" value="${resValue.FmsFileCheckList}"></c:set>
<c:set var="CmnLookupMstYes" value="${resValue.CmnLookupMstYes}"></c:set>
<c:set var="CmnLookupMstNo" value="${resValue.CmnLookupMstNo}"></c:set>

<hdiits:form name="CheckLisForm" method="POST" validate="true" action="./hdiits.htm">
<hdiits:hidden name="fileId" default="${resValue.fileId}"/>
<hdiits:hidden name="id" default="${FmsFileCheckList[0].srNo}"/>
<hdiits:hidden name="length" default="${resValue.length}"/>
<fmt:message key="WF.Select" bundle="${wfLables}" var="select"></fmt:message>

	<display:table list="${FmsFileCheckList}" pagesize="12" requestURI="" id="row"  style="width:100%">
		
		<display:column  titleKey="WF.ApproveStatus" headerClass="datatableheader">
		
			<select  size="1"  sort="false"  id="${row.srNo}" name="${row.srNo}">	
							<c:if test="${row.status eq 0}">
								<option value="-1">${select}</option>
					    		<option value="<c:out value="${CmnLookupMstNo.lookupName}"/>">
								<c:out value="${CmnLookupMstNo.lookupShortName}"/></option>
								<option value="<c:out value="${CmnLookupMstYes.lookupName}"/>">
								<c:out value="${CmnLookupMstYes.lookupShortName}"/></option>
					    	</c:if>	
					    	<c:if test="${row.status eq 1}">
					    	<option value="-1">${select}</option>
									<option value="<c:out value="${CmnLookupMstNo.lookupName}"/>"  selected="true">
									<c:out value="${CmnLookupMstNo.lookupShortName}"/></option>
									<option value="<c:out value="${CmnLookupMstYes.lookupName}"/>">
									<c:out value="${CmnLookupMstYes.lookupShortName}"/></option>
							</c:if>
										
			    			<c:if test="${row.status eq 2}">
			    			<option value="-1">${select}</option>
									<option value="<c:out value="${CmnLookupMstYes.lookupName}"/>"  selected="true">
									<c:out value="${CmnLookupMstYes.lookupShortName}"/></option>
									<option value="<c:out value="${CmnLookupMstNo.lookupName}"/>">
									<c:out value="${CmnLookupMstNo.lookupShortName}"/></option>
							</c:if>										  							
										
			 </select>
		
		</display:column>
		
		<display:column titleKey="WF.description" headerClass="datatableheader">${row.description}</display:column>
	
		
	
	</display:table>
		
	<center><hdiits:button type="button"  name="Addfile" id="Addfile" captionid="WF.SAVE" bundle="${wfLables}" onclick="Add()" /></center>
		
</hdiits:form>
		
<%}

catch(Exception e)
{
	e.printStackTrace();
}

%>
<script>
	function Add()
	{	
		var length=document.getElementById('length').value;
		if(length==0)
			alert('there is no check list for this doc');
		if(length!=0){
			var action="${contextPath}/hdiits.htm?actionFlag=setCheckListForFile";	
			document.getElementById("CheckLisForm").method="post";
			document.getElementById("CheckLisForm").action=action;									
			document.getElementById("CheckLisForm").submit();	
		}	
			
	}
	
	
</script>