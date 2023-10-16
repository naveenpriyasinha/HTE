<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/calendar.js"></script>

<c:set var="priority" value="${param.priority}"></c:set>

<script type="text/javascript">
	var tempStr = '${priority}';
	var priorityList = tempStr.split(',');
</script>

<%
try
{
%>
<fmt:setBundle basename="resources.WFLables" var="wfLables" scope="request"/>
<fmt:setBundle basename="resources.workflow.workFlowLables" var="workFlowLables" scope="request"/>

<hdiits:fieldGroup id="fldgrp" bundle="${workFlowLables}" titleCaptionId="WF.SRCH" expandable="true" collapseOnLoad="true">
<table align="center" id="table1" border="1" width="100%" bordercolor="black" height="2%">
	<tr>
		<td style="border:none">
			<table>
				<tr id="jobSelect" style="display: none;">
					<td align="center" colspan="6">
						<table>
							<tr>
								<td>
									<input type="radio" name="jobType" value="0" onclick="showFile()" checked="checked" />
								</td>
								<td>
									<hdiits:caption captionid="WF.FILE" bundle="${workFlowLables}" />
								</td>
								<td>
									<input type="radio" name="jobType" value="1" onclick="showCorr()" />
								</td>
								<td>
									<hdiits:caption captionid="WF.CORR" bundle="${workFlowLables}" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="10%" align="left">
						<hdiits:caption captionid="WF.FILESUBJECT" bundle="${workFlowLables}" />
					</td>
					<td>
						<hdiits:text name="docnames" />
					</td> 
					<td width="10%" align="left">
						<hdiits:caption captionid="WF.DESC" bundle="${workFlowLables}" />
					</td>
					<td>
						<hdiits:text name="fileDesc" />
					</td>
					<td width="10%" align="left">
						<hdiits:caption captionid="WF.TRANSNO" bundle="${workFlowLables}" />
					</td>
					<td>
						<hdiits:text name="transnotxt" />
					</td>
				</tr>
				<tr>
					<td width="10%" align="left" >
						<hdiits:caption captionid="WF.RECDATE" bundle="${workFlowLables}" />
					</td>
					<td width="10%" align="left" >
						<hdiits:caption captionid="WF.RECDATEFROM" bundle="${workFlowLables}" />
						<hdiits:dateTime  name="RecieveDateFrom" captionid="WF.RECDATEFROM" bundle="${workFlowLables}"></hdiits:dateTime>
					</td>
					<td width="10%" align="left" >
						<hdiits:caption captionid="WF.RECDATETO" bundle="${workFlowLables}" />
					</td>
					<td width="10%" align="left" >
						<hdiits:dateTime  name="RecieveDateTo" captionid="WF.RECDATETO" bundle="${workFlowLables}"></hdiits:dateTime>
					</td>
					<td width="10%" align="left">
						<hdiits:caption captionid="WF.PRIORITY" bundle="${workFlowLables}" />
					</td>
					<td width="10%" align="left">
						<select id="comboPriority" name="comboPriority">
							<option selected="selected" value="0"><hdiits:caption captionid="WF.SELECT" bundle="${wfLables}" /></option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="10%" align="left">
						<hdiits:caption captionid="WF.FNAME" bundle="${workFlowLables}" />
					</td>
					<td>
						<hdiits:text name="empFName" />
					</td>
					<td width="10%" align="left">
						<hdiits:caption captionid="WF.MNAME" bundle="${workFlowLables}" />
					</td>
					<td>
						<hdiits:text name="empMName" />
					</td>
					<td width="10%" align="left">
						<hdiits:caption captionid="WF.LNAME" bundle="${workFlowLables}" />
					</td>
					<td>
						<hdiits:text name="empLName" />
					</td>
				</tr>
				<tr id="agerow">
					<td width="10%" align="left">
						<hdiits:caption captionid="WF.AGERANGE" bundle="${workFlowLables}" />
					</td>
					<td>
						<table>
							<tr>
								<td align="left">
									<hdiits:caption captionid="WF.AGEFROM" bundle="${workFlowLables}" />
								</td>
								<td align="right">
									<hdiits:text name="agingFrom" maxlength="2" onblur="checkNumber(this)" />
								</td>
							</tr>
						</table>
					</td>
					<td width="10%" align="left">
						<hdiits:caption captionid="WF.AGETO" bundle="${workFlowLables}" />
					</td>
					<td>
						<hdiits:text name="agingTo" maxlength="2" onblur="checkNumber(this)"/>
					</td>
					<td width="10%" align="left">
					</td>
					<td>
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
var comboid = document.getElementById('comboPriority');
for(var i=0; i<priorityList.length;)
{
	var element = document.createElement('option');   				
	element.value = priorityList[i++];	
	element.text = priorityList[i++];
	try
	{
		comboid.add(element,null); // standards compliant
	}
	catch(ex)
	{
	    comboid.add(element); // IE only
	}
}

function checkNumber(tag)
{
	if(!(parseInt(tag.value)>=0 && parseInt(tag.value)<=99))
	{
		alert('<fmt:message key="WF.ALLOWCHARS" bundle="${wfLables}" />');
		tag.value = '';
	}
}

function resetValues()
{
	if(confirm('<fmt:message key="WF.RESETCONFIRM" bundle="${wfLables}" />')==true)
	{
		document.forms[0].docnames.value='';
		document.forms[0].fileDesc.value='';
		document.forms[0].RecieveDateFrom.value='';
		document.forms[0].RecieveDateTo.value='';
		document.forms[0].empFName.value='';
		document.forms[0].empMName.value='';
		document.forms[0].empLName.value='';
		document.forms[0].transnotxt.value='';
//		document.forms[0].branch.value='';
		document.forms[0].comboPriority.selectedIndex='0';
	}
}

var radioSelect = '0';
function showFile()
{
	radioSelect = '0';
}

function showCorr()
{
	radioSelect = '1';
}

</script>
<%
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
%>