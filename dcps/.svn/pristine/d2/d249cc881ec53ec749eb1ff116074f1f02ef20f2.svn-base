<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}" scope="session"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" scope="session"></c:set>
<c:set var="ServiceObj" value="${resValue.ServiceLocator}" scope="session"></c:set>

<hdiits:form name="frameForm" validate="true" encType="multipart/form-data">
<hdiits:hidden name="actionFlag" default="firstFrame&elementId=999957" />

<table width="100%"  height="100%" border="1">
<tr>
	<td width="100%"  height="100%">
			<iframe src="hdiits.htm?actionFlag=fetchFileDraft&fileId=10001030167" width="100%" height="50%" marginwidth="0" marginheight="0" scrolling="yes">
			</iframe>
	
			<iframe  src="./servlet/FileOpenServlet?docId=10001030881&attachmentId=<%=request.getParameter("attachmentId")%>&attachmentSerialNumber=<%=request.getParameter("attachmentSerialNumber")%>" name="frameNameDyn" id="frame_id"  width="100%" height="50%" marginwidth="0" marginheight="0">
			</iframe>
	</td>
</tr>
</table>
</hdiits:form>


