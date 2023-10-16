<%try{ %>
<%@ include file="../core/include.jsp" %> 
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="resources.common.ScanLables" var="ScanLables" scope="request"/>
<SCRIPT>
	function showResponse<%=request.getParameter("attachmentName")%>(filename, desc, count) 
	{
		window.opener.formNameGlobalVar<%=request.getParameter("attachmentName")%> = window.opener.document.forms[0].name;
		//var rno = window.opener.document.forms[0].<%=request.getParameter("attachmentName")%>_rowNumber.value;
		var x<%=request.getParameter("attachmentName")%> = window.opener.document.getElementById('myTable<%=request.getParameter("attachmentName")%>').insertRow()

 		var col1<%=request.getParameter("attachmentName")%> = x<%=request.getParameter("attachmentName")%>.insertCell(0);
	    var col2<%=request.getParameter("attachmentName")%> = x<%=request.getParameter("attachmentName")%>.insertCell(1);
	    var col3<%=request.getParameter("attachmentName")%> = x<%=request.getParameter("attachmentName")%>.insertCell(2);

		col1<%=request.getParameter("attachmentName")%>.innerHTML = desc;
		col2<%=request.getParameter("attachmentName")%>.innerHTML = filename;

		<%if(request.getParameter("ismultiple").equalsIgnoreCase("true")){%>		
			var rno = window.opener.returnLatestRowNumber<%=request.getParameter("attachmentName")%>(window.opener.document.forms[0].name); 		
			col3<%=request.getParameter("attachmentName")%>.innerHTML = '<a class="removeLink" onClick="delRow<%=request.getParameter("attachmentName")%>(this,'+ rno + ')" target="target_upload<%=request.getParameter("attachmentName")%>">Remove</a> / <a href="#" onClick="viewRow<%=request.getParameter("attachmentName")%>(this,'+ rno +')">View</a>'; 									
		<%}
		else
		{%>
			col3<%=request.getParameter("attachmentName")%>.innerHTML = '<a class="removeLink" onClick="delRow<%=request.getParameter("attachmentName")%>(this)" target="target_upload<%=request.getParameter("attachmentName")%>">Remove</a> / <a href="#" onClick="viewRow<%=request.getParameter("attachmentName")%>(this)">View</a>';	
		<%}%>
		if(count == 0)
		{
			window.close();	
		}
    }
    function closeApplet()
	{
		window.close();
	}
</SCRIPT>
<fmt:message bundle="${ScanLables}" key="SCN.REMOVALL" var="removeall" />
<fmt:message bundle="${ScanLables}" key="SCN.STARTSCAN" var="startscan" />
<fmt:message bundle="${ScanLables}" key="SCN.UPLOADALL"  var="uploadall" />
<fmt:message bundle="${ScanLables}" key="SCN.SCANNERNOTAVAIL" var="scannernotavailable" />

    	<jsp:plugin name="scanApp" type="applet" code="com.tcs.sgv.common.utils.fileupload.scan.ScanJApplet.class" archive="scan/hdiits-scan.jar,scan/morena.jar,scan/morena_windows.jar,scan/morena_license.jar,scan/jai_core.jar,scan/jai_codec.jar" width="100%" height="100%">
		<jsp:params>
			<jsp:param name="attachmentName" value="${param.attachmentName}" />
			<jsp:param name="rawNumber" value="${param.rawNumber}" />						
			<jsp:param name="attachmentPrefix" value="${param.attachmentPrefix}" />
			<jsp:param name="locale" value='<%=(String)session.getAttribute("locale")%>' />
			<jsp:param name="removeall" value="${removeall}" />
			<jsp:param name="startscan" value="${startscan}" />
			<jsp:param name="uploadall" value="${uploadall}" />
	 		<jsp:param name="scannernotavailable" value="${scannernotavailable}" />
		</jsp:params>
		</jsp:plugin>

<%
}catch(Exception e)
{
	e.printStackTrace();
}
%>