<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<%@page import="java.util.ResourceBundle"%>
<%
ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

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
<body>

<form name="frmChqRename" validate="true" method="post" >
   	<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		
	<div id="progressImage" style="display:none"></div>		
<input type="hidden" name="chkbox" id="chkbox" > 
<input type="hidden" name="MessageStatus" id="MessageStatus">
<input type="hidden" name="DdoList" id="DdoList" >
<input type="hidden" name="Ddolists" id="Ddolists" >
<table width="90%" align="center" border="0">
	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td align="left">
			<table width="90%" align="center" class="groupTable" border="0" cellpadding="1" cellspacing="1">
				<tr>
					<td class="datatableheader" colspan="4">
						<b><fmt:message key="CHQRENAME.TITLE" bundle="${billprocLabels}"></fmt:message></b> 
					</td>
				</tr>
				<tr><td colspan="4">&nbsp;</td></tr>	
				<tr >
					<td width="7%" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.CHEQUE_NO" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="25%" align="left">
					    :&nbsp;<input type="text" name="txtChqNo" id="txtChqNo" class="texttag mandatorycontrol">
						<a href="#" id="srcClick" ><img src="images/icon_search.gif" ></a>
						<font color="red">*</font>
					</td>
					<td width="5%" align="left">
					<fmt:message key="CHQRENAME.NEW" bundle="${billprocLabels}"></fmt:message> &nbsp;
						<fmt:message key="CMN.CHEQUE_NO" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="*" align="left">
						:&nbsp;<input type="text" name="txtNewChqNo" id="txtNewChqNo" class="texttag mandatorycontrol"></text><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td width="25%" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CMN.IN_FAVOUR_OF" bundle="${billprocLabels}"></fmt:message>(<fmt:message key="CMN.OLD" bundle="${billprocLabels}"></fmt:message>)
					</td>
					<td width="25%" align="left">
						:&nbsp;<input type="text" name="txtPartyName" id="txtPartyName" size="20" readonly="true"></text>
					</td>
					<td width="25%" align="left">
						<fmt:message key="CMN.CHEQUE_AMOUNT" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="25%" align="left">
						:&nbsp;<input type="text" name="txtChqAmt" id="txtChqAmt" readonly="true" ></text>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>									
				</tr>
				<tr>
					<td align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CHQRENAME.TYPE" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left">
						:&nbsp;<select name="ddoParty" id ="ddoParty" style="width:73%">
							<option value="DDO"><fmt:message key="CHQRENAME.DDOCHQ" bundle="${billprocLabels}"></fmt:message></option>
							<option value="PARTY"><fmt:message key="CHQRENAME.PARTYCHQ" bundle="${billprocLabels}"></fmt:message></option>
						</select>
					</td>
					<td width="25%" align="left">
						<fmt:message key="CMN.IN_FAVOUR_OF" bundle="${billprocLabels}"></fmt:message>(New)
					</td>
					<td width="25%" align="left">
						:&nbsp;<input type="text" name="txtPartyNameNew" id="txtPartyNameNew" class="texttag mandatorycontrol"></text>
						 <a href="#" onclick="javascript:getParty();" ><img src="images/party.gif" ></a><font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CHQCOMMON.PARTYACCNT" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td>
						:&nbsp;<input type="text" name="txtAccountNo" id="txtAccountNo" size="20">
					</td>
					<td width="25%" align="left">
						<fmt:message key="CHQ.ADDRESS" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="25%" align="left">
						:&nbsp;<input type=text name="txtAddress" id="txtAddress"> 
						<input type="hidden" name="partyCode" id="partyCode"> &nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>	
				<tr>
					<td width="25%" align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CHQ.CHEQUE_DATE" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td width="25%" align="left">
						:&nbsp;<input type="text" name="txtChqDate" id="txtChqDate" size="20" readonly="true" ></text>
<!-- 						<img src="common/images/calendar.gif" >  -->
					</td>
					
					<td align="left">
						<fmt:message key="CHQRENAME.REASON" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td>
						:&nbsp;<textarea name="txtReason" rows="3" cols="17"></textarea>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>	
			</table>
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td>
			<center>
				<%-- <input type="button" name="btnSearch" value="New Cheque Rename" onclick="javascript:newChq();">--%>
				<hdiits:button type="button" name="btnRename" id="btnClick1" value='<%=buttonBundle.getString("CHQRENAME.RENAME")%>' readonly="true" onclick="javascript:renameChq();"></hdiits:button>
				<hdiits:button type="button" name="btnChq" id="btnClick3" classcss="bigbutton" value='<%=buttonBundle.getString("CHQRENAME.GENCHEQUE")%>'  onclick="javascript:genChq();" readonly="true"></hdiits:button>
				<%--  <input type="button" name="btnAdvice"  id="btnClick2" value="Generate Advice" disabled=true onclick="javascript:genAdvice();">--%>
				
	   			
				<%-- <input type="button" name="btnClose" value="Close"  onclick="window.location.href='index.jsp'" > --%>
			</center>
		</td>
	</tr>					
</table>




</form>


<ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=chqFrmChqNum&chqPage=chqRen" 
	   		action="srcClick"  source="txtChqNo" target="txtPartyName,txtChqAmt,txtChqDate,txtNewChqNo,MessageStatus,txtAccountNo,txtAddress,partyCode" 
		   	parameters="txtChqNo={txtChqNo}" postFunction="validateChqRname" ></ajax:updateField>	
  <ajax:updateField baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=chqRenameDetail" 
	   		action="btnClick1"  source="txtChqNo" target="txtPartyName,txtChqAmt,txtChqDate,txtNewChqNo,chkbox,MessageStatus" 
		   	parameters="txtChqNo={txtChqNo},txtPartyName={txtPartyName},txtChqAmt={txtChqAmt},txtChqDate={txtChqDate},txtNewChqNo={txtNewChqNo},ddoParty={ddoParty},txtPartyNameNew={txtPartyNameNew},txtReason={txtReason},MessageStatus={MessageStatus},txtAccountNo={txtAccountNo},txtAddress={txtAddress},partyCode={partyCode}" postFunction="disableRname" ></ajax:updateField>		
</body>
</html>
				