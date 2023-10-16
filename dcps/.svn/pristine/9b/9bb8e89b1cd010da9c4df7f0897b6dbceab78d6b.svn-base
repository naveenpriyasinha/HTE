<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Locale"%>
<%@page import="com.tcs.sgv.common.helper.SessionHelper"%>
<%@page import="java.util.ResourceBundle"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Insert title here</title>
			
			<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	
	<script language="javascript">
		
		function saveAttach(url)
		{
			var cnt = document.frmAttachment.cntattachment.value;
			if(cnt<=0)
			{
				alert('Select the file before attaching.');
				return false;
			}
			document.frmAttachment.action = url;	
			document.frmAttachment.submit();			
			
		}
		</script>
	</head>
	<%
		Locale locale = new Locale(SessionHelper.getLocale(request));
		ResourceBundle bundle =ResourceBundle.getBundle("resources/billproc/billproc",locale);			
		String lStrAttachment = bundle.getString("CMN.Attachment");
		System.out.println("Value of Attachment from constants file : " +lStrAttachment);
	%>
	<body>
	<script language="javascript">
	</script>
	<hdiits:form name="frmAttachment" method="post" encType="multipart/form-data" validate="true">
		<table width="90%" align="center">
			
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr><td colspan="2">&nbsp;</td></tr>
						
			<tr class="TableHeaderBG">
				<td colspan="2" class="datatableheader">
					<fmt:message key="CMN.ATTACHMENT" bundle="${billprocLabels}"></fmt:message>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		
			<tr>
				<td colspan="2">
				
					<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
         	  	        <jsp:param name="attachmentName" value="attachment" />
         	  	        <jsp:param name="formName" value="frmAttachment" />
                		<jsp:param name="attachmentType" value="Document" />
                		<jsp:param name="attachmentTitle" value="<%=lStrAttachment%>" />
					    <jsp:param name="multiple" value="N" />
		    		</jsp:include>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		
			<tr>
				<td align="right">
					<hdiits:button name="btnAttach" type="button" value="Attach File" onclick="javascript:saveAttach('ifms.htm?actionFlag=insAttach')" />
				</td>
				<td align="left">
					<hdiits:button name="btnClose" type="button" value="Close" onclick="self.close();" />
				</td>
			</tr>
		</table>
		<%
			ResultObject rs = (ResultObject)request.getAttribute("result");
			Map map = (Map)rs.getResultValue();
			if(map.get("CloseFlag") != null)
			{				
				%>
					<script>
						self.close();
					</script>
			 	<%
			}
		%>
	</hdiits:form>
	</body>
</html>