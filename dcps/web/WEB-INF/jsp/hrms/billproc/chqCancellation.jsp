<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
    
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>

<html>
<head>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script type="text/javascript" src="common/script/prototype-  
          1.3.1.js">
</script>

<script type="text/javascript" src="common/script/ajaxtags- 
           1.1.5.js">
</script>

<script type="text/javascript" src="script/billproc/chqValidation.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	 		
</head>
<%@page import="java.util.ResourceBundle"%>
<%
ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

<body>
<form name="frmChqCancle" validate="true" method="post" >
<input type="hidden" name="chkbox" id="chkbox" > 
<table width="90%" align="center" border="0">
	<tr><td>&nbsp;</td></tr>	
	<tr>
		<td>
		
			<table width="90%" align="center" border="0" cellpadding="1" cellspacing="1" class="groupTable">
				<tr>
					<td class="datatableheader" colspan="4" align="center">
						<b><fmt:message key="CHQCANCEL.TITLE" bundle="${billprocLabels}"></fmt:message></b> 
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td width="7%" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.CHEQUE_NO" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="30%" align="left">
					   :&nbsp;<input type="text" name="txtChqNo" id="txtChqNo" class="texttag mandatorycontrol">
						<a href="#" id="srcClick" ><img src="images/icon_search.gif" ></a>
						<input type="hidden" name="txtNewChqNo" id="txtNewChqNo" >
						<font color="red">*</font>
					</td>
					<td width="7%" align="left">&nbsp;</td>
					<td width="36%" align="left">&nbsp;</td>
				</tr>
				<tr>
					<td width="25%" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.IN_FAVOUR_OF" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="25%" align="left">
						:&nbsp;<input type="text" name="txtPartyName" id="txtPartyName" readonly="true" size="20">
					</td>
					<td width="25%" align="left">
						<fmt:message key="CMN.CHEQUE_AMOUNT" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="25%" align="left">
						:&nbsp;<input type="text" name="txtChqAmt" id="txtChqAmt" readonly="true" size="20">&nbsp;&nbsp;&nbsp;&nbsp;
					</td>									
				</tr>
				
				<tr>
					<td width="25%" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CHQ.CHEQUE_DATE" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="25%" align="left" >
						:&nbsp;<input type="text" name="txtChqDate" id="txtChqDate" readonly="true" size="20">
			<!-- 						<img src="common/images/calendar.gif" >  -->
					</td>
					
					<td align="left">
						<fmt:message key="CHQCANCEL.REASON" bundle="${billprocLabels}"></fmt:message>:
					</td>
					<td>
					:&nbsp;<textarea name="txtReason" rows="3" cols="17"></textarea>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>					
			</table>
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td colspan="4">
			<center>
			<%-- <input type="button" name="btnSearch" value="New Cheque Rename" onclick="javascript:newChq();">--%>
				<hdiits:button type="button" name="btnCancle" id="btnClick1" readonly="true" value='<%=buttonBundle.getString("CHQCANCEL.CANCEL")%>' onclick="javascript:cancleChq();"></hdiits:button>
				<%--=buttonBundle.getString("CHQCANCEL.CANCEL")--%>
			
			<%-- <input type="button" name="btnClose" value="Close"  onclick="window.location.href='index.jsp'" > --%>
			</center>
		</td>
	</tr>
</table>
</form>
<ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=chqFrmChqNum&chqPage=chqCncl" 
	   		action="srcClick"  source="txtChqNo" target="txtPartyName,txtChqAmt,txtChqDate,txtNewChqNo" 
		   	parameters="txtChqNo={txtChqNo}" postFunction="validateChqCncl" ></ajax:updateField>	
</body>
</html>
				