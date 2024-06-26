<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="common/script/calendar.js"></script>
<script type="text/javascript">
function validate() 
{
  	document.frm_auditorInput.submit();
}	
</script>

<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billProcLabels" scope="application"/>
<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
<c:set var="auditorList" value="${resValue.auditorList}"> </c:set>
<c:set var="billTypeList" value="${resValue.billTypeList}"> </c:set>
<%@page import="java.util.ResourceBundle"%>
<%
ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

<hdiits:form name="frm_auditorInput" validate="true" method="post" action="ifms.htm?actionFlag=genAudWiseBillTypeVito&vitoType=73">
<table border="1" cellspacing="0" cellpadding="0" align="center" width="90%">
	<tr class="TableHeaderBG"> 
	    <td  align="center"><fmt:message key="AUDITORWISEBILLTYPEVITO.TITLE" bundle="${billProcLabels}"></fmt:message></td>
	</tr>
	<tr>
	<td>
	<table align="center" width="90%">
		<tr height="20">
			<td><br></td>
		</tr>	
		<tr>
			<td>
				<table align="center" width="80%" cellspacing="0" cellpadding="0">
					<tr height="20" align="center">
						<td align="left"><hdiits:caption captionid="CMN.AUDITORS" bundle="${billProcLabels}"/>:</td>
						<td align="left">
							<hdiits:select name="cmbAuditors" multiple="true">
								<option value="-1"><fmt:message key="CHQCOMMON.SELECT" bundle="${billProcLabels}"></fmt:message></option>
								<c:forEach var="aud" items="${auditorList}" >
									 <option value='<c:out value="${aud.id}"/>'>
									 	 <c:out value="${aud.desc}"/>
		 							 </option>
   					    		</c:forEach>	
   					    	</hdiits:select>
   					   </td>
   					   <td align="left"><hdiits:caption captionid="CMN.BILL_TYPE" bundle="${billProcLabels}"/>:</td>
						<td align="left">
							<hdiits:select name="cmbBillType" style="width:200px">
								<option value="-1"><fmt:message key="CHQCOMMON.SELECT" bundle="${billProcLabels}"></fmt:message></option>
								<c:forEach var="vo" items="${billTypeList}" >
									 <option value='<c:out value="${vo.subjectId}"/>'>
									 	 <c:out value="${vo.billTypeCode} --"></c:out> 	
									 	 <c:out value="${vo.subjectDesc}"/>
		 							 </option>
	 							
   					    		</c:forEach>	
   					    	</hdiits:select>
   					   </td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>
		<tr>
			<td height="20"></td>
		</tr>
		<tr align="center">
				<td><hdiits:button name="btnGenRpt" type="button" value='<%=buttonBundle.getString("COMMON.GENERATEREPORT")%>'  onclick="validate()"/>	
					<hdiits:button name="btnClose" type="button" value='<%=buttonBundle.getString("COMMON.CANCEL")%>'  onclick="window.location.href='ifms.htm?viewName=homePage'"/>	
				</td>	
		</tr>
		<tr>
			<td height="20"></td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</hdiits:form>
