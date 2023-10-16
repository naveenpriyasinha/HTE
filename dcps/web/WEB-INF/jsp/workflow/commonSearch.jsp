<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript" src="script/common/calendar.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="docNameList" value="${resultMap.docNameList}"></c:set>
<c:set var="docDesc" value="${param.docDesc}"></c:set>
<c:set var="subjectFilter" value="${param.subjectFilter}"></c:set>
<c:set var="transNo" value="${param.transNo}"></c:set>
<c:set var="empFName" value="${param.empFName}"></c:set>
<c:set var="empMName" value="${param.empMName}"></c:set>
<c:set var="empLName" value="${param.empLName}"></c:set>
<c:set var="unreadJob" value="${param.unread}"></c:set>
<c:set var="recieveDateTo" value="${param.recieveDateTo}"></c:set>
<c:set var="recieveDateFrom" value="${param.recieveDateFrom}"></c:set>

<%
try
{
%>
<script ="text/javascript" src="script/common/tabcontent.js"></script>
<fmt:setBundle basename="resources.workflow.workFlowLables" var="workFlowLables" scope="request"/>
<hdiits:hidden name="docType" default="2" />
<c:set var="docIdForSearchFromService" value="forSearchDocid"></c:set>

<c:if test="${resultMap.docId != null}">
	<c:set var="docIdForSearchFromService" value="${resultMap.docId}"></c:set>
</c:if>
<hdiits:hidden name="docIdForSearch" default="${docIdForSearchFromService}"/>

<hdiits:fieldGroup id="fldgrp" bundle="${workFlowLables}" titleCaptionId="WF.SRCH" expandable="true" collapseOnLoad="true">
<table align="center" id="table1" border="1" width="100%" bordercolor="black" height="2%">
<tr>
	<td style="border:none">
			<table>
			<tr>
				<td width="10%" align="left">
					<nobr><b><hdiits:caption captionid="WF.FILESUBJECT" bundle="${workFlowLables}" /></b></nobr>
				</td>
				<td>
					<hdiits:text name="docnames" default="${subjectFilter}"/>
				</td> 
				<td width="10%" align="left">
					<nobr><hdiits:caption captionid="WF.DESC" bundle="${workFlowLables}" /></nobr>
				</td>
				<td>
					<hdiits:text name="fileDesc" default="${docDesc}"/>
				</td>
				<td width="10%" align="left">
					<nobr><hdiits:caption captionid="WF.TRANSNO" bundle="${workFlowLables}" /></nobr>
				</td>
				<td>
					<hdiits:text name="transnotxt" default="${transNo}"/>
				</td>
			</tr>
			<tr>
				<td width="10%" align="left" >
					<nobr><hdiits:caption captionid="WF.RECDATE" bundle="${workFlowLables}" /></nobr>
				</td>
				<td width="10%" align="left" >
				<nobr>
						<hdiits:caption captionid="WF.RECDATEFROM" bundle="${workFlowLables}" />
						<hdiits:dateTime  name="RecieveDateFrom" captionid="WF.RECDATEFROM" bundle="${workFlowLables}"></hdiits:dateTime>
				</nobr>
				</td>
				<td width="10%" align="left" >
					<nobr><hdiits:caption captionid="WF.RECDATETO" bundle="${workFlowLables}" /></nobr>
				</td>
				<td width="10%" align="left" >
					<hdiits:dateTime  name="RecieveDateTo" captionid="WF.RECDATETO" bundle="${workFlowLables}"></hdiits:dateTime>
				</td>
			</nobr>	
			</tr>
			<tr>
				<td width="10%" align="left">
					<nobr><hdiits:caption captionid="WF.FNAME" bundle="${workFlowLables}" /></nobr>
				</td>
				<td>
					<hdiits:text name="empFName" default="${empFName}"/>
				</td>
				<td width="10%" align="left">
					<nobr><hdiits:caption captionid="WF.MNAME" bundle="${workFlowLables}" /></nobr>
				</td>
				<td>
					<hdiits:text name="empMName" default="${empMName}"/>
				</td>
				<td width="10%" align="left">
					<nobr><hdiits:caption captionid="WF.LNAME" bundle="${workFlowLables}" /></nobr>
				</td>
				<td>
					<hdiits:text name="empLName" default="${empLName}"/>
				</td>
			</tr>
			</table>
			<table align="center" width="100%" style="border: none;">
			<tr>
				<td align="center" width="100%">
					  <hdiits:button name="Search" type="button" captionid="WF.SRCH" bundle="${workFlowLables}" onclick="javascript:getSearchData()"/>
					  <hdiits:button  name="btnReset" type="button" onclick="resetValues()" captionid="WF.RESET" bundle="${workFlowLables}"/>
				</td>
			</tr>		
			</table>
	</td>
	</tr>
	
</table>
</hdiits:fieldGroup>
<script type="text/javascript">
function toCallOnLoadOfPage(recDateFrom, recDateTo)
{
	try
	{
	if(document.getElementById('RecieveDateFrom') != null)
		document.getElementById('RecieveDateFrom').value=recDateFrom;
	if(document.getElementById('RecieveDateTo') != null)
		document.getElementById('RecieveDateTo').value=recDateTo;
	}
	catch(e)
	{
		alert('abc' + e.message);
	}
		
	if(document.getElementById("viewdocnameid") != null)
	{
		var cmb = document.getElementById("viewdocnameid");
		for(var cntComboLength=0;cntComboLength<cmb.length;cntComboLength++)
		{
			if(subjectFilter == cmb.options[cntComboLength].value)
			{
				cmb.options[cntComboLength].selected = true;
				break;
			}
		}
	}
}

function resetValues()
{
	if(confirm("The Entered Values will cleared, Please Confirm?")==true)
	{
		document.forms[0].docnames.value='';
		document.forms[0].fileDesc.value='';
		document.forms[0].RecieveDateFrom.value='';
		document.forms[0].RecieveDateTo.value='';
		document.forms[0].empFName.value='';
		document.forms[0].empMName.value='';
		document.forms[0].empLName.value='';
		document.forms[0].transnotxt.value='';
  }
}

	function getSearchData()
	{
		document.getElementById('searchVal').value="1";
		if('${param.fromOutBox}' == 'true')
			document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=getSentDocListForWorkflow";
		else
			document.forms[0].action = "${contextPath}/hdiits.htm?actionFlag=getFilteredfWorkList";
		document.forms[0].submit();
	}

	toCallOnLoadOfPage("${recieveDateFrom}", "${recieveDateTo}");
	
</script>

<%
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
%>

