<%
try
{
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.workflow.FMS_TEMPLables" var="fmsTempLables" scope="request"/>
<hdiits:form name="fmsConfigureFrm" validate="true" method="POST"	action="./hdiits.htm" encType="multipart/form-data">
<br>
<center>
	<b>
		<font size="3">
			<fmt:message key="FMS.CONFIGDETAILS"  bundle="${fmsTempLables}"></fmt:message>
		</font>
	</b>
</center>
<br>
<br>
<table width="100%" height="100%" border="1">
	<tr>
	<td valign="top">
		<table width="100%" border="1"> 
		<ul style="list-style-image: url(images/next.gif)">
			<tr>
				<td>
					<li style="list-style-image: url(images/next.gif)">
						<font size="2">
						<b>
							<a href="hdiits.htm?viewName=fms-TempAttrib" target="target_frame" >
								<fmt:message key="FMS.ATTRIBUTE" bundle="${fmsTempLables}"/>
							</a>
						</b>
						</font>
					</li>
				</td>
			</tr>
		</ul>		
		<ul style="list-style-image: url(images/next.gif)">
			<tr>
				<td>
					<li style="list-style-image: url(images/next.gif)">
						<font size="2">
						<b>
							<a href="hdiits.htm?viewName=fms-TempCatgry" target="target_frame" >
								<fmt:message key="FMS.CATEGORY" bundle="${fmsTempLables}"/>
							</a>
						</b>
						</font>
					</li>
				</td>
			</tr>
		</ul>
		<ul style="list-style-image: url(images/next.gif)">
			<tr>
				<td>
					<li style="list-style-image: url(images/next.gif)">
						<font size="2">
						<b>
							<a href="hdiits.htm?viewName=fms-TempRefDocs" target="target_frame" >
								<fmt:message key="FMS.REFDOCSTMP" bundle="${fmsTempLables}"/>
							</a>
						</b>
						</font>
					</li>
				</td>
			</tr>
		</ul>
		</table>
	</td>		
	<td width="80%" height="100%" valign="top">
		<iframe name="target_frame" id="target_frame_id" width="100%" height="670" scrolling="auto"/>	
	</td>
	</tr>
</table>
</hdiits:form>
<%
} 
catch (Exception e)
{
		e.printStackTrace();
}
%>

