<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
<form name="frmChqRename" validate="true" method="post" >
<input type="hidden" name="chkbox" id="chkbox" > 
<input type="hidden" name="MessageStatus" id="MessageStatus">

<table width="90%" align="center">
	<tr><td>&nbsp;</td></tr>	
	<tr>
		<td>
			<table width="90%" align="center" class="groupTable">
				<tr align="center">
					<td class="datatableheader" colspan="4">
						<b><fmt:message key="CHQDUPLICATE.TITLE" bundle="${billprocLabels}"></fmt:message></b> 
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
						<font color="red">*</font>
					</td>
					<td width="7%" align="left">
						<fmt:message key="CHQCOMMON.NEW" bundle="${billprocLabels}"></fmt:message> &nbsp;
						<fmt:message key="CMN.CHEQUE_NO" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="36%" align="left">
						:&nbsp;<input type="text" name="txtNewChqNo" id="txtNewChqNo" size="20" class="texttag mandatorycontrol"></text><font color="red">*</font>
						&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td width="25%" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.IN_FAVOUR_OF" bundle="${billprocLabels}"></fmt:message>(<fmt:message key="CMN.OLD" bundle="${billprocLabels}"></fmt:message>)
					</td>
					<td width="25%" align="left">
						:&nbsp;<input type="text" name="txtPartyName" id="txtPartyName" readonly="true"></text>
					</td>
					<td width="25%" align="left">
						<fmt:message key="CMN.CHEQUE_AMOUNT" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="25%" align="left">
						:&nbsp;<input type="text" name="txtChqAmt" id="txtChqAmt" size="20" readonly="true" ></text>&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="hidden" name="ddoParty" id="ddoParty" value="~">
						<input type="hidden" name="txtPartyNameNew" id="txtPartyNameNew" value="~">
					</td>									
				</tr>
				
				<tr>
					<td width="25%" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CHQ.CHEQUE_DATE" bundle="${billprocLabels}"></fmt:message>:
					</td>
					<td width="25%" align="left" >
						:&nbsp;<input type="text" name="txtChqDate" id="txtChqDate" size="20" readonly="true" ></text>
<!-- 						<img src="common/images/calendar.gif" >  -->
					</td>
					
					<td align="left">
						<fmt:message key="CHQCOMMON.REASON" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td>
						:&nbsp;<textarea name="txtReason" rows="3" cols="17"></textarea>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>	
			</table>
		</td>
	</tr>
	<tr><td><br></td></tr>
	<tr>
		<td>
			<center>
				<%-- <input type="button" name="btnSearch" value="New Cheque Rename" onclick="javascript:newChq();">--%>
				<hdiits:button type="button" name="btnRename" id="btnClick1" value='<%=buttonBundle.getString("CHQDUPLICATION.DUPLICATE")%>' readonly="true" onclick="javascript:duplChq();"></hdiits:button>
				<hdiits:button type="button" name="btnChq" id="btnClick3" classcss="bigbutton" value='<%=buttonBundle.getString("CHQCOMMON.GENCHEQUE")%>'  onclick="javascript:genChq();" readonly="true"></hdiits:button>
				<%--<input type="button" name="btnAdvice" id="btnClick2" value="Generate Advice" disabled=true onclick="javascript:genAdvice();"> --%>
				
	   			
				<%-- <input type="button" name="btnClose" value="Close"  onclick="window.location.href='index.jsp'" > --%>
			</center>
		</td>
	</tr>					
</table>
</form>
<ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=chqFrmChqNum&chqPage=chqDup" 
	   		action="srcClick"  source="txtChqNo" target="txtPartyName,txtChqAmt,txtChqDate,txtNewChqNo" 
		   	parameters="txtChqNo={txtChqNo}" postFunction="validateChqDupl" ></ajax:updateField>	
  <ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=chqRenameDetail" 
	   		action="btnClick1"  source="txtChqNo" target="txtPartyName,txtChqAmt,txtChqDate,txtNewChqNo,chkbox,MessageStatus" 
		   	parameters="txtChqNo={txtChqNo},txtPartyName={txtPartyName},txtChqAmt={txtChqAmt},txtChqDate={txtChqDate},txtNewChqNo={txtNewChqNo},ddoParty={ddoParty},txtPartyNameNew={txtPartyNameNew},txtReason={txtReason},MessageStatus={MessageStatus}" postFunction="disableDupl" ></ajax:updateField>		
</body>
</html>
				