<%@ include file="../core/include.jsp" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.app.diary.DiaryLables" var="diaryLables" scope="request"/>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>

<%@page import="java.util.ResourceBundle,java.util.Locale"%>
<html>
<%
	String  locale = (String)session.getAttribute("locale");
	Locale localee = new Locale(locale);
	ResourceBundle bundle =ResourceBundle.getBundle("resources/billproc/billproc",localee);			

%>
	<head>
		<title></title>
		<script language="javascript">
			function audForward(url)
			{								
				url = url +"&BillNo="+<%=request.getParameter("BillNo")%>;
				url = url+ "&BillCat="+window.opener.document.getElementById('id_cmbTCCtgry').value;
//				alert("Forward URL : " +url);
				window.open(url,"_self","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=400"); 
//				window.open(url);
			}			
			
			function audReject(url)
			{
				window.opener.document.forms[0].audAmtFlag.value = '1';
				url = url +"&BillNo="+<%=request.getParameter("BillNo")%>;
				url = url+ "&BillCat="+window.opener.document.getElementById('id_cmbTCCtgry').value;
//				alert("Reject URL : " +url);
				window.open(url,"_self","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=500,height=400"); 
			}
		</script>
	</head>
	
	<body>
		<hdiits:form name="AdtAmtMismatch" validate="true" id="id_AdtAmtMismatch">
		       <input type="hidden" name="toPost" value="-1">
				<input type="hidden" name="toUser" value="-1">
				<input type="hidden" name="SendTo" value="">
		
			<table width="100%" align="center">
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="center" class="datatableheader">
						<b><fmt:message key="MSG.AdtAmtMismatch" bundle="${billprocLabels}"></fmt:message></b>
					</td>				
				</tr>
				<tr><td>&nbsp;</td></tr>				
				<tr>
					<td>
					 	<fmt:message key="MSG.AdtAmtNote" bundle="${billprocLabels}"></fmt:message><br>
						<fmt:message key="MSG.Continue" bundle="${billprocLabels}"></fmt:message>
					</td>				
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td align="center">
						<hdiits:button name="btnAudForward" type="button" value='<%=bundle.getString("COMMON.FORWARD")%>' onclick="javascript:audForward('ifms.htm?actionFlag=getHyrUsers&statusFlag=BAUD');"/>
						<hdiits:button name="btnAudReject" type="button" value='<%=bundle.getString("COMMON.REJECT")%>' onclick="javascript:audReject('ifms.htm?actionFlag=getHyrUsers&statusFlag=BAUD&actionVal=REJECT');"/>
						<hdiits:button name="btnAudCancel" type="button" value='<%=bundle.getString("COMMON.CANCEL")%>' onclick="self.close();"/>					
					</td>
				</tr>
			</table>
		</hdiits:form>
	</body>
</html>
