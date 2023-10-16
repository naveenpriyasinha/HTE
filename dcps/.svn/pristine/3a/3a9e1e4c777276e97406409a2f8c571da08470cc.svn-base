<%
try
{
%>
<%@ include file="../core/include.jsp"%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables"	scope="request" />

<script>
function insertNewFolderDetail()
{
	window.document.forms[0].action = "hdiits.htm?actionFlag=FMS_insertNewFolderDetail";
	window.document.forms[0].submit();
}
</script>

<hdiits:form name="crtnewfolderfrm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true">
<hdiits:hidden name="parent_folder_id" default='${param.parent_folder_id}'/>
<center><h3><fmt:message key="WF.CrtNewFolder" bundle="${fmsLables}" ></fmt:message></h3></center>


<table width="100%" border="1" bordercolor="green">
	
	<tr>
		<td style="border: none" width="30%" align="left">
			
		</td>
		<td style="border: none" width="20%" align="left">
			<hdiits:caption  captionid="WF.FolderName" bundle="${fmsLables}" />
			<hdiits:caption  captionid=""  caption=":"/>
		</td>
		<td style="border: none" width="50%" align="left">
			<hdiits:text name="txtfoldername" />
		</td>
	</tr>
	<tr>	
		<td style="border: none" width="30%" align="left">
			
		</td>
		<td style="border: none" width="20%" align="left">
			<hdiits:caption captionid="WF.FolderDesc" bundle="${fmsLables}" />
			<hdiits:caption  captionid=""  caption=":"/>
		</td>
		<td style="border: none" width="50%" align="left">
			<hdiits:text  name="txtfolderdesc"></hdiits:text>
		</td>
	</tr>
	
	
</table>
<br>
<center>
	<hdiits:button name="crtfolderbtn"  captionid="WF.Create" bundle="${fmsLables}"  type="button"  onclick="insertNewFolderDetail()" />
	<hdiits:button name="closebtn" captionid="WF.Close" bundle="${fmsLables}"  type="button"  onclick="window.close()"/>
</center>
</hdiits:form>
<%
}
catch (Exception e)
{
	e.printStackTrace();	
}
%>