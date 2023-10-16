
<%
try
{
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.janvaJog.ContentLabels" var="content" scope="request" />
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<fmt:setBundle basename="resources.workflow.FMS_TEMPLables" var="fmsTempLables" scope="request"/>

<hdiits:form name="wfconfigmenu" validate="true" method="POST"	action="./hdiits.htm" encType="multipart/form-data">
<br>
<center><b><font size="3"><fmt:message key="WF.WfConfigDetails"  bundle="${fmsLables}"></fmt:message></font></b></center>
<br>
<br>
<table width="100%" height="100%" border="1">
	<tr>
	<td valign="top">
		<table width="100%" border="1"> 
			<ul style="list-style-image: url(images/next.gif)">		
			<tr>
				<td>
					<li style="list-style-image: url(images/next.gif)"><font size="2"><b><a href="hdiits.htm?actionFlag=wfLoadTriName" target="target_frame"><fmt:message key="WF.TriCode" bundle="${fmsLables}"></fmt:message></a></b></font></li>
				</td>
			</tr>
			<tr>
				<td>
				<li style="list-style-image: url(images/next.gif)"><font size="2"><b><a href="hdiits.htm?viewName=wf-locBranchDoc" target="target_frame"><fmt:message key="WF.BranchSel" bundle="${fmsLables}"></fmt:message></a></b></font></li>
				</td>
			</tr>			
			<tr>
				<td>
				<li style="list-style-image: url(images/next.gif)"><font size="2"><b><a href="hdiits.htm?actionFlag=getLocationsForSubjectEntry" target="target_frame">Subject Entry</a></b></font></li>
				</td>
			</tr>
			<tr>
				<td>
				<li style="list-style-image: url(images/next.gif)"><font size="2"><b><a href="hdiits.htm?actionFlag=fms-getDocsForModule" target="target_frame">UpDate Subject Details</a></b></font></li>
				</td>
			</tr>
			<tr>
				<td>
				<li style="list-style-image: url(images/next.gif)"><font size="2"><b><a href="hdiits.htm?actionFlag=getLocationsByPostId" target="target_frame">Map Hierarchy</a></b></font></li>
				</td>
			</tr>			
			<tr>
				<td>
				<li style="list-style-image: url(images/next.gif)"><font size="2"><b><a href="hdiits.htm?actionFlag=fms_viewalternateflowDetails" target="target_frame">FlowDetails</a></b></font></li>
				</td>
			</tr>			
			</ul>
		</table>
	</td>	
	<td width="80%" height="100%" valign="top">
		<iframe name="target_frame" id="target_frame_id" width="100%" height="670" scrolling="auto">
		</iframe>
	</td>	
	</tr>
</table>
</hdiits:form>
<script type="text/javascript">
</script>
<%
} 
catch (Exception e)
{
		e.printStackTrace();
}
%>

