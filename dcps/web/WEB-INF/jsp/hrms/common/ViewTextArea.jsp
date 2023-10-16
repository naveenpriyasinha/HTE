<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.common.hrmsCommon" var="hrmsCommonLables" scope="request" />
<TABLE >
			<tr>
				<TD class=fieldLabel><b><U><fmt:message key='<%=request.getParameter("ScreenCaption")%>' bundle="${hrmsCommonLables}" />:</U></b></TD>
			</tr>
			<tr>
				<TD class=fieldLabel width="100%">
				<%	String disableText= request.getParameter("txtdisable"); 
					//System.out.println("Parita=====>>>"+disableText);
				if(disableText!=null && disableText.trim().equalsIgnoreCase("readOnly"))
				   {
				%>
					<textarea name="txtCompainantDesc" cols="75" rows="25" onkeypress="validateSize(<%=request.getParameter("txtSize")%>)" onblur="validateSize2(<%=request.getParameter("txtSize")%>)" disabled="disabled"></textarea>
				<%	
				   }
				else
				{
				%>
					<textarea name="txtCompainantDesc" cols="75" rows="25" onkeypress="validateSize(<%=request.getParameter("txtSize")%>)" onblur="validateSize2(<%=request.getParameter("txtSize")%>)"></textarea>
				<% } %>
				</TD>
			</tr> 
			<tr>
				<TD class=fieldLabel width="100%">
				<center><button name="btnClose" type="button" onclick="closeForm()" value="Close">Close</button></center>
				</TD>
			</tr> 
</table>
<script type="text/javascript">
function closeForm()
{
	window.parent.opener.document.getElementById("<%=request.getParameter("noteDesc")%>").value=document.getElementById("txtCompainantDesc").value;
	window.close();
}
function validateSize(size)
{
	var val=document.getElementById('txtCompainantDesc').value;
	if(val.length > size)
	{
		 event.returnValue = false;
	}
	else
	{
		event.returnValue = true;
	}
}
function validateSize2(size)
{
	var val=document.getElementById('txtCompainantDesc').value;
	//alert(val.length);
	if(val.length > size)
	{
		 alert("<fmt:message key='HRMS.TextAreaSizeExceed' bundle='${hrmsCommonLables}'/>");
		 document.getElementById('txtCompainantDesc').value = val.substring(0,size);
	}
}
document.getElementById("txtCompainantDesc").value=window.parent.opener.document.getElementById("<%=request.getParameter("noteDesc")%>").value;
</script>		
	